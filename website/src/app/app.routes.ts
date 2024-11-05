import { Routes } from '@angular/router';
import { LayoutComp } from './module/layout/LayoutComp';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComp,
        children: [
            {
                path: 'home',
                loadComponent: () => import('./module/home/HomeComp').then(m => m.HomeComp)
            },
            {
                path: 'single-product',
                loadComponent: () => import('./module/single_product/SingleProductComp').then(m => m.SingleProductComp)
            },
            {
                path: 'category',
                loadComponent: () => import('./module/category/CategoryComp').then(m => m.CategoryComp)
            },
            {
                path: 'checkout',
                loadComponent: () => import('./module/checkout/CheckoutComp').then(m => m.CheckoutComp)
            },
            {
                path: 'cart',
                loadComponent: () => import('./module/cart/CartComp').then(m => m.CartComp)
            },
            {
                path: 'compare',
                loadComponent: () => import('./module/compare/CompareComp').then(m => m.CompareComp)
            },
            {
                path: 'login',
                loadComponent: () => import('./module/login/LoginComp').then(m => m.LoginComp)
            },
            {
                path: 'registration',
                loadComponent: () => import('./module/registration/RegistrationComp').then(m => m.RegistrationComp)
            },
            {
                path: 'search',
                loadComponent: () => import('./module/search/SearchComp').then(m => m.SearchComp)
            },
            {
                path: 'shop',
                loadComponent: () => import('./module/shop/ShopComp').then(m => m.ShopComp)
            }
        ]
    }
];
