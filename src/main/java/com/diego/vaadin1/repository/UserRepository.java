package com.diego.vaadin1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.vaadin1.moodle.User;
import com.google.common.base.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
}
