package com.example.model;
import lombok.AllArgsConstructor; 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User",
schema ="nitconf",
uniqueConstraints= {
		@UniqueConstraint(
		name="emailId_unique",
		 columnNames = { "emailId" }
)
})
public class User{
	//check for identity type in java guides there is generated value
	//for id sequence generator see video
	//for no null constraint @Column(name ="string name",nullable=false)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userid=null;
	private String firstName=null;
	private String lastName=null;
	private String username=null;
	private String number=null;
	private String password=null;
	private Long paperlimit=null;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Long getPaperlimit() {
		return paperlimit;
	}
	public void setPaperlimit(Long paperlimit) {
		this.paperlimit = paperlimit;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	private List<String> tags;
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", number=" + number + ", password=" + password + ", paperlimit=" + paperlimit + ", tags=" + tags
				+ "]";
	}
	public User(Long userid, String firstName, String lastName, String emailId, String number, String password,
			Long paperlimit, List<String> tags) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.number = number;
		this.password = password;
		this.paperlimit = paperlimit;
		this.tags = tags;
	}
	public User() {
		super();
	}
	
	
	
	
}
