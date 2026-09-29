import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MenuItem, MENU_ITEMS } from '../../../core/Menu-config/menu.config';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class HeaderComponent {
  title = 'Sistema Financiero';
  menuItems: MenuItem[] = MENU_ITEMS;
}
