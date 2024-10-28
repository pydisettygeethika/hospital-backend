package com.example.hospital.service;

import com.example.hospital.model.Doctor;
import com.example.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing doctors in the hospital system.
 * Provides methods to register doctors, log in, and retrieve doctor information.
 */
@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    // Registers a new doctor by saving their information in the database.
   
    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

   // Logs in a doctor by retrieving their information using the provided email.
    
    public Doctor login(String email) {
        return doctorRepository.findByEmail(email);
    }

    // Retrieves a list of all registered doctors in the system.
     
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
