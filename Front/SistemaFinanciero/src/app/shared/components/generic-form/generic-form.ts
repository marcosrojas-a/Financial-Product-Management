import { Component, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';

export type FieldType =
  | 'text'
  | 'number'
  | 'email'
  | 'date'
  | 'select'
  | 'checkbox'
  | 'textarea';

export interface FormFieldOption {
  value: any;
  label: string;
}

export interface FormField {
  key: string;
  label: string;
  type: FieldType;
  options?: FormFieldOption[];
  placeholder?: string;
  min?: number;
  step?: string;
  full?: boolean;
  required?: boolean;
}

@Component({
  selector: 'app-generic-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './generic-form.html',
  styleUrl: './generic-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GenericFormComponent {
  @Input({ required: true }) form!: FormGroup;
  @Input() fields: FormField[] = [];
  @Input() submitLabel = 'Guardar';
  @Output() formSubmit = new EventEmitter<Record<string, any>>();
  @Output() cancel = new EventEmitter<void>();

  getFieldControl(key: string): FormControl | null {
    return this.form.get(key) as FormControl | null;
  }

  isInvalid(key: string): boolean {
    const control = this.form.get(key);
    return !!control && control.invalid && (control.dirty || control.touched);
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.formSubmit.emit(this.form.getRawValue());
    } else {
      this.form.markAllAsTouched();
    }
  }

  onReset(): void {
    this.form.reset();
    this.cancel.emit();
  }
}
