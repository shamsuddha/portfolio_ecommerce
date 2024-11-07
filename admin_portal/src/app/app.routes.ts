import { Route } from '@angular/router';


export const routeList: Route[] = [

  { path: '', loadComponent: () => import('./module/index/IndexComp').then(m => m.IndexComp) },

  { path: 'login', loadComponent: () => import('./module/login/AdminLoginComp').then(m => m.AdminLoginComp) },

  {
    path: 'authenticated',
    loadComponent: () => import('./module/authenticated/AuthenticatedComp').then(m => m.AuthenticatedComp),
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./module/authenticated/dashboard/DashboardComp').then(m => m.DashboardComp)
      },     
    ]
  }

];
