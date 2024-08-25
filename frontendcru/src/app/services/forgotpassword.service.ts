import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ForgotPassword } from '../models/ForgotPassword';
import { User } from '../models/Users';
import { ChangePassword } from '../models/ChangePassword';

@Injectable({
  providedIn: 'root'
})
export class ForgotpasswordService {

  private baseUrl = 'http://localhost:8080/forgot';

  constructor(private http: HttpClient) { }

  requestPasswordChange(email: string): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/verifyEmail/${email}`, {});
  }

  verifyCode(code: number, email:string): Observable<ForgotPassword> {
    return this.http.post<ForgotPassword>(`${this.baseUrl}/verifyOtp/${code}/${email}`, {});
  }

  createPassword(email: string, changePassword: ChangePassword): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/resetPassword/${email}`, changePassword, {
      responseType: 'text' as 'json'
    });
  }

}
