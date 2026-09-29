import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../core/Services/api.service';
import { NotificationService } from '../../core/Services/notification.service';
import { FinancialProduct, ProductStatus, FinancialProductRequest } from '../../core/Models/financial-product.model';
import { Client } from '../../core/Models/client.model';
import { TableColumn, TableAction } from '../../shared/components/generic-table/generic-table';
import { GenericTableComponent } from '../../shared/components/generic-table/generic-table';
import { formatCurrency } from '../../core/utils';

interface ProductWithClient extends FinancialProduct {
  clientName?: string;
}

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, GenericTableComponent],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class AccountsComponent implements OnInit {
  private apiService = inject(ApiService);
  private notificationService = inject(NotificationService);

  products: ProductWithClient[] = [];
  clients: Client[] = [];
  statuses: ProductStatus[] = [];
  isLoading = false;

  columns: TableColumn[] = [
    { key: 'accountNumber', label: 'Número de Cuenta' },
    { key: 'productType', label: 'Tipo' },
    { key: 'clientName', label: 'Cliente' },
    { key: 'balance', label: 'Saldo', type: 'currency' },
    { key: 'availableBalance', label: 'Saldo Disponible', type: 'currency' },
    { key: 'productStatus', label: 'Estado' },
    { key: 'exemptGmf', label: 'Exenta GMF', type: 'boolean' },
  ];

  actions: TableAction[] = [
    { label: 'Cancelar', icon: '❌', action: 'cancel', color: 'warn' },
  ];

  ngOnInit(): void {
    this.loadProducts();
    this.loadStatuses();
    this.loadClients();
  }

  loadProducts(): void {
    this.isLoading = true;
    this.apiService.get<FinancialProduct[]>('/api/productos-financieros').subscribe({
      next: (data) => {
        this.products = data.map((p) => ({ ...p, clientName: this.getClientName(p.clientId) }));
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  loadStatuses(): void {
    this.apiService.get<ProductStatus[]>('/api/product-status').subscribe((data) => {
      this.statuses = data;
    });
  }

  loadClients(): void {
    this.apiService.get<Client[]>('/api/clientes').subscribe((data) => {
      this.clients = data;
      this.refreshClientNames();
    });
  }

  private getClientName(clientId: number): string {
    const client = this.clients.find((c) => c.id === clientId);
    return client ? `${client.nombres} ${client.apellidos}` : 'Desconocido';
  }

  private refreshClientNames(): void {
    this.products = this.products.map((p) => ({ ...p, clientName: this.getClientName(p.clientId) }));
  }

  getCancelledStatus(): ProductStatus | undefined {
    return this.statuses.find((s) => s.code === 'CANCELADA');
  }

  onCancel(row: any): void {
    const product = row as FinancialProduct;
    const cancelledStatus = this.getCancelledStatus();

    if (!cancelledStatus) {
      this.notificationService.error('No se pudo determinar el estado de cancelación');
      return;
    }

    if (confirm('¿Está seguro de cancelar esta cuenta? Solo se pueden cancelar cuentas con saldo $0.')) {
      const request: FinancialProductRequest = { newStatusId: cancelledStatus.id };
      this.apiService.put<FinancialProduct>(`/api/productos-financieros/${product.id}`, request).subscribe({
        next: (updated) => {
          const idx = this.products.findIndex((p) => p.id === updated.id);
          this.products[idx] = { ...updated, clientName: this.getClientName(updated.clientId) };
          this.notificationService.success('Cuenta cancelada correctamente');
        },
      });
    }
  }

  onRowAction(event: { action: string; row: any }): void {
    if (event.action === 'cancel') {
      this.onCancel(event.row);
    }
  }

  formatCurrency = formatCurrency;
}
