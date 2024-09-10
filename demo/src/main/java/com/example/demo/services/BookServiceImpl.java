package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.SaveBookRequest;
import com.example.demo.model.Book;
import com.example.demo.model.BookCategory;
import com.example.demo.model.Category;
import com.example.demo.model.Image;
import com.example.demo.repositories.BookCategoryRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.dto.CategoryDTO;
@Service
public class BookServiceImpl implements BookService {
	
	
	private final BookRepository bookRepository;

	private final ImageService imageService;

	private final CategoryRepository categoryRepository;

	private final BookCategoryRepository bookCategoryRepository;

	public BookServiceImpl(BookRepository bookRepository, ImageService imageService, CategoryRepository categoryRepository, BookCategoryRepository bookCategoryRepository) {
		this.bookRepository = bookRepository;
		this.imageService = imageService;
		this.categoryRepository = categoryRepository;
		this.bookCategoryRepository = bookCategoryRepository;
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

	@Override
	public Page<Book> getBooksByCategory(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return bookRepository.findByCategoryName(name, pageable);
	}

	@Override
	public Book addBook(SaveBookRequest bookDTO) throws IOException {
		// TODO Auto-generated method stub

		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setPages(bookDTO.getPages());
		book.setPrice(bookDTO.getPrice());
		book.setStock(bookDTO.getStock());
			
		

		bookRepository.save(book);

		if (bookDTO.getCategoryIds() !=null) {
			for(Long categoryId : bookDTO.getCategoryIds()){
				Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
				if (categoryOptional.isPresent()) {
					Category category = categoryOptional.get();
					BookCategory bookCategory = new BookCategory();
					bookCategory.setBook(book);
					bookCategory.setCategory(category);
					bookCategoryRepository.save(bookCategory);
				}
			}
		}
		return book;
	}

	

	@SuppressWarnings("unused")
	public List<BookDTO> convertToBookDtoList(List<Book> books) {
		return books.stream().map(book -> {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(book.getId());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setPages(book.getPages());
			bookDTO.setStock(book.getStock());
			bookDTO.setPrice(book.getPrice());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setImage(book.getImage());
			bookDTO.setCategories(book.getBookCategories().stream()
				.map(bc -> bc.getCategory().getName())
				.collect(Collectors.toList()));
			return bookDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public List<CategoryDTO> convertToCategoryDtoList(List<Category> categories) {
		return categories.stream().map(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			return categoryDTO;
		}).collect(Collectors.toList());
	}

	
}
