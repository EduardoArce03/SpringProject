import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookServicesService {

  private apiUrl = 'http://localhost:8080/book';

  constructor(private http : HttpClient) { }

  getBooks():Observable<Book[]>{
    return this.http.get<Book[]>(this.apiUrl);
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  createBook(book: Book, image: File): Observable<Book>{
    const formData = new FormData();
    formData.append('book', new Blob([JSON.stringify(book)], { type: 'application/json' }));
    formData.append('image', image);
    
    return this.http.post<Book>(this.apiUrl, book);
  }

  deleteBook(id: number): Observable<Book>{
    return this.http.delete<Book>(`${this.apiUrl}/${id}`);
  }

  updateBook(book: Book): Observable<Book>{
    return this.http.put<Book>(this.apiUrl, book);
  }

  updateBookImage(id: number, image: File): Observable<Book> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http.put<Book>(`${this.apiUrl}/${id}/image`, formData);
  }
}
