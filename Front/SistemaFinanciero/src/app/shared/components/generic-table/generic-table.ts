import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface TableColumn {
  key: string;
  label: string;
  type?: 'text' | 'number' | 'currency' | 'date' | 'datetime' | 'boolean' | 'avatar';
  subKey?: string; // texto secundario para type 'avatar'
}

export interface TableAction {
  label: string;
  icon?: string;   // emoji o texto (compatibilidad)
  symbol?: string; // nombre de Material Symbols, tiene prioridad sobre icon
  action: string;
  color?: 'primary' | 'warn' | 'accent';
}

@Component({
  selector: 'app-generic-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './generic-table.html',
  styleUrl: './generic-table.css',
})
export class GenericTableComponent {
  @Input() columns: TableColumn[] = [];
  @Input() data: any[] = [];
  @Input() actions: TableAction[] = [];
  @Input() isLoading: boolean = false;
  @Input() emptyMessage: string = 'No hay registros para mostrar';
  @Output() rowAction = new EventEmitter<{ action: string; row: any }>();

  trackByIdx(index: number): number {
    return index;
  }

  onAction(action: string, row: any): void {
    this.rowAction.emit({ action, row });
  }

  getInitials(value: string): string {
    if (!value) return '';
    const parts = String(value).trim().split(/\s+/);
    return (parts[0][0] + (parts[1]?.[0] ?? '')).toUpperCase();
  }
}