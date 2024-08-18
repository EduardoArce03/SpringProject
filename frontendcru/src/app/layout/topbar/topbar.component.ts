import { Component } from '@angular/core';
import { ToolbarModule } from 'primeng/toolbar';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { UsersService } from '../../services/users.service';
import { User } from '../../models/Users';
import { MenuModule } from 'primeng/menu';
import { AuthService } from '../../services/auth.service';
import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { Router } from '@angular/router';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { BadgeModule } from 'primeng/badge';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [ToolbarModule, AvatarGroupModule, AvatarModule, MenuModule, ButtonModule, OverlayPanelModule, BadgeModule],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.scss'
})
export class TopbarComponent {

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
items: MenuItem[]|undefined;
avatar: any;


  constructor(private userService: UsersService, public authService: AuthService, private router:Router) { }

  ngOnInit() {
    this.getUser();
    this.items = [
      { label: 'Profile', icon: 'pi pi-user', command: () => this.router.navigate(['/profile']) },
      { label: 'Settings', icon: 'pi pi-cog' },
      { label: 'Logout', icon: 'pi pi-sign-out', command: () => this.logout() }
    ];
    this.getUser();
    this.isLogged();
  }

  getUser(){
    this.userService.getUser(4).subscribe({
      next: (data) => {
        console.log('User', data);
        this.user = data;
      },
      error: (error) => {
        console.log('Error', error);
      }
    });


  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isLogged(){
    return this.authService.isLoggedIn();
  }



}
