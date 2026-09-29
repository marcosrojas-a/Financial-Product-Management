import { Component, Input, Output, EventEmitter, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FinancialProductRequest, ProductType } from '../../core/Models/financial-product.model';
import { Client } from '../../core/Models/client.model';
import { FormField } from '../../shared/components/generic-form/generic-form';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form';

@Component({
  selector: 'app-account-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, GenericFormComponent],
  templateUrl: './account-form.html',
  styleUrl: './account-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountFormComponent implements OnInit {
  private fb = new FormBuilder();

  @Input() productTypes: ProductType[] = [];
  @Input() clients: Client[] = [];
  @Output() save = new EventEmitter<FinancialProductRequest>();
  @Output() cancel = new EventEmitter<void>();

  form = this.fb.group({
    clientId: [null as any, Validators.required],
    productTypeId: [null as any, Validators.required],
    initialBalance: [0 as any, [Validators.required, Validators.min(0)]],
    exemptGmf: [false],
  });

  fields: FormField[] = [
    { key: 'clientId', label: 'Cliente', type: 'select', required: true, full: true },
    { key: 'productTypeId', label: 'Tipo de Cuenta', type: 'select', required: true, full: true },
    { key: 'initialBalance', label: 'Saldo Inicial', type: 'number', placeholder: '0.00', min: 0, step: '0.01', required: true },
    { key: 'exemptGmf', label: 'Exenta de GMF', type: 'checkbox' },
  ];

  ngOnInit(): void {
    this.updateOptions();
  }

  ngOnChanges(): void {
    this.updateOptions();
  }

  private updateOptions(): void {
    const clientField = this.fields.find((f) => f.key === 'clientId');
    if (clientField) {
      clientField.options = this.clients.map((c) => ({
        value: c.id,
        label: `${c.nombres} ${c.apellidos} — ${c.numeroDocumento}`,
      }));
    }

    const productTypeField = this.fields.find((f) => f.key === 'productTypeId');
    if (productTypeField) {
      productTypeField.options = this.productTypes.map((pt) => ({ value: pt.id, label: pt.name }));
    }
  }

  onSave(data: Record<string, any>): void {
    const payload: FinancialProductRequest = {
      clientId: data['clientId'],
      productTypeId: data['productTypeId'],
      initialBalance: Number(data['initialBalance']) || 0,
      exemptGmf: !!data['exemptGmf'],
    };

    this.save.emit(payload);
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
