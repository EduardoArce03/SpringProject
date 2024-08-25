import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ForgotpasswordService } from '../../services/forgotpassword.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ChangePassword } from '../../models/ChangePassword';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-create-password',
  standalone: true,
  imports: [ButtonModule, InputTextModule, ReactiveFormsModule, ToastModule],
  templateUrl: './create-password.component.html',
  styleUrl: './create-password.component.scss'
})
export class CreatePasswordComponent {

  email: string = '';
  passwordForm!: FormGroup;

  constructor(private forgotService : ForgotpasswordService, private route : ActivatedRoute, private fb : FormBuilder, private messageService : MessageService ) { }

  ngOnInit() {  
    this.route.params.subscribe(params => {
      this.email = params['email'];
    });

    this.passwordForm = this.fb.group({
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    if(this.passwordForm.valid) {
      console.log('Formulario valido');
      const changePassword : ChangePassword = this.passwordForm.value;
      this.forgotService.createPassword(this.email, changePassword).subscribe({
        next: (data) => {
          console.log('Data', data);
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Contraseña cambiada' });
        },
        error: (error) => {
          console.log('Error', error);
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Ha ocurrido un error ' });
        }
      });
    }
  }




}
