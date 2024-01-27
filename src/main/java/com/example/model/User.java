package com.example.model;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String fullName;

	@Column
	private Date dateOfBirth;

	@Column(nullable = false, unique = true)
	private String emailId;

	@Column
	private String phoneNumber;

	@Column
	private String specialization;

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getspecialization() {
		return specialization;
	}

	public void setspecialization(String specialization) {
		this.specialization = specialization;
	}

	public User(Long id, String username, String fullName, Date dateOfBirth, String emailId, String phoneNumber,
			String specialization) {
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.specialization = specialization;
	}
}
