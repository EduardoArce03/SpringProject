package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;
import com.example.demo.model.Image;
import com.example.demo.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	
	private final BookRepository bookRepository;

	private final ImageService imageService;

	public BookServiceImpl(BookRepository bookRepository, ImageService imageService) {
		this.bookRepository = bookRepository;
		this.imageService = imageService;
	}

	@Override
	public Book saveBook(Book book, MultipartFile file) throws IOException{
		// TODO Auto-generated method stub
		if(file != null && !file.isEmpty()) {
			Image image = imageService.uploadImage(file);
			book.setImage(image);
		}
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	} 

	@Override
	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> getBookById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public void deleteBook(Long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
		
	}

	@Override
	public Book updateBookImage( MultipartFile file, Book book) throws IOException {
		// TODO Auto-generated method stub
		if(book.getImage() != null) {
			imageService.deleteImage(book.getImage());
		}
		Image image = imageService.uploadImage(file);
		book.setImage(image);
		return bookRepository.save(book);
	}
}
