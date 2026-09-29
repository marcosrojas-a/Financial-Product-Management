import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../core/Services/api.service';
import { NotificationService } from '../../core/Services/notification.service';
import { Movement, Transfer } from '../../core/Models/transaction.model';
import { FinancialProduct } from '../../core/Models/financial-product.model';
import { TableColumn, TableAction } from '../../shared/components/generic-table/generic-table';
import { GenericTableComponent } from '../../shared/components/generic-table/generic-table';
import { TransactionFormComponent } from './transaction-form';
import { formatCurrency, formatDateTime } from '../../core/utils';

type SubmitEvent = { type: 'CONSIGNATION' | 'WITHDRAWAL' | 'TRANSFER'; data: unknown };

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, GenericTableComponent, TransactionFormComponent],
  templateUrl: './transactions.html',
  styleUrl: './transactions.css',
})
export class TransactionsComponent implements OnInit {
  private apiService = inject(ApiService);
  private notificationService = inject(NotificationService);

  movements: Movement[] = [];
  isLoading = false;
  showForm = false;

  columns: TableColumn[] = [
    { key: 'movementDate', label: 'Fecha', type: 'datetime' },
    { key: 'movementType', label: 'Tipo' },
    { key: 'description', label: 'Descripción' },
    { key: 'amount', label: 'Valor', type: 'currency' },
    { key: 'previousBalance', label: 'Saldo Anterior', type: 'currency' },
    { key: 'newBalance', label: 'Saldo Nuevo', type: 'currency' },
  ];

  actions: TableAction[] = [
    { label: 'Ver', icon: '👁', action: 'view' },
  ];

  ngOnInit(): void {
    this.loadMovements();
  }

  loadMovements(): void {
    this.isLoading = true;
    this.apiService.get<Movement[]>('/api/movimiento').subscribe({
      next: (data) => {
        this.movements = data;
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  onCreate(): void {
    this.showForm = true;
  }

  onFormSubmit(event: SubmitEvent): void {
    switch (event.type) {
      case 'CONSIGNATION':
        this.apiService.post<Movement>('/api/movimiento/consignaciones', event.data).subscribe({
          next: (movement) => {
            this.movements = [movement, ...this.movements];
            this.notificationService.success('Consignación realizada correctamente');
            this.showForm = false;
          },
        });
        break;
      case 'WITHDRAWAL':
        this.apiService.post<Movement>('/api/movimiento/retiros', event.data).subscribe({
          next: (movement) => {
            this.movements = [movement, ...this.movements];
            this.notificationService.success('Retiro realizado correctamente');
            this.showForm = false;
          },
        });
        break;
      case 'TRANSFER':
        this.apiService.post<Transfer>('/api/movimiento/transferencias', event.data).subscribe({
          next: (transfer) => {
            this.movements = [transfer.originMovement, transfer.destinationMovement, ...this.movements];
            this.notificationService.success('Transferencia realizada correctamente');
            this.showForm = false;
          },
        });
        break;
    }
  }

  onFormCancel(): void {
    this.showForm = false;
  }

  onView(row: any): void {
    const movement = row as Movement;
    alert(
      `Detalle del movimiento:\n\n` +
        `Tipo: ${movement.movementType}\n` +
        `Valor: ${formatCurrency(movement.amount)}\n` +
        `Saldo Anterior: ${formatCurrency(movement.previousBalance)}\n` +
        `Saldo Nuevo: ${formatCurrency(movement.newBalance)}\n` +
        `Descripción: ${movement.description || 'N/A'}\n` +
        `Fecha: ${formatDateTime(movement.movementDate)}`
    );
  }

  onRowAction(event: { action: string; row: any }): void {
    if (event.action === 'view') {
      this.onView(event.row);
    }
  }

  formatCurrency = formatCurrency;
}
