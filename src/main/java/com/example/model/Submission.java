package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "submissions")
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer submissionId;

	@Column(nullable = false)
	private Date deadline;

	@Column
	private String status; // null or reviewed or revised

	@Column
	private String comment; //initially null and then updated by the reviewer to a message about what was wrong with the paper

	@Column
	private Integer rating; //initially null and then updated by the reviewer to a number between 1 and 5
	
	@Column
	private String Link; //link of the pdf file submitted by the user
	
	@ManyToOne(targetEntity = Paper.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "paperId")
	private Paper paper;

	public Submission() {
		super();
	}

	public Submission(Integer submissionId, Date deadline, String status, String comment, int rating, String link, Paper paper) {
		super();
		this.submissionId = submissionId;
		this.deadline = deadline;
		this.status = status;
		this.comment = comment;
		this.rating = rating;
		this.Link = link;
		this.paper = paper;
	}

	public Integer getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Integer submissionId) {
		this.submissionId = submissionId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
