import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layouts/main/main-layout';
import { DashboardComponent } from './features/dashboard/dashboard';
import { ClientsComponent } from './features/clients/clients';
import { AccountsComponent } from './features/accounts/accounts';
import { TransactionsComponent } from './features/transactions/transactions';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'clients', component: ClientsComponent },
      { path: 'accounts', component: AccountsComponent },
      { path: 'transactions', component: TransactionsComponent },
    ],
  },
  { path: '**', redirectTo: 'dashboard' },
];
