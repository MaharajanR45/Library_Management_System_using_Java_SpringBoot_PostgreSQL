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

import com.example.LibraryManagementSystem.model.Member;
import com.example.LibraryManagementSystem.service.*;

@RestController
public class MemberController {
    @Autowired
    private MemberService MemberService;
    
    @PostMapping("/members/add")
    public ResponseEntity<Member> addMembers(@RequestBody Member member) {
        Member m = MemberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @GetMapping("/members")
    public List<Member> viewAllMembers() {
        return MemberService.viewMembers();
    }

    @GetMapping("member/{id}")
    public Member viewMemberById(@PathVariable int id) {
        return MemberService.viewMemberById(id);
    }
    
    @PutMapping("member/{id}")
    public Member updateMember(@PathVariable int id,@RequestBody Member member) {
    	return MemberService.updateMember(id, member);
    }


    @DeleteMapping("member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable int id,@RequestBody Member member) {
        MemberService.deleteMember(id);
        return ResponseEntity.ok("Member ID " +id+ " is deleted successfully");
    }
}
