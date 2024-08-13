import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormBookComponent } from './form-book/form-book.component';
import { LoginComponent } from './auth/login/login.component';

export const routes: Routes = [

    {
        path: 'login',
        component: LoginComponent,
        title: 'Login'
    },
    {
        path: '', 
        component: HomeComponent,
        title: 'Home',
        
    },
    {
        path: 'book-form/:id',
        component: FormBookComponent,
        title: 'Book Form'
    },
    {
        path: '**',
        redirectTo: '',
        pathMatch: 'full'
    },

    //Path of logins MmM
    
];
