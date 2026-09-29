import {
  Component,
  Output,
  EventEmitter,
  inject,
  OnInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../core/Services/api.service';
import { FinancialProduct } from '../../core/Models/financial-product.model';
import { formatCurrency } from '../../core/utils';

type TransactionType = 'CONSIGNATION' | 'WITHDRAWAL' | 'TRANSFER';

type SubmitEvent = { type: TransactionType; data: unknown };

interface TypeTab {
  value: TransactionType;
  label: string;
  verb: string;
  icon: string;
}

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
  private cdr = inject(ChangeDetectorRef);
  private fb = new FormBuilder();

  @Output() formSubmit = new EventEmitter<SubmitEvent>();

  products: FinancialProduct[] = [];
  isLoading = true;

  tabs: TypeTab[] = [
    { value: 'CONSIGNATION', label: 'Consignar', verb: 'consignar', icon: 'download' },
    { value: 'WITHDRAWAL', label: 'Retirar', verb: 'retirar', icon: 'upload' },
    { value: 'TRANSFER', label: 'Transferir', verb: 'transferir', icon: 'swap_horiz' },
  ];

  form: FormGroup = this.fb.group({
    type: ['CONSIGNATION', Validators.required],
    productId: [null, Validators.required],
    destinationAccount: [''],
    amount: [null, [Validators.required, Validators.min(1)]],
    description: [''],
  });

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.apiService.get<FinancialProduct[]>('/api/productos-financieros').subscribe({
      next: (data) => {
        this.products = data.filter((p) => p.productStatus === 'Activa');
        this.isLoading = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.isLoading = false;
        this.cdr.markForCheck();
      },
    });
  }

  get currentType(): TransactionType {
    return this.form.get('type')?.value as TransactionType;
  }

  get currentTab(): TypeTab {
    return this.tabs.find((t) => t.value === this.currentType) ?? this.tabs[0];
  }

  get isTransfer(): boolean {
    return this.currentType === 'TRANSFER';
  }

  get selectedProduct(): FinancialProduct | undefined {
    const id = this.form.get('productId')?.value;
    return this.products.find((p) => p.id === id);
  }

  // La cuenta destino se escribe por número y se resuelve contra las cuentas activas.
  get destinationProduct(): FinancialProduct | undefined {
    const raw = this.cleanNumber(this.form.get('destinationAccount')?.value);
    return raw ? this.products.find((p) => this.cleanNumber(p.accountNumber) === raw) : undefined;
  }

  get destinationError(): string | null {
    const control = this.form.get('destinationAccount');
    return control && (control.touched || control.dirty) ? this.destinationIssue() : null;
  }

  private destinationIssue(): string | null {
    if (!this.isTransfer) return null;

    if (!this.cleanNumber(this.form.get('destinationAccount')?.value)) {
      return 'Ingresa el número de cuenta destino';
    }
    const destination = this.destinationProduct;
    if (!destination) {
      return 'No existe una cuenta activa con ese número';
    }
    if (destination.id === this.form.get('productId')?.value) {
      return 'La cuenta destino debe ser distinta a la de origen';
    }
    return null;
  }

  setType(type: TransactionType): void {
    this.form.get('type')?.setValue(type);
  }

  isInvalid(key: string): boolean {
    const control = this.form.get(key);
    return !!control && control.invalid && (control.dirty || control.touched);
  }

  onSubmit(): void {
    this.form.markAllAsTouched();
    if (this.form.invalid || this.destinationIssue()) {
      return;
    }

    const values = this.form.getRawValue();
    const description = values.description?.trim() || null;

    if (this.isTransfer) {
      this.formSubmit.emit({
        type: 'TRANSFER',
        data: {
          originProductId: values.productId,
          destinationProductId: this.destinationProduct!.id,
          amount: values.amount,
          description,
        },
      });
      return;
    }

    this.formSubmit.emit({
      type: values.type,
      data: { productId: values.productId, amount: values.amount, description },
    });
  }

  formatAccountNumber(value: string | number): string {
    const digits = String(value ?? '');
    return /^\d+$/.test(digits) ? digits.replace(/(\d{4})(?=\d)/g, '$1 ') : digits;
  }

  private cleanNumber(value: unknown): string {
    return String(value ?? '').replace(/\s/g, '');
  }

  formatCurrency = formatCurrency;
}