package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.dto.SaveBookRequest;
import com.example.demo.model.Book;
import com.example.demo.model.Category;

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

	//Obtener lista de libros por categoria
	Page<Book> getBooksByCategory(String name, Pageable pageable);

	List<Category> getCategories();

	Book addBook (SaveBookRequest bookDTO) throws Exception;

}
