import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../core/Services/api.service';
import { NotificationService } from '../../core/Services/notification.service';
import { FinancialProduct, ProductType, ProductStatus, FinancialProductRequest } from '../../core/Models/financial-product.model';
import { Client } from '../../core/Models/client.model';
import { AccountFormComponent } from './account-form';
import { formatCurrency } from '../../core/utils';

interface ProductWithClient extends FinancialProduct {
  clientName?: string;
}

const CREATEABLE_PRODUCT_TYPE_CODES = ['AHORROS', 'CORRIENTE'];

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, AccountFormComponent],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class AccountsComponent implements OnInit {
  private apiService = inject(ApiService);
  private notificationService = inject(NotificationService);
  private route = inject(ActivatedRoute);

  products: ProductWithClient[] = [];
  clients: Client[] = [];
  productTypes: ProductType[] = [];
  statuses: ProductStatus[] = [];
  isLoading = false;
  showForm = false;

  ngOnInit(): void {
    this.loadProducts();
    this.loadStatuses();
    this.loadClients();
    this.loadProductTypes();
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

  loadProductTypes(): void {
    this.apiService.get<ProductType[]>('/api/product-type').subscribe({
      next: (data) => {
        this.productTypes = data.filter(
          (pt) => pt.active && CREATEABLE_PRODUCT_TYPE_CODES.includes(pt.code.toUpperCase())
        );
        this.openFormFromShortcut();
      },
      error: () => {
        this.productTypes = [];
        this.notificationService.error('No se pudieron cargar los tipos de cuenta');
      },
    });
  }

  // Atajo desde el resumen: /accounts?nuevo=true
  // Se ejecuta cuando ya cargaron los tipos, porque onCreate() valida que existan.
  private openFormFromShortcut(): void {
    if (this.route.snapshot.queryParamMap.get('nuevo')) {
      this.onCreate();
    }
  }

  onCreate(): void {
    if (!this.clients.length) {
      this.notificationService.error('Debe registrar un cliente antes de crear una cuenta');
      return;
    }

    if (!this.productTypes.length) {
      this.notificationService.error('No hay tipos de cuenta disponibles para crear cuentas');
      return;
    }

    this.showForm = true;
  }

  onFormSubmit(data: FinancialProductRequest): void {
    this.apiService.post<FinancialProduct>('/api/productos-financieros', data).subscribe({
      next: (created) => {
        this.products.push({ ...created, clientName: this.getClientName(created.clientId) });
        this.notificationService.success('Cuenta creada correctamente');
        this.showForm = false;
      },
    });
  }

  onFormCancel(): void {
    this.showForm = false;
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
          if (idx >= 0) {
            this.products[idx] = { ...updated, clientName: this.getClientName(updated.clientId) };
          }
          this.notificationService.success('Cuenta cancelada correctamente');
        },
      });
    }
  }

  // Helpers de la vista
  isActive(product: FinancialProduct): boolean {
    return product.productStatus?.toLowerCase() === 'activa';
  }

  formatAccountNumber(value: string | number): string {
    const digits = String(value ?? '');
    return /^\d+$/.test(digits) ? digits.replace(/(\d{4})(?=\d)/g, '$1 ') : digits;
  }

  trackById(_: number, product: FinancialProduct): number {
    return product.id;
  }

  formatCurrency = formatCurrency;
}