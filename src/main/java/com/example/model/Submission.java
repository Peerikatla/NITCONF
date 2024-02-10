package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int submissionId;

    @Column(nullable = false)
    private Date deadline;

    @Column
    private String comment;

    @Column
    private int rating;
    
    @Column
    private String Link;
    
    @ManyToOne(targetEntity = Paper.class, optional=false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Paper paper;

	public Submission(int submissionId, Date deadline, String comment, int rating, String link, Paper paper) {
		super();
		this.submissionId = submissionId;
		this.deadline = deadline;
		this.comment = comment;
		this.rating = rating;
		Link = link;
		this.paper = paper;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public Submission() {
		super();
	}
    
}
