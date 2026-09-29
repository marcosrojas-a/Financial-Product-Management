export interface MenuItem {
  label: string;
  icon: string;
  route: string;
  roles?: string[];
}

export const MENU_ITEMS: MenuItem[] = [
  { label: 'Resumen', icon: 'dashboard', route: '/Resumen' },
  { label: 'Clientes', icon: 'people', route: '/clients' },
  { label: 'Cuentas', icon: 'account_balance', route: '/accounts' },
  { label: 'Transacciones', icon: 'receipt_long', route: '/transactions' },
];
