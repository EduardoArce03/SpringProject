import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputNumberModule } from 'primeng/inputnumber';
import { ForgotpasswordService } from '../../services/forgotpassword.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ForgotPassword } from '../../models/ForgotPassword';
import { ButtonModule } from 'primeng/button';
import { InputOtpModule } from 'primeng/inputotp';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-email-verification',
  standalone: true,
  imports: [InputNumberModule, FormsModule, ButtonModule, InputOtpModule, CommonModule],
  templateUrl: './email-verification.component.html',
  styleUrl: './email-verification.component.scss'
})
export class EmailVerificationComponent {

  value!: number;
  email: string = '';
  forgotPassword?: ForgotPassword;

  constructor(private forgotService: ForgotpasswordService, private route : ActivatedRoute, private router : Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      console.log('EmailENRUTADO:', params['email']);
      this.email = params['email'];
    });
  }

  verifyCode() {
    const verificationCode = this.value;
    console.log('Verification Code:', verificationCode);
    this.forgotService.verifyCode(Number(verificationCode), this.email).subscribe({
      next: (data : ForgotPassword) => {
        this.forgotPassword = data;
        console.log(this.forgotPassword.user.name);
        this.router.navigate(['/change-password']);
      },
      error: (error) => {
        console.log('Error', error);
      }
    });
  }

  verifyEmail() {
    this.forgotService.requestPasswordChange(this.email).subscribe({
      next: (data) => {
        console.log('Data', data);
      },
      error: (error) => {
        console.log('Error', error);
      }
    });
  }
}
