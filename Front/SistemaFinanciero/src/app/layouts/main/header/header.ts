import { Component, DestroyRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { filter, startWith } from 'rxjs';
import { MenuItem, MENU_ITEMS } from '../../../core/Menu-config/menu.config';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class HeaderComponent {
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);

  appName = 'Prueba Financiera';
  appSubtitle = 'Gestión bancaria';
  menuItems: MenuItem[] = MENU_ITEMS;

  today = this.formatToday();
  pageTitle = '';

  constructor() {
    this.router.events
      .pipe(
        filter((event) => event instanceof NavigationEnd),
        startWith(null),
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe(() => {
        this.pageTitle = this.resolveTitle(this.router.url);
      });
  }

  private resolveTitle(url: string): string {
    const match = this.menuItems.find((item) => url.startsWith(item.route));
    return match?.label ?? 'Resumen';
  }

  private formatToday(): string {
    const text = new Date().toLocaleDateString('es-CO', {
      weekday: 'long',
      day: 'numeric',
      month: 'long',
      year: 'numeric',
    });
    return text.charAt(0).toUpperCase() + text.slice(1);
  }
}