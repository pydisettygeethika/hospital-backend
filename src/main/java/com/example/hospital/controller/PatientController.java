package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import com.example.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Allows requests from the React frontend running on localhost:3000
@RestController  // Indicates this class handles HTTP requests for RESTful services
@RequestMapping("/api/patients")  // Defines the base URL for all endpoints in this controller as /api/patients
public class PatientController {

    @Autowired
    private PatientService patientService;  // Injects the PatientService to handle patient-related operations

    // Endpoint to register a new patient
    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
        // Calls the service to save the new patient and returns the saved patient with a 201 (CREATED) status
        Patient savedPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // Endpoint to log in a patient using email and password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        // Calls the service to log in the patient and checks if credentials are valid
        Patient patient = patientService.login(email, password);
        if (patient != null) {
            // Returns the patient details with a 200 (OK) status if credentials are valid
            return ResponseEntity.ok(patient);
        } else {
            // Returns an error message with a 401 (UNAUTHORIZED) status if credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Endpoint to retrieve a list of all registered patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        // Calls the service to retrieve all patients and returns them with a 200 (OK) status
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
}
