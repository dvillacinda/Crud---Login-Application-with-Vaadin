package com.diego.vaadin1.moodle;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idStatus; 
	private String name;
	@OneToMany(mappedBy = "status")
	List<Student> students; 
	public Status() {

	}

	public Status(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
