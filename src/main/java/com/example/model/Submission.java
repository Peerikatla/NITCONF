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
	private String comment; // initially null and then updated by the reviewer to a message about what was
							// wrong with the paper

	@Column
	private Integer Originality;

	@Column
	private Integer Relevance;

	@Column
	private Integer Quality;

	@Column
	private Integer TechnicalContentAndAccuracy;

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
			Integer relevance, Integer quality, Integer technicalContentandAccuracy, Integer significanceOfWork,
			Integer appropriateForSAC, String link, Paper paper) {
		this.submissionId = submissionId;
		this.deadline = deadline;
		this.status = status;
		this.comment = comment;
		Originality = originality;
		Relevance = relevance;
		Quality = quality;
		TechnicalContentAndAccuracy = technicalContentandAccuracy;
		SignificanceOfWork = significanceOfWork;
		AppropriateForSAC = appropriateForSAC;
		Link = link;
		this.paper = paper;
	}



	public Integer getTechnicalContentAndAccuracy() {
		return TechnicalContentAndAccuracy;
	}

	public void setTechnicalContentAndAccuracy(Integer technicalContentandAccuracy) {
		TechnicalContentAndAccuracy = technicalContentandAccuracy;
	}

	public Integer getSignificanceOfWork() {
		return SignificanceOfWork;
	}

	public void setSignificanceOfWork(Integer significanceOfWork) {
		SignificanceOfWork = significanceOfWork;
	}

	public Integer getAppropriateForSAC() {
		return AppropriateForSAC;
	}

	public void setAppropriateForSAC(Integer appropriateForSAC) {
		AppropriateForSAC = appropriateForSAC;
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
		Originality = originality;
	}

	public Integer getRelevance() {
		return Relevance;
	}

	public void setRelevance(Integer relevance) {
		Relevance = relevance;
	}

	public Integer getQuality() {
		return Quality;
	}

	public void setQuality(Integer quality) {
		Quality = quality;
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
