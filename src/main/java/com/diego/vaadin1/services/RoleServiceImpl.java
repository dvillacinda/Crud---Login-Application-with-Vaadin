package com.diego.vaadin1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.vaadin1.moodle.Role;
import com.diego.vaadin1.repository.RoleRepository;
/**
 * Service implementation for Role operations.
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    /**
     * Autowired RoleRepository instance.
     */
    @Autowired
    private RoleRepository roleR;
    
    /**
     * Saves a Role entity to the database.
     * 
     * @param role the Role entity to be saved.
     */
    @Override
    public void save(Role role) {
        roleR.save(role); 
    }

    /**
     * Retrieves all Role entities from the database.
     * 
     * @return a list of all Role entities.
     */
    @Override
    public List<Role> findAll() {
        return roleR.findAll();
    }
}
