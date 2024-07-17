package com.diego.vaadin1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.vaadin1.moodle.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{
	//all CRUD functions are implemented as default 
	
}
