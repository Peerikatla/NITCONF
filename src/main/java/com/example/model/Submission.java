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

import java.time.LocalDate;

@Entity
@Table(name = "submissions")
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer submissionId;

	@Column(nullable = false)
	private LocalDate deadline;

	@Column
	private String status; // null or reviewed or revised

	@Column
	private String comment;

	@Column
	private Integer Originality;

	@Column
	private Integer Relevance;

	@Column
	private Integer Quality;

	@Column
	private Integer TCA;

	@Column
	private Integer SignificanceOfWork;

	@Column
	private Integer AppropriateForSAC;

	@Column
	private String Link; // link of the pdf file submitted by the user

	@ManyToOne(targetEntity = Paper.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "paperId")
	private Paper paper;

	public Submission() {
		super();
	}

	

	public Submission(Integer submissionId, LocalDate deadline, String status, String comment, Integer originality,
			Integer relevance, Integer quality, Integer TCA, Integer significanceOfWork,
			Integer appropriateForSAC, String link, Paper paper) {
		this.submissionId = submissionId;
		this.deadline = deadline;
		this.status = status;
		this.comment = comment;
		this.Originality = originality;
		this.Relevance = relevance;
		this.Quality = quality;
		this.TCA = TCA;
		this.SignificanceOfWork = significanceOfWork;
		this.AppropriateForSAC = appropriateForSAC;
		this.Link = link;
		this.paper = paper;
	}



	public Integer getTCA() {
		return TCA;
	}

	public void setTCA(Integer TCA) {
		this.TCA = TCA;
	}

	public Integer getSignificanceOfWork() {
		return SignificanceOfWork;
	}

	public void setSignificanceOfWork(Integer significanceOfWork) {
		this.SignificanceOfWork = significanceOfWork;
	}

	public Integer getAppropriateForSAC() {
		return AppropriateForSAC;
	}

	public void setAppropriateForSAC(Integer appropriateForSAC) {
		this.AppropriateForSAC = appropriateForSAC;
	}

	public Integer getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Integer submissionId) {
		this.submissionId = submissionId;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
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

	public Integer getOriginality() {
		return Originality;
	}

	public void setOriginality(Integer originality) {
		this.Originality = originality;
	}

	public Integer getRelevance() {
		return Relevance;
	}

	public void setRelevance(Integer relevance) {
		this.Relevance = relevance;
	}

	public Integer getQuality() {
		return Quality;
	}

	public void setQuality(Integer quality) {
		this.Quality = quality;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		this.Link = link;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
