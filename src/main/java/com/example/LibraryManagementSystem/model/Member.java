package com.example.LibraryManagementSystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
	
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfMembership;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfMembership() {
		return dateOfMembership;
	}

	public void setDateOfMembership(LocalDate dateOfMembership) {
		this.dateOfMembership = dateOfMembership;
	}
}
