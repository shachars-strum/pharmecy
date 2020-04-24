package com.shachar.pharmacy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "organizations")
public class Organizations {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String organizationName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	private Organizations() {
		
	}

	public Organizations(long id, String organizationName, String email, String phone) {
		this.id = id;
		this.organizationName = organizationName;
		this.email = email;
		this.phone = phone;
	}
	
	public Organizations( String organizationName, String email, String phone) {
		this.organizationName = organizationName;
		this.email = email;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}


	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Organizations [id=" + id + ", organizationName=" + organizationName + ", email=" + email + ", phone="
				+ phone + "]";
	}
	
	
	
	
}
