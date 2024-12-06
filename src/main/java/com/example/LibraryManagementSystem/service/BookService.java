package com.example.LibraryManagementSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.*;

@Service
public class BookService {
	
	@Autowired
	private BookRepository BookRepository;
	
	public Book addBook(Book book)
	{
		return BookRepository.save(book);
	}
	
	public List<Book> findAllBooks()
	{
		return BookRepository.findAll();
	}
	
	public Book updateBook(int bookId, Book updatedBook) {
        Book existingBook = BookRepository.findById(bookId)
        		.orElseThrow(() -> new ResourceNotFoundException("Book not Found with Id: " +bookId));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
        return BookRepository.save(existingBook);
    }

	
	public Book findBookByIsbn(Long isbn) {
        return BookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }
	
	public Book findBookById(int bookId) {
		 return BookRepository.findById(bookId)
	                .orElseThrow(() -> new ResourceNotFoundException("Book not found with Id: " + bookId));
	}
	
	public void deleteBook(int id)
	{
		 if (!BookRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Book with ID " + id + " does not exist.");
	        }
		BookRepository.deleteById(id);
	}

}
