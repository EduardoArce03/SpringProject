import { Component } from '@angular/core';
import { UsersService } from '../services/users.service';
import { User } from '../models/Users';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {

  user : User = {
    id: 0,
    name: '',
    email: '',
    phone: '',
    username: '',
    lastname: '',
    password: '',
    address: '',
    role: ''
  }
  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.getUser();
  }

  getUser(){
    this.userService.getUser(4).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (error) => {
        console.log('Error', error);
      }
    });
  }
}
