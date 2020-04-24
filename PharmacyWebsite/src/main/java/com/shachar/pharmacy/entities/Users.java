package com.shachar.pharmacy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shachar.pharmacy.enums.UserType;



	@Entity
	@Table (name = "users")
	public class Users{

		// Variables--------------------
		
		@Id
		@GeneratedValue
		private long id;
		
		@Column (unique = true, nullable = false)
		private String userName;
		
		@Column (nullable = false)
		private String password;
		
		@Column (nullable = true)
		@Enumerated(EnumType.STRING)
		private UserType userType;
		
		@ManyToOne
		private Organizations organizations;
		
		
		
		

		// Constructors--------------------

		public Users() {

		}

		public Users(String userName, String password, UserType userType) {
			this.userName = userName;
			this.password = password;
			this.userType = userType;
		}
		
		public Users(String userName, String password, UserType userType, Organizations organizations) {
			this.userName = userName;
			this.password = password;
			this.userType = userType;
			this.organizations = organizations;
		}


	

		public Users(long id, String userName, String password, UserType userType, Organizations organizations) {
			this.id = id;
			this.userName = userName;
			this.password = password;
			this.userType = userType;
			this.organizations = organizations;

		}

		// Getters and Setters--------------------



		public long getId() {
			return id;
		}

		public Organizations getOrganizations() {
			return organizations;
		}


		public void setOrganizations(Organizations organizations) {
			this.organizations = organizations;
		}


		public void setId(long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserType getUserType() {
			return userType;
		}

		public void setUserType(UserType userType) {
			this.userType = userType;
		}

		

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType
					+ "\n";
		}
	}


