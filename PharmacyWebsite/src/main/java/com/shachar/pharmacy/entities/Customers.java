package com.shachar.pharmacy.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shachar.pharmacy.enums.UserType;


@Entity
@Table (name = "customers")
public class Customers {

	
	@Id
	private long id;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	private Users user;
	
	@Column (nullable = false)
	private String fullName;
	
	@Column
	private int amountOfKids;
	
	@Column
	private boolean isMarried;
	
	@Column
	private Date dateOfBirth;
	
	@JsonIgnore
	@OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchases> purchases;
	
	// Constructors--------------------
	
	public Customers() {

	}

	public Customers(String username, String password, UserType userType, String fullName, int amountOfKids, boolean isMarried, Date dateOfBirth) {
		this.user = new Users(username, password, userType);
		this.fullName = fullName;
		this.amountOfKids = amountOfKids;
		this.isMarried = isMarried;
		this.dateOfBirth = dateOfBirth;
	}

	public Customers(long id, String username, String password, UserType userType, String fullName, int amountOfKids, boolean isMarried, Date dateOfBirth) {
		this.id = id;
		this.user = new Users(username, password, userType);
		this.fullName = fullName;
		this.amountOfKids = amountOfKids;
		this.isMarried = isMarried;
		this.dateOfBirth = dateOfBirth;
	}

	// Getters and Setters--------------------
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAmountOfKids() {
		return amountOfKids;
	}

	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}

	public boolean getIsMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", user=" + user + ", fullName=" + fullName + ", amountOfKids=" + amountOfKids
				+ ", isMarried=" + isMarried + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
