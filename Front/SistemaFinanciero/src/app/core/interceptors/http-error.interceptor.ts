import { Injectable, inject } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NotificationService } from '../Services/notification.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  private notificationService = inject(NotificationService);

  intercept(req: HttpRequest<unknown>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        let message = 'Ocurrió un error inesperado';

        if (error.error instanceof ErrorEvent) {
          message = `Error: ${error.error.message}`;
        } else {
          switch (error.status) {
            case 0:
              message = 'No se pudo conectar al servidor. Verifique que el backend esté en ejecución.';
              break;
            case 400:
              message = error.error?.message || 'Solicitud inválida.';
              break;
            case 401:
              message = 'No autorizado. Inicie sesión nuevamente.';
              break;
            case 403:
              message = 'No tiene permisos para realizar esta acción.';
              break;
            case 404:
              message = 'Recurso no encontrado.';
              break;
            case 409:
              message = error.error?.message || 'Conflicto en la operación.';
              break;
            case 500:
              message = 'Error interno del servidor.';
              break;
            default:
              message = error.error?.message || `Error ${error.status}`;
          }
        }

        this.notificationService.error(message);
        return throwError(() => error);
      })
    );
  }
}
