package com.example.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.model.Member;
import com.example.LibraryManagementSystem.repository.MemberRepository;
import java.util.List;

@Service
public class MemberService {

	 @Autowired
	    private MemberRepository MemberRepository;

	    public List<Member> viewMembers() {
	        return MemberRepository.findAll();
	    }

	    public Member viewMemberById(int id) {
	        return MemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found for Id: " +id));
	    }

	    public Member saveMember(Member member) {
	        return MemberRepository.save(member);
	    }
	    
	    public Member updateMember(int memberId, Member updatedMember) {
	        Member existingMember = MemberRepository.findById(memberId)
	        		.orElseThrow(() -> new ResourceNotFoundException("Member not Found with Id: "+memberId));
	        existingMember.setName(updatedMember.getName());
	        existingMember.setEmail(updatedMember.getEmail());
	        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
	        existingMember.setDateOfMembership(updatedMember.getDateOfMembership());
	        return MemberRepository.save(existingMember);
	    }

	    public void deleteMember(int id) {
	    	if(!MemberRepository.existsById(id)) {
	    		throw new ResourceNotFoundException("Member with ID " + id + " does not exist.");
	    	}
	        MemberRepository.deleteById(id);
	    }
}
