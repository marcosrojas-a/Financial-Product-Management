import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface TableColumn {
  key: string;
  label: string;
  type?: 'text' | 'number' | 'currency' | 'date' | 'datetime' | 'boolean';
}

export interface TableAction {
  label: string;
  icon: string;
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
}
