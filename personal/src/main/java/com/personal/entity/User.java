package com.personal.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="findUserByUsernameAndPassword",query="select u from User u where u.email=:email and password=:password")
})
public class User extends BaseEntity implements Serializable{

	@Basic(optional=false)
	@Email
	@Column(name="email")
	private String email;
	
	@Basic(optional=false)
	@Column(name="password")
	private String password;
	
	@Basic(optional=false)
	@Column(name="first_name")
	private String firstName;
	
	@Basic(optional=false)
	@Column(name="last_name")
	private String lastName;
	
	@OneToOne
	@JoinColumn(name="address_id", referencedColumnName="id")	
	private Address address;
	
	@Basic(optional=false)
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE })
	@JoinTable(name = "user_company_association", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	private List<Company> companies;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List<Detail> details;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List<TimeSheet> timeSheets;
	
	private transient String authToken;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(List<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}