package com.example.LibraryManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LibraryManagementSystem.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow,Integer>{

}
