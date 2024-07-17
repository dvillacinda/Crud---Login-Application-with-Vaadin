package com.diego.vaadin1.services;

import java.util.List;

import com.diego.vaadin1.moodle.Student;

public interface StudentService {
	public void save(Student student);
	public void remove(Student student); 
	public List<Student> findAll(); 
	public List<Student> find(String substring); 
}
