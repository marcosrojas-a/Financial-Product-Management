import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../core/Services/api.service';
import { Client } from '../../core/Models/client.model';
import { FinancialProduct } from '../../core/Models/financial-product.model';
import { Movement } from '../../core/Models/transaction.model';
import { formatCurrency, formatDateTime } from '../../core/utils';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
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
    this.apiService.get<Client[]>('/api/clientes').subscribe((data) => {
      this.clients = data;
      this.loadProducts();
    });
  }

  loadProducts(): void {
    this.apiService.get<FinancialProduct[]>('/api/productos-financieros').subscribe((data) => {
      this.products = data;
      this.loadMovements();
    });
  }

  loadMovements(): void {
    this.apiService.get<Movement[]>('/api/movimiento').subscribe((data) => {
      this.recentMovements = data.slice(0, 5);
      this.isLoading = false;
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
