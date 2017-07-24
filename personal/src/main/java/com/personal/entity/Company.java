package com.personal.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company extends BaseEntity implements Serializable {

	@Column(name="name")
	private String name;
	
	@OneToOne
	@JoinColumn(name="address_id", referencedColumnName="id")
	private Address address;

	@ManyToMany(mappedBy="companies")
	private List<User> users;
	
	@OneToMany
	@JoinColumn(name="company_id")
	private List<Detail> details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	
}
