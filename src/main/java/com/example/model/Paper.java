package com.example.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "papers")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paperId;

    @Column
    private String title;

    @Column
    private int revisionStatus;

    @OneToMany(mappedBy = "paper")
//    @Size(max = 5)
    private List<Submission> submissions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public int getRevisionStatus() {
		return revisionStatus;
	}

	public void setRevisionStatus(int revisionStatus) {
		this.revisionStatus = revisionStatus;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", revisionStatus=" + revisionStatus
				+ ", submissions=" + submissions + ", user=" + user + "]";
	}

	public Paper(Integer paperId, String title, int revisionStatus, List<Submission> submissions, User user) {
		super();
		this.paperId = paperId;
        this.title = title;
		this.revisionStatus = revisionStatus;
		this.submissions = submissions;
		this.user = user;
	}

	public Paper() {
		super();
	}

    

    // getters and setters
    
}
