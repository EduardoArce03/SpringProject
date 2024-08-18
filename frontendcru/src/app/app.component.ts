import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import { TopbarComponent } from "./layout/topbar/topbar.component";
import { FooterComponent } from "./footer/footer.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, ToastModule, TopbarComponent, TopbarComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontendcru';
}
