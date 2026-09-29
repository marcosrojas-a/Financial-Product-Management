import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ApiService } from '../../core/Services/api.service';
import { Client } from '../../core/Models/client.model';
import { FinancialProduct } from '../../core/Models/financial-product.model';
import { Movement } from '../../core/Models/transaction.model';
import { formatCurrency, formatDateTime } from '../../core/utils';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent implements OnInit {
  private apiService = inject(ApiService);

  clients: Client[] = [];
  products: FinancialProduct[] = [];
  recentMovements: Movement[] = [];
  isLoading = true;

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    forkJoin({
      clients: this.apiService.get<Client[]>('/api/clientes'),
      products: this.apiService.get<FinancialProduct[]>('/api/productos-financieros'),
      movements: this.apiService.get<Movement[]>('/api/movimiento'),
    }).subscribe({
      next: ({ clients, products, movements }) => {
        this.clients = clients;
        this.products = products;
        this.recentMovements = movements.slice(0, 5);
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  get totalClients(): number {
    return this.clients.length;
  }

  get totalAccounts(): number {
    return this.products.length;
  }

  get activeAccounts(): number {
    return this.products.filter((p) => p.productStatus === 'Activa').length;
  }

  get cancelledAccounts(): number {
    return this.products.filter((p) => p.productStatus === 'Cancelada').length;
  }

  formatCurrency = formatCurrency;
  formatDateTime = formatDateTime;
}