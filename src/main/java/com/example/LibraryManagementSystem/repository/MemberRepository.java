package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LibraryManagementSystem.model.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {

}
