import { Component } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { BookServicesService } from '../services/book-services.service';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { TabMenuModule } from 'primeng/tabmenu';
import { MenuItem } from 'primeng/api';
import { AnimateOnScrollModule } from 'primeng/animateonscroll';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CarouselModule, TagModule, ButtonModule, TabMenuModule, AnimateOnScrollModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {
  
  books: any[] = [];
  responsiveOptions: any[] | undefined;
  images: any[] = ['home-img1.jpg', 'home-img2.png'];
  items: MenuItem[] =[];
  tagSeverity: 'success' | 'secondary' | 'info' | 'warning' | 'danger' | 'contrast' = 'success';

  
  constructor(private bookService: BookServicesService) { }

  ngOnInit() {
      this.responsiveOptions = [
        {
          breakpoint: '3024px',
          numVisible: 3,
          numScroll: 3
        },
        {
          breakpoint: '968px',
          numVisible: 2,
          numScroll: 2
        },
        {
          breakpoint: '821px',
          numVisible: 1,
          numScroll: 1
        }
      ];
      this.getBooks();

      this.items = [
        {label: 'Novedades', icon: 'pi pi-fw pi-home'},
        {label: 'Ver todos', icon: 'pi pi-fw pi-calendar', routerLink: '/book'},
      ];
    }

    getBooks(){
      this.bookService.getBooks().subscribe((data) => {
        this.books = data;
      });
    }

    getStockStatus(stock: number): string {
      if (stock > 5) return 'In Stock';
      if (stock > 0) return 'Low Stock';
      return 'Out of Stock';
    }

    getStockSeverity(stock: number): 'success' | 'warning' | 'danger' {
      if (stock > 5) return 'success';
      if (stock > 0) return 'warning';
      return 'danger';
    }


}
