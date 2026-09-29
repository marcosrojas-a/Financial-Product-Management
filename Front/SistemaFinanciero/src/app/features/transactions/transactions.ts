import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../core/Services/api.service';
import { NotificationService } from '../../core/Services/notification.service';
import { Movement, Transfer } from '../../core/Models/transaction.model';
import { TransactionFormComponent } from './transaction-form';
import { formatCurrency, formatDateTime } from '../../core/utils';

type SubmitEvent = { type: 'CONSIGNATION' | 'WITHDRAWAL' | 'TRANSFER'; data: unknown };

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, TransactionFormComponent],
  templateUrl: './transactions.html',
  styleUrl: './transactions.css',
})
export class TransactionsComponent implements OnInit {
  private apiService = inject(ApiService);
  private notificationService = inject(NotificationService);
  private route = inject(ActivatedRoute);

  movements: Movement[] = [];
  isLoading = false;
  showForm = false;

  ngOnInit(): void {
    this.loadMovements();

    // Atajo desde el resumen: /transactions?nuevo=true
    if (this.route.snapshot.queryParamMap.get('nuevo')) {
      this.onCreate();
    }
  }

  loadMovements(): void {
    this.isLoading = true;
    this.apiService.get<Movement[]>('/api/movimiento').subscribe({
      next: (data) => {
        this.movements = this.sortByDateDesc(data);
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
          next: (movement) => this.onSuccess([movement], 'Consignación realizada correctamente'),
        });
        break;
      case 'WITHDRAWAL':
        this.apiService.post<Movement>('/api/movimiento/retiros', event.data).subscribe({
          next: (movement) => this.onSuccess([movement], 'Retiro realizado correctamente'),
        });
        break;
      case 'TRANSFER':
        this.apiService.post<Transfer>('/api/movimiento/transferencias', event.data).subscribe({
          next: (transfer) =>
            this.onSuccess(
              [transfer.originMovement, transfer.destinationMovement],
              'Transferencia realizada correctamente'
            ),
        });
        break;
    }
  }

  onFormCancel(): void {
    this.showForm = false;
  }

  private onSuccess(created: Movement[], message: string): void {
    this.movements = this.sortByDateDesc([...created, ...this.movements]);
    this.notificationService.success(message);
    this.showForm = false;
  }

  private sortByDateDesc(list: Movement[]): Movement[] {
    return [...list].sort(
      (a, b) => new Date(b.movementDate).getTime() - new Date(a.movementDate).getTime()
    );
  }

  // El signo se deduce de la variación del saldo, así una transferencia
  // resta en la cuenta origen y suma en la destino.
  isCredit(m: Movement): boolean {
    return Number(m.newBalance) > Number(m.previousBalance);
  }

  signedAmount(m: Movement): string {
    const sign = this.isCredit(m) ? '+' : '-';
    return `${sign}${formatCurrency(Math.abs(Number(m.amount)))}`;
  }

  formatCurrency = formatCurrency;
  formatDateTime = formatDateTime;
}