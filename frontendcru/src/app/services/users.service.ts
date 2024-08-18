import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../models/loginRequest';
import { User } from '../models/Users';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private apiUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) { }

  getUser(id: number) : Observable<User> { 
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }
}
