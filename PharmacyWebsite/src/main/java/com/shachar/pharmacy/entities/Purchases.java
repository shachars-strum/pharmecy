package com.shachar.pharmacy.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table (name = "purchases")
public class Purchases {

	// Variables--------------------
	
	@Id
	@GeneratedValue
	private long id;
	
	

	@ ManyToOne(fetch = FetchType.EAGER)
	private medicines medicines;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Customers customer;
	
	@Column
	private int amountOfItems;
	
	@CreationTimestamp
	private Timestamp timeStamp;
	
	// Constructors--------------------
	
	public Purchases() {

	}

	public Purchases( Customers customer, int amountOfItems, Timestamp timestamp) {
		this.customer = customer;
		this.amountOfItems = amountOfItems;
		this.timeStamp = timestamp;
	}

	public Purchases(long id, Customers customer, int amountOfItems, Timestamp timestamp) {
		this.id = id;
		this.customer = customer;
		this.amountOfItems = amountOfItems;
		this.timeStamp = timestamp;
	}

	// Getters and Setters--------------------
	
	public long getId() {
		return id;
	}



	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setAmountOfItems(int amountOfItems) {
		this.amountOfItems = amountOfItems;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timestamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", customer=" + customer==null?customer.getFullName():"" + ", amountOfItems="
				+ amountOfItems + ", timestamp=" + timeStamp + "\n";
	}
}
