package com.example.model;
// import lombok.AllArgsConstructor; 
import lombok.Builder;
import lombok.Data;
// import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;

/**
 * A representation of a user in the system.
 *
 * @author example
 */
@Builder
@Entity
@Data
// @AllArgsConstructor
// @NoArgsConstructor
@Table(name = "user")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    /**
     * The full name of the user.
     */
    private String fullName;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The phone number of the user.
     */
    private String number;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The limit of papers for the user.
     */
    private Long paperlimit;

    private Date dateOfBirth;

    private String email;

    /**
     * The Specialization for the user.
     */
    private String Specialization;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Paper> papers;

    

    /**
     * Gets the unique identifier for the user.
     *
     * @return the userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userid the userid to set
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * Gets the full name of the user.
     *
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void     setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	/**
     * Gets the limit of papers for the user.
     *
     * @return the paperlimit
     */
    public Long getPaperlimit() {
        return paperlimit;
    }

    /**
     * Sets the limit of papers for the user.
     *
     * @param paperlimit the paperlimit to set
     */
    public void setPaperlimit(Long paperlimit) {
        this.paperlimit = paperlimit;
    }
    
    

    public User(Integer userid, String fullName, String username, String number, String password,
			Long paperlimit, Date dateOfBirth, String email, String specialization, List<Paper> papers) {
		super();
		this.userid = userid;
		this.fullName = fullName;
		this.username = username;
		this.number = number;
		this.password = password;
		this.paperlimit = paperlimit;
		this.dateOfBirth = dateOfBirth;
        this.email = email;
		Specialization = specialization;
		this.papers = papers;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	@Override
	public String toString() {
		return "User [userid=" + userid + ", fullName=" + fullName + ", username="
				+ username + ", number=" + number + ", password=" + password + ", paperlimit=" + paperlimit
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", Specialization=" + Specialization + "]";
	}

    /**
     * Instantiates a new user.
     */
    public User() {
        super();
    }

}
