package com.example.hospital.repository;

import com.example.hospital.model.Patient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Patient entities.
 * Extends JpaRepository to provide basic CRUD operations.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
     //Finds a patient by their email.
     
    Optional<Patient> findByEmail(String email);
}
