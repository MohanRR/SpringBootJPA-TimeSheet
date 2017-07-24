package com.personal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="details")
public class Detail extends BaseEntity implements Serializable{

	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="number")
	private String number;
	
	@Column(name="provided_date")
	private Timestamp providedDate;
	
	@Column(name="return_date")
	private Timestamp returnDate;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="paid_amount")
	private BigDecimal paidAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company compnay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getProvidedDate() {
		return providedDate;
	}

	public void setProvidedDate(Timestamp providedDate) {
		this.providedDate = providedDate;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompnay() {
		return compnay;
	}

	public void setCompnay(Company compnay) {
		this.compnay = compnay;
	}
	
}
