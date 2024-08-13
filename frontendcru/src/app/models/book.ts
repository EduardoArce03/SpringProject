import { BookImage } from "./bookImage";


export interface Book {
    id: number;
    title: string;
    author: string;
    pages: number;
    price: number;
    image?: BookImage;
}