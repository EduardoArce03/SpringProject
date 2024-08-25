import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormBookComponent } from './form-book/form-book.component';
import { LoginComponent } from './auth/login/login.component';
import { TopbarComponent } from './layout/topbar/topbar.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ProfileComponent } from './profile/profile.component';
import { ForgotpasswordComponent } from './forgot/forgotpassword/forgotpassword.component';
import { EmailVerificationComponent } from './forgot/email-verification/email-verification.component';
import { CreatePasswordComponent } from './forgot/create-password/create-password.component';


export const routes: Routes = [

    {
        path: 'login',
        component: LoginComponent,
        title: 'Login'
    },
    {
        path: 'book', 
        component: HomeComponent,
        title: 'book',
        
    },
    {
        path: 'book-form/:id',
        component: FormBookComponent,
        title: 'Book Form'
    },

    {
        path: 'home',
        component: HomepageComponent,
        title: 'home'
    },

    {
        path: 'profile',
        component: ProfileComponent,
        title: 'Profile'
    },
    {
        path: 'forgot',
        component: ForgotpasswordComponent,
        title: 'Forgot Password'
    },

    {
        path: 'verify/:email',
        component: EmailVerificationComponent,
        title: 'Email Verification'
    },

    {
        path: 'change-password',
        component: CreatePasswordComponent,
        title: 'Create Password'
    },

    
    
];
