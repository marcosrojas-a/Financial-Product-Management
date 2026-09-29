import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../core/Services/api.service';
import { NotificationService } from '../../core/Services/notification.service';
import { Client, ClientRequest, DocumentType } from '../../core/Models/client.model';
import { GenericTableComponent, TableColumn, TableAction } from '../../shared/components/generic-table/generic-table';
import { ClientFormComponent } from './client-form';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, GenericTableComponent, ClientFormComponent],
  templateUrl: './clients.html',
  styleUrl: './clients.css',
})
export class ClientsComponent implements OnInit {
  private apiService = inject(ApiService);
  private notificationService = inject(NotificationService);
  private route = inject(ActivatedRoute);

  clients: Client[] = [];
  filteredClients: Client[] = [];
  documentTypes: DocumentType[] = [];
  isLoading = false;
  editingClient: Client | null = null;
  showForm = false;
  private searchTerm = '';

  columns: TableColumn[] = [
    { key: 'fullName', label: 'Cliente', type: 'avatar', subKey: 'documentTypeName' },
    { key: 'documentNumberDisplay', label: 'Documento' },
    { key: 'correo', label: 'Correo electrónico' },
    { key: 'fechaNacimiento', label: 'Fecha nacimiento', type: 'date' },
  ];

  actions: TableAction[] = [
    { label: 'Editar', symbol: 'edit', action: 'edit' },
    { label: 'Eliminar', symbol: 'delete', action: 'delete', color: 'warn' },
  ];

  ngOnInit(): void {
    this.loadClients();
    this.loadDocumentTypes();

    // Atajo desde el resumen: /clients?nuevo=true
    if (this.route.snapshot.queryParamMap.get('nuevo')) {
      this.onCreate();
    }
  }

  loadClients(): void {
    this.isLoading = true;

    this.apiService.get<Client[]>('/api/clientes').subscribe({
      next: (data) => {
        this.clients = data;
        this.refreshClientDisplay();
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  loadDocumentTypes(): void {
    this.apiService.get<DocumentType[]>('/api/document-type').subscribe({
      next: (data) => {
        this.documentTypes = data;
        this.refreshClientDisplay();
      },
    });
  }

  onSearch(term: string): void {
    this.searchTerm = term.trim().toLowerCase();
    this.applyFilter();
  }

  private applyFilter(): void {
    if (!this.searchTerm) {
      this.filteredClients = this.clients;
      return;
    }

    this.filteredClients = this.clients.filter((c: any) =>
      [c.fullName, c.numeroDocumento, c.correo]
        .filter(Boolean)
        .some((v) => String(v).toLowerCase().includes(this.searchTerm))
    );
  }

  onCreate(): void {
    this.editingClient = null;
    this.showForm = true;
  }

  onEdit(row: any): void {
    this.editingClient = row as Client;
    this.showForm = true;
  }

  onDelete(row: any): void {
    const client = row as Client;
    if (confirm(`¿Está seguro de eliminar al cliente ${client.nombres} ${client.apellidos}?`)) {
      this.apiService.delete(`/api/clientes/${client.id}`).subscribe({
        next: () => {
          this.clients = this.clients.filter((c) => c.id !== client.id);
          this.applyFilter();
          this.notificationService.success('Cliente eliminado correctamente');
        },
      });
    }
  }

  onFormSubmit(data: ClientRequest): void {
    if (this.editingClient) {
      this.apiService.put<Client>(`/api/clientes/${this.editingClient.id}`, data).subscribe({
        next: (updated) => {
          const idx = this.clients.findIndex((c) => c.id === updated.id);
          if (idx >= 0) {
            this.clients[idx] = updated;
          }
          this.refreshClientDisplay();
          this.notificationService.success('Cliente actualizado correctamente');
          this.showForm = false;
        },
      });
    } else {
      this.apiService.post<Client>('/api/clientes', data).subscribe({
        next: (created) => {
          this.clients.push(created);
          this.refreshClientDisplay();
          this.notificationService.success('Cliente creado correctamente');
          this.showForm = false;
        },
      });
    }
  }

  onFormCancel(): void {
    this.showForm = false;
    this.editingClient = null;
  }

  private refreshClientDisplay(): void {
    this.clients = this.clients.map((c) => {
      const documentType = this.documentTypes.find((dt) => dt.id === c.tipoDocumentoId);

      return {
        ...c,
        documentNumberDisplay: `${documentType?.code ?? ''} ${c.numeroDocumento}`.trim(),
        documentTypeName: documentType?.name ?? '',
        fullName: `${c.nombres} ${c.apellidos}`,
      };
    });

    this.applyFilter();
  }

  onRowAction(event: { action: string; row: any }): void {
    switch (event.action) {
      case 'edit':
        this.onEdit(event.row);
        break;
      case 'delete':
        this.onDelete(event.row);
        break;
    }
  }
}