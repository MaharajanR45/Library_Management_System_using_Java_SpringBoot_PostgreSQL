package com.example.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.*;

@RestController
public class BookController {
	
	@Autowired
	private BookService BookService;
	
	@PostMapping("books/add")
	public ResponseEntity<Book> saveBook(@RequestBody Book book)
	{
		Book b = BookService.addBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(b);
	}
	
	@GetMapping("books")
	public List<Book> viewBooks()
	{
		return BookService.findAllBooks();
	}
	
	@GetMapping("book/isbn/{isbn}")
    public Book findBookByIsbn(@PathVariable Long isbn) {
        return BookService.findBookByIsbn(isbn);
    }
	
	@GetMapping("book/id/{id}")
    public Book findBookById(@PathVariable int id) {
        return BookService.findBookById(id);
    }
	
	@PutMapping("book/{id}")
	public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        return BookService.updateBook(id, book);
    }
	
	@DeleteMapping("book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		BookService.deleteBook(id);
	    return ResponseEntity.ok("Book ID " +id+ " is deleted successfully");
	    }

}
