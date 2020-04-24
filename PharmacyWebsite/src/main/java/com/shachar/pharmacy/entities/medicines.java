package com.shachar.pharmacy.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shachar.pharmacy.enums.MedicineType;

@Entity
@Table (name = "medicines")
public class medicines {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private int quantityOfItems;
	
	@Column
	private String medicineName;
	
	@Column
	private  float quantityForOne;
	
	@Column
	private boolean isRegistrarsObligation;

	@Column
	private Date expirationDate;
	
	@Column (nullable = true)
	@Enumerated(EnumType.STRING)
	private MedicineType medicineType;

	@JsonIgnore
	@OneToMany (mappedBy = "medicines", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchases> purchases;
	
	public medicines() {
	}
	
	
	public medicines(long id, int quantityOfItems, String medicineName, float quantityForOne,
			boolean isRegistrarsObligation, Date expirationDate, MedicineType medicineType) {
		this.id = id;
		this.quantityOfItems = quantityOfItems;
		this.medicineName = medicineName;
		this.quantityForOne = quantityForOne;
		this.isRegistrarsObligation = isRegistrarsObligation;
		this.expirationDate = expirationDate;
		this.medicineType = medicineType;
	}
	
	public medicines(int quantityOfItems, String medicineName, float quantityForOne,
			boolean isRegistrarsObligation, Date expirationDate, MedicineType medicineType) {
		this.quantityOfItems = quantityOfItems;
		this.medicineName = medicineName;
		this.quantityForOne = quantityForOne;
		this.isRegistrarsObligation = isRegistrarsObligation;
		this.expirationDate = expirationDate;
		this.medicineType = medicineType;
	}


	public long getId() {
		return id;
	}

	public int getQuantityOfItems() {
		return quantityOfItems;
	}


	public void setQuantityOfItems(int quantityOfItems) {
		this.quantityOfItems = quantityOfItems;
	}


	public String getMedicineName() {
		return medicineName;
	}


	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}


	public float getQuantityForOne() {
		return quantityForOne;
	}


	public void setQuantityForOne(float quantityForOne) {
		this.quantityForOne = quantityForOne;
	}


	public boolean isRegistrarsObligation() {
		return isRegistrarsObligation;
	}


	public void setRegistrarsObligation(boolean isRegistrarsObligation) {
		this.isRegistrarsObligation = isRegistrarsObligation;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public MedicineType getMedicineType() {
		return medicineType;
	}


	public void setMedicineType(MedicineType medicineType) {
		this.medicineType = medicineType;
	}


	@Override
	public String toString() {
		return "medicines [id=" + id + ", quantityOfItems=" + quantityOfItems + ", medicineName=" + medicineName
				+ ", quantityForOne=" + quantityForOne + ", isRegistrarsObligation=" + isRegistrarsObligation
				+ ", expirationDate=" + expirationDate + ", medicineType=" + medicineType + "]";
	}
	
	
}
