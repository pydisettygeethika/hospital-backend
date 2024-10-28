package com.example.hospital.service;

import com.example.hospital.model.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Service class for managing operations related to patients, such as registration, login, and retrieval.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Registers a new patient by saving their details in the database.
   
    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

   // Authenticates a patient by checking their email and password.
    
    public Patient login(String email, String password) {
        Optional<Patient> optionalPatient = patientRepository.findByEmail(email);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            if (patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }
    // Retrieves a list of all registered patients.
   
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Saves a patient to the database with the current date as the registration date.
    
    public void savePatient(Patient patient) {
        // Set the current date as the date of registration
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDate.now().format(formatter);
        patient.setDateOfRegistration(currentDate);

        // Save the patient to the database
        patientRepository.save(patient);
    }

    // Retrieves the registration date of a patient as a LocalDate.
   
    public LocalDate getRegistrationDate(Patient patient) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(patient.getDateOfRegistration(), formatter);
    }
}
