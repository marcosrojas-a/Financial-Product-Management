import { Component, Input, Output, EventEmitter, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Client, ClientRequest, DocumentType } from '../../core/Models/client.model';
import { FormField } from '../../shared/components/generic-form/generic-form';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, GenericFormComponent],
  templateUrl: './client-form.html',
  styleUrl: './client-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ClientFormComponent implements OnInit {
  private fb = new FormBuilder();

  @Input() documentTypes: DocumentType[] = [];
  @Input() client: Client | null = null;
  @Output() save = new EventEmitter<ClientRequest>();
  @Output() cancel = new EventEmitter<void>();

  form = this.fb.group({
    tipoDocumentoId: [null as any, Validators.required],
    numeroDocumento: [null as any, Validators.required],
    nombres: [null as any, Validators.required],
    apellidos: [null as any, Validators.required],
    correo: [null as any, Validators.required],
    fechaNacimiento: [null as any, Validators.required],
  });

  fields: FormField[] = [
    { key: 'tipoDocumentoId', label: 'Tipo de Documento', type: 'select', required: true, full: true },
    { key: 'numeroDocumento', label: 'Número de Documento', type: 'text', placeholder: 'Ingrese el número de documento', required: true },
    { key: 'nombres', label: 'Nombres', type: 'text', placeholder: 'Ingrese los nombres', required: true },
    { key: 'apellidos', label: 'Apellidos', type: 'text', placeholder: 'Ingrese los apellidos', required: true },
    { key: 'correo', label: 'Correo Electrónico', type: 'email', placeholder: 'correo@ejemplo.com', required: true, full: true },
    { key: 'fechaNacimiento', label: 'Fecha de Nacimiento', type: 'date', required: true },
  ];

  ngOnInit(): void {
    this.updateOptions();
    this.patchClient();
  }

  ngOnChanges(): void {
    this.updateOptions();
    this.patchClient();
  }

  private updateOptions(): void {
    const docTypeField = this.fields.find((f) => f.key === 'tipoDocumentoId');
    if (docTypeField && this.documentTypes.length) {
      docTypeField.options = this.documentTypes.map((dt) => ({ value: dt.id, label: dt.name }));
    }
  }

  private patchClient(): void {
    if (this.client) {
      this.form.patchValue({
        tipoDocumentoId: this.client.tipoDocumentoId,
        numeroDocumento: this.client.numeroDocumento,
        nombres: this.client.nombres,
        apellidos: this.client.apellidos,
        correo: this.client.correo,
        fechaNacimiento: this.client.fechaNacimiento,
      });
    }
  }

  onSave(data: Record<string, any>): void {
    this.save.emit(data as ClientRequest);
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
