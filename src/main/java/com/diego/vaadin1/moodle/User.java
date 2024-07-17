package com.diego.vaadin1.moodle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	@NotEmpty(message = "this field can't be emtpy")
	private String username;

	@Column(name = "password")
	@NotEmpty(message = "this field can't be emtpy")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role; 
	
	public User() {
		
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(this.role);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
