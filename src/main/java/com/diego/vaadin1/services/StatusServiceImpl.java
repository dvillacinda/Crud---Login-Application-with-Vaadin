package com.diego.vaadin1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.vaadin1.moodle.Status;
import com.diego.vaadin1.repository.StatusRepository;

/**
 * Service implementation for Status operations.
 */
@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements StatusService {
    
    /**
     * Autowired StatusRepository instance.
     */
    @Autowired
    private StatusRepository statusR;
    
    /**
     * Saves a Status entity to the database.
     * 
     * @param status the Status entity to be saved.
     */
    @Override
    @Transactional
    public void save(Status status) {
        statusR.save(status); 
    }

    /**
     * Retrieves all Status entities from the database.
     * 
     * @return a list of all Status entities.
     */
    @Override
    public List<Status> findAll() {
        return statusR.findAll();
    }

	
}
