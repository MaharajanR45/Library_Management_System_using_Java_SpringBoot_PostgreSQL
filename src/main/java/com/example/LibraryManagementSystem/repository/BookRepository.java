package com.example.LibraryManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagementSystem.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
	
	 Optional<Book> findByIsbn(Long isbn);
}
