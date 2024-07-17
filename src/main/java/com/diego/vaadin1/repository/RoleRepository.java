package com.diego.vaadin1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.vaadin1.moodle.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{	

}
