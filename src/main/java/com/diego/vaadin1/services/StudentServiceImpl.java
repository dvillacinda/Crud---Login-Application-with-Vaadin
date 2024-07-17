package com.diego.vaadin1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.vaadin1.moodle.Student;
import com.diego.vaadin1.repository.StudentRepository;

/**
 * Service implementation for Student operations.
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    /**
     * Autowired StudentRepository instance.
     */
    @Autowired
    private StudentRepository studentR;
    
    /**
     * Saves a Student entity to the database.
     * 
     * @param student the Student entity to be saved.
     */
    @Override
    public void save(Student student) {
        studentR.save(student); 
    }

    /**
     * Removes a Student entity from the database.
     * 
     * @param student the Student entity to be removed.
     */
    @Override
    public void remove(Student student) {
        studentR.delete(student);
    }

    /**
     * Retrieves all Student entities from the database.
     * 
     * @return a list of all Student entities.
     */
    @Override
    public List<Student> findAll() {
        return studentR.findAll();
    }

    /**
     * Retrieves a list of Student entities that match the given substring.
     * 
     * @param substring the substring to search for.
     * @return a list of Student entities that match the substring.
     */
    @Override
    public List<Student> find(String substring) {
        return studentR.findStudents(substring); 
    }
}