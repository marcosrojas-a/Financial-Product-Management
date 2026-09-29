import { Component, Output, EventEmitter, inject, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../core/Services/api.service';
import { FinancialProduct } from '../../core/Models/financial-product.model';

type TransactionType = 'CONSIGNATION' | 'WITHDRAWAL' | 'TRANSFER';

type SubmitEvent = { type: TransactionType; data: unknown };

@Component({
  selector: 'app-transaction-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './transaction-form.html',
  styleUrl: './transaction-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TransactionFormComponent implements OnInit {
  private apiService = inject(ApiService);
  private fb = new FormBuilder();

  @Output() submit = new EventEmitter<SubmitEvent>();
  @Output() cancel = new EventEmitter<void>();

  products: FinancialProduct[] = [];
  isLoading = true;

  form: FormGroup = this.fb.group({
    type: ['CONSIGNATION', Validators.required],
    productId: [null, Validators.required],
    originProductId: [null, Validators.required],
    destinationProductId: [null, Validators.required],
    amount: [null, [Validators.required, Validators.min(1)]],
    description: [null],
  });

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.apiService.get<FinancialProduct[]>('/api/productos-financieros').subscribe({
      next: (data) => {
        this.products = data.filter((p) => p.productStatus === 'Activa');
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  get activeProducts(): FinancialProduct[] {
    return this.products;
  }

  get typeControl(): FormGroup['controls'] {
    return { type: this.form.get('type')! };
  }

  isConsignment(): boolean {
    return this.form.get('type')?.value === 'CONSIGNATION';
  }

  isWithdrawal(): boolean {
    return this.form.get('type')?.value === 'WITHDRAWAL';
  }

  isTransfer(): boolean {
    return this.form.get('type')?.value === 'TRANSFER';
  }

  getSubmitType(): TransactionType {
    return this.form.get('type')?.value as TransactionType;
  }

  getSubmitData(): unknown {
    const values = this.form.getRawValue();
    const type = values.type;

    if (type === 'TRANSFER') {
      return {
        originProductId: values.originProductId,
        destinationProductId: values.destinationProductId,
        amount: values.amount,
        description: values.description,
      };
    }

    return {
      productId: values.productId,
      amount: values.amount,
      description: values.description,
    };
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const type = this.getSubmitType();
    const data = this.getSubmitData();
    this.submit.emit({ type, data });
  }

  formatProductName(product: FinancialProduct): string {
    return `${product.accountNumber} (${product.productType}) - Saldo: ${product.balance}`;
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
