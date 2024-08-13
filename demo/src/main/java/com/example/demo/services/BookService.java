package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;

public interface BookService {
	
	//Guardar Libro
	Book saveBook(Book book, MultipartFile file) throws Exception;
	
	//Obtener lista de libros
	List<Book> getBooks();
	
	//Obtener UN Libro
	Book updateBook(Book book);
	
	//Obtener libro por ID
	Optional<Book> getBookById(Long id);
	
	//Eliminar Libro
	void deleteBook(Long id);

	//Actualizar imagen de libro
	Book updateBookImage( MultipartFile file, Book book ) throws IOException;
}
