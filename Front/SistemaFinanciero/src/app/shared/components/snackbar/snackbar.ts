import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../../core/Services/notification.service';
import type { NotificationMessage } from '../../../core/Services/notification.service';
import { formatDateTime } from '../../../core/helpers/date.helper';

@Component({
  selector: 'app-snackbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './snackbar.html',
  styleUrl: './snackbar.css',
})
export class SnackbarComponent {
  notificationService = inject(NotificationService);

  currentNotification: NotificationMessage | null = null;
  visible = false;
  timeoutId: any;

  constructor() {
    this.notificationService.notifications$.subscribe((notification) => {
      this.show(notification);
    });
  }

  show(notification: NotificationMessage | null): void {
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
    this.currentNotification = notification;
    if (notification) {
      this.visible = true;
      this.timeoutId = setTimeout(() => {
        this.visible = false;
        setTimeout(() => {
          this.currentNotification = null;
          this.notificationService.clear();
        }, 300);
      }, notification.duration ?? 5000);
    }
  }

  getTime = formatDateTime;
}
