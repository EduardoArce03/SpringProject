import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { BookServicesService } from '../services/book-services.service';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { FloatLabelModule } from 'primeng/floatlabel';
import { FileSelectEvent } from 'primeng/fileupload';
import { FileUploadModule } from 'primeng/fileupload';
import { MultiSelectModule } from 'primeng/multiselect';
import { Category } from '../models/Category';


@Component({
  selector: 'app-form-book',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, ButtonModule, ToastModule, RouterModule,
    InputNumberModule, CardModule, InputTextModule, FloatLabelModule, FileUploadModule, MultiSelectModule],
  templateUrl: './form-book.component.html',
  styleUrl: './form-book.component.scss'
})
export class FormBookComponent {

  form!: FormGroup;
  isSaveInProgress: boolean = false;
  edit: boolean = false;
  selectedFile: File | null = null;
  categories: any[] = [];
  selectedCategories: any[] = [];

  constructor(private fb: FormBuilder, private bookService: BookServicesService, private activatedRoute: ActivatedRoute,
    private messageService: MessageService, private router: Router) {
    this.form = this.fb.group({
      id: [null],
      title: ['', Validators.required],
      author: ['', Validators.required],
      pages: [1, [Validators.required, Validators.min(1)]],
      price: [0, [Validators.required, Validators.min(0)]],
      stock: [0, [Validators.required, Validators.min(1)]],
      image: [],
      selectedCategories: [[], Validators.required]
    });
  }

  ngOnInit(): void {
    this.getCategories();
    //-------//
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id != 'new' && id !== null) {
      this.edit = true
      this.getBookById(Number(id));
    }
  }

  onFileSelected(event: FileSelectEvent) {
    this.selectedFile = event.files[0];
  }

  changeImage(event: FileSelectEvent) {
    this.selectedFile = event.files[0];
    if (!this.selectedFile) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccione una imagen' });
      return;
    }
    this.bookService.updateBookImage(this.form.value.id, this.selectedFile).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Imagen actualizada con éxito' });
        this.router.navigateByUrl('/');
      },
      error: () => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error al actualizar la imagen' });
      }
    });
  }

  getBookById(id: number) {
    this.bookService.getBookById(id).subscribe({
      next: (foundBook) => {
        this.form.patchValue(foundBook);
      },
      error: () => {
        console.log('Libro no encontrado');
        console.log('IDDDDDDDDDDD', id);
        this.messageService.add({
          severity: 'error', summary: 'Error', detail: 'Libro no encontrado'
        });
        //this.router.navigateByUrl('/');
      }
    });
  }

  createBook() {
    if (this.form.invalid) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Revise los campos y intente nuevamente ' });
      return;
    }

    if (this.selectedFile === null) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccione una imagen' });
      return;
    }
    // Extrae los valores del formulario
    const formValues = this.form.value;
    const selectedCategoryIds = formValues.selectedCategories;

    // Construye el objeto para enviar al backend
    const bookData = {
      ...formValues,
      categoryIds: selectedCategoryIds
    };

    this.bookService.createBook(bookData, this.selectedFile).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Libro creado con éxito' });
        this.router.navigateByUrl('/');
      },

      error: () => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error al crear el libro' });
      }
    });
  }

  updateBook() {
    if (this.form.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Revise los campos e intente nuevamente',
      });
      return;
    }
    this.isSaveInProgress = true;
    this.bookService.updateBook(this.form.value).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Guardado',
          detail: 'Libro actualizado correctamente',
        });
        this.isSaveInProgress = false;
        this.router.navigateByUrl('/');
      },
      error: (error) => {
        console.log('Error', error);
        this.isSaveInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Revise los campos e intente nuevamente',
        });
      },
    });
  }

  getCategories() {
    this.bookService.getCategories().subscribe({
      next: (categories: Category[]) => {

        //this.categories = categories;
        this.categories = categories
      },
      error: () => {
        console.log('Error al obtener las categorías');
      }
    });
  }



}
