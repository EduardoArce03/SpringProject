import { Component } from '@angular/core';
import { PasswordModule } from 'primeng/password';
import { CheckboxModule } from 'primeng/checkbox';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/loginRequest';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [PasswordModule, CheckboxModule, FormsModule, ReactiveFormsModule, ToastModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  user: LoginRequest = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router, private messageService : MessageService) { }

  login(user: LoginRequest) {
    this.authService.login(user).subscribe({
      next: (data) => {
        localStorage.setItem('token', data.token);
        localStorage.setItem('user', data.userId.toString());
        console.log('Logeado', data.token);
        this.authService.saveToken(data.token);
        this.router.navigate(['']);
        this.messageService.add({severity: 'success', summary: 'Exito', detail: 'Logeado correctamente'});
      },
      error: (error) => {
        console.log('Error', error);
        this.messageService.add({severity: 'error', summary: 'Error', detail: 'Usuario o contraseña incorrectos'});
      }
    });
  }

  logout() {
    try {
      this.authService.logout();
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Sesión cerrada correctamente' });
      this.router.navigate(['']);
    } catch (error) {
      console.error('Error durante el cierre de sesión', error);
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Hubo un problema al cerrar la sesión' });
    }
  }


}