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
	private String Approvestatus;

    @Column
    private int revisionStatus;

	@Column
	private String Tag;

    @OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
//    @Size(max = 5)
    private List<Submission> submissions;

    @ManyToOne(fetch = FetchType.EAGER)
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

	public String getApprovestatus() {
		return Approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.Approvestatus = approvestatus;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		this.Tag = tag;
	}

	@Override
	public String toString() {
		return "Paper [Approvestatus=" + Approvestatus + ", paperId=" + paperId + ", revisionStatus=" + revisionStatus
				+ ", submissions=" + submissions + ", title=" + title + ", user=" + user + "]" + ", Tag=" + Tag;
	}

	public Paper(Integer paperId, String title, int revisionStatus, List<Submission> submissions, User user, String approvestatus, String tag) {
		super();
		this.paperId = paperId;
        this.title = title;
		this.revisionStatus = revisionStatus;
		this.submissions = submissions;
		this.user = user;
		this.Approvestatus = approvestatus;
		this.Tag = tag;
	}

	public Paper() {
		super();
	}

}
