package com.example.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.model.Borrow;
import com.example.LibraryManagementSystem.service.BorrowService;

@RestController
public class BorrowController {
	
	@Autowired
	private BorrowService BorrowService;
	
	 @PostMapping("borrow")
	 public Borrow borrowBook(int bookId,int memberId) {
		 return BorrowService.borrowBook(bookId, memberId);
	 }
	 
	 @GetMapping("borrows")
	 public List<Borrow> viewBorrows(){
		 return BorrowService.viewBorrows();
	 }
	 
	 @GetMapping("borrow/{id}")
	 public Borrow viewBorrowsById(@PathVariable int id) {
		 return BorrowService.viewBorrowsById(id);
	 }
	
	 @DeleteMapping("return/{id}")
	 public ResponseEntity<String> returnBook(@PathVariable int id) {
		 BorrowService.returnBook(id);
	     return ResponseEntity.ok("Book returned successfully.");
	 }
}
