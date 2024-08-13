import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Book } from '../models/book';
import { BookServicesService } from '../services/book-services.service';
import { RouterModule } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, CardModule, RouterModule, ToastModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  books: Book[] = []

  constructor(private bookService : BookServicesService, private messageService : MessageService) { }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(){
    this.bookService.getBooks().subscribe((data) => {
      this.books = data;
      console.log(this.books);
    });
  }

  deleteBook(id: number){
    this.bookService.deleteBook(id).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success', summary: 'Exito', detail: 'Libro eliminado correctamente'
        });
        this.getBooks();
        
      },
      error: () => {
        this.messageService.add({severity: 'error', summary: 'Error', detail: 'Libro no encontrado'});
        console.log('Error al eliminar el libro');
      }
    });
  }
}
