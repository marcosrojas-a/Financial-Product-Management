import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SnackbarComponent } from './shared/components/snackbar/snackbar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SnackbarComponent],
  template: `
    <router-outlet />
    <app-snackbar />
  `,
  styles: [],
})
export class App {
  protected readonly title = signal('SistemaFinanciero');
}
