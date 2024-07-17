package com.diego.vaadin1.moodle;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role implements GrantedAuthority{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRole;
	
	@Column
	private String name;

	@OneToMany(mappedBy = "role")
	List<User> users;
	public Role() {
		
	}
	public Role(String name) {
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
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	
	
}
