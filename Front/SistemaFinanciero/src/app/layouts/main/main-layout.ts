import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header';
import { SidebarComponent } from './sidebar/sidebar';
import { SpinnerComponent } from '../../shared/components/spinner/spinner';
import { LoadingService } from '../../core/Services/loading.service';
import { SnackbarComponent } from '../../shared/components/snackbar/snackbar';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent, SidebarComponent, SpinnerComponent, SnackbarComponent],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css',
})
export class MainLayoutComponent {
  private loadingService = inject(LoadingService);
  loading$ = this.loadingService.loading$;

}

