package com.diego.vaadin1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diego.vaadin1.moodle.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query("select s from Student s where lower(s.name) like lower(concat('%', :substring, '%'))")
	List<Student> findStudents(String substring);

}
