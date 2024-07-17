package com.diego.vaadin1.services;

import java.util.List;

import com.diego.vaadin1.moodle.Role;

public interface RoleService {
	public void save(Role role);
	public List<Role> findAll();
}
