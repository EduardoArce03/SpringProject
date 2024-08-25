import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { ForgotpasswordService } from '../../services/forgotpassword.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { User } from '../../models/Users';

@Component({
  selector: 'app-forgotpassword',
  standalone: true,
  imports: [CardModule, InputTextModule, ButtonModule, FormsModule, ReactiveFormsModule, ToastModule],
  templateUrl: './forgotpassword.component.html',
  styleUrl: './forgotpassword.component.scss'
})
export class ForgotpasswordComponent {

  email: string = '';
  user?: User;

  constructor(private forgotPassService : ForgotpasswordService, private messageService : MessageService, private router : Router) { }

  verifyEmail(){
    this.forgotPassService.requestPasswordChange(this.email).subscribe({
      next: (data: User) => {
        console.log('Correo enviado')
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Email enviado' });
        this.user = data;
        console.log(this.user.email);
        this.router.navigate(['/verify', this.user.email]);
      },
      error: (error) => {
        console.log('Error', error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Email no enviado, revisa que este correcto' });
      }
    });
  }

}
