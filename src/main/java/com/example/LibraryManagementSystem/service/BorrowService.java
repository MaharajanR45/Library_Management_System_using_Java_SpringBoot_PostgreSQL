package com.example.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Borrow;
import com.example.LibraryManagementSystem.model.Member;
import com.example.LibraryManagementSystem.repository.*;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository BorrowRepository;

    @Autowired
    private BookRepository BookRepository;

    @Autowired
    private MemberRepository MemberRepository;

    public Borrow borrowBook(int bookId, int memberId) {
        Book book = BookRepository.findById(bookId)
        		.orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        if (book.getAvailableCopies() <= 0) {
            throw new ResourceNotFoundException("Book Unavailable for Borrowing");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        BookRepository.save(book);

        Member member = MemberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setMember(member);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(15));

        return BorrowRepository.save(borrow);
    }
    
    public List<Borrow> viewBorrows(){
    	return BorrowRepository.findAll();
    }
    
    public Borrow viewBorrowsById(int id) {
    	return BorrowRepository.findById(id).
    			orElseThrow(() -> new ResourceNotFoundException("Not found a match with this Id: " +id));
    }

    public void returnBook(int id) {
        Borrow borrow = BorrowRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("ID" + id + "not present"));

        Book book = borrow.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        BookRepository.save(book);

        BorrowRepository.deleteById(id);
    }
}
