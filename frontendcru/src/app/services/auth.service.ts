import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../models/loginRequest';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/authResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  

  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) { }

  login(user: LoginRequest) : Observable <AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, user);
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    // Aquí puedes verificar si el token existe y es válido
    const token = this.getToken();
    if (!token) {
      return false;
    }
    return !!token;
  }
}
