import { Route } from '@angular/router';


export const routeList: Route[] = [

  { path: '', loadComponent: () => import('./module/index/IndexComp').then(m => m.IndexComp) },

  // { path: 'login', loadComponent: () => import('./module/login/AdminLoginComp').then(m => m.AdminLoginComp) },

  { path: '', loadComponent: () => import('./module/index/IndexComp').then(m => m.IndexComp) },

  {
    path: 'authenticated',
    loadComponent: () => import('./module/authenticated/AuthenticatedComp').then(m => m.AuthenticatedComp),
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./module/authenticated/dashboard/DashboardComp').then(m => m.DashboardComp)
      },
      {
        path: 'category',
        loadComponent: () => import('./module/authenticated/category_setup/CategorySetupComp').then(m => m.CategorySetupComp)
      },
      {
        path: 'sub-category',
        loadComponent: () => import('./module/authenticated/subcategory_setup/SubCategorySetupComp').then(m => m.SubCategorySetupComp)
      },
      {
        path: 'brand',
        loadComponent: () => import('./module/authenticated/brand_setup/BrandSetupComp').then(m => m.BrandSetupComp)
      },     
      {
        path: 'model',
        loadComponent: () => import('./module/authenticated/model_setup/ModelSetupComp').then(m => m.ModelSetupComp)
      },
      {
        path: 'product',
        loadComponent: () => import('./module/authenticated/product_setup/ProductSetupComp').then(m => m.ProductSetupComp)
      },
      {
        path: 'unit',
        loadComponent: () => import('./module/authenticated/unit_setup/UnitSetupComp').then(m => m.UnitSetupComp)
      },

    ]
  }

];
