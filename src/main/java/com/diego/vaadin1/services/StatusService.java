package com.diego.vaadin1.services;

import java.util.List;

import com.diego.vaadin1.moodle.Status;

public interface StatusService {
	public void save(Status status); 
	public List<Status> findAll(); 
}
