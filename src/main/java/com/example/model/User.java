package com.example.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    private String fullName;

    private String username;

    private String number;

    private String password;

    private LocalDate dateOfBirth;

    private String email;

    private String Specialization;

    private String otp;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime otpExpiry;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Paper> papers;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    @Override
    public String toString() {
        return "User [userid=" + userid + ", fullName=" + fullName + ", username="
                + username + ", number=" + number + ", password=" + password 
                + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", Specialization=" + Specialization + "]";
    }

    public User() {
        super();
    }

    public User(Integer userid, String fullName, String username, String number, String password,
            LocalDate dateOfBirth, String email, String specialization, List<Paper> papers) {
        this.userid = userid;
        this.fullName = fullName;
        this.username = username;
        this.number = number;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.Specialization = specialization;
        this.papers = papers;
    }

}