package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.SaveBookRequest;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.services.BookServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestPart("book") Book book, @RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Received book: " + book);
            Book savedBook = bookServiceImpl.saveBook(book, file);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Book> addBook(@RequestBody SaveBookRequest bookDTO){
        try {
            Book book = bookServiceImpl.addBook(bookDTO);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
        try {
            Book updatedBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar imagen de libro
    @PutMapping("/{id}/image")
    public ResponseEntity<Book> updateBookImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if (book.isPresent()) {
            Book updatedBook = bookServiceImpl.updateBookImage(file, book.get());
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> books = bookServiceImpl.getBooks();
        List<BookDTO> listBooks = bookServiceImpl.convertToBookDtoList(books);
        return new ResponseEntity<>(listBooks,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if (book.isPresent()){
            bookServiceImpl.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<Category> categories = bookServiceImpl.getCategories();
        List<CategoryDTO> listCategories = bookServiceImpl.convertToCategoryDtoList(categories);
        return new ResponseEntity<>(listCategories, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}/orden/{orden}/{pagina}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable("categoria") String category, @PathVariable("orden")String orden, @PathVariable("pagina")int pagina) throws Exception{
        try {
            Pageable pageable = PageRequest.of(pagina, 10, Sort.by(orden.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "title"));
            return new ResponseEntity<>(bookServiceImpl.getBooksByCategory(category, pageable).getContent(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
}
