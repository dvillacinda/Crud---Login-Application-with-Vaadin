package com.diego.vaadin1.moodle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idStudent; 
	@Column
	@NotEmpty(message = "Name can't be null")
	private String name; 
	
	@Column
	@NotNull(message="Age can't be null")
	@Min(value=0,message ="Age can't be smaller than 0")
	@Max(value=120,message ="Age can't be larger than 120")
	private int age;
	
	@Column
	@NotNull(message = "Zip code can't be null")
	@Min(value=1000,message="Zip code can't be smaller than 1000")
	@Max(value=9999,message ="Zip code can't be larger than 9999")
	
	private int zipcode; 
	
	@Column
	@NotEmpty(message = "Country can't be null")
	private String country; 
	
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status status; 
	
	public Student() {
		
	}

	public Student(String name, int age, int zipcode, String country,Status status) {
		super();
		this.name = name;
		this.age = age;
		this.zipcode = zipcode;
		this.country = country;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
