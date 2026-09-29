import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export type NotificationType = 'success' | 'error' | 'warning' | 'info';

export interface NotificationMessage {
  message: string;
  type: NotificationType;
  duration?: number;
}

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private notificationSubject = new BehaviorSubject<NotificationMessage | null>(null);

  notifications$ = this.notificationSubject.asObservable();

  show(message: string, type: NotificationType = 'info', duration = 5000): void {
    this.notificationSubject.next({ message, type, duration });
  }

  success(message: string, duration = 4000): void {
    this.show(message, 'success', duration);
  }

  error(message: string, duration = 5000): void {
    this.show(message, 'error', duration);
  }

  warning(message: string, duration = 4000): void {
    this.show(message, 'warning', duration);
  }

  info(message: string, duration = 4000): void {
    this.show(message, 'info', duration);
  }

  clear(): void {
    this.notificationSubject.next(null);
  }
}
