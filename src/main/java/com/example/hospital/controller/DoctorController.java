package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import com.example.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Allows cross-origin requests from the React frontend running on localhost:3000
@RestController  // Marks this class as a REST controller, making it capable of handling HTTP requests
@RequestMapping("/api/doctors")  // Sets the base URL for all endpoints in this controller to /api/doctors
public class DoctorController {

    @Autowired
    private DoctorService doctorService;  // Injects the DoctorService to handle doctor-related operations

    // Endpoint to register a new doctor
    @PostMapping("/register")
    public Doctor registerDoctor(@RequestBody Doctor doctor) {
        // Calls the service method to register a new doctor and returns the registered doctor details
        return doctorService.registerDoctor(doctor);
    }

    // Endpoint to log in a doctor based on email
    @PostMapping("/login")
    public Doctor login(@RequestParam String email) {
        // Calls the service method to log in the doctor using their email and returns the doctor details
        return doctorService.login(email);
    }

    // Endpoint to retrieve a list of all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        // Calls the service method to retrieve and return a list of all doctors
        return doctorService.getAllDoctors();
    }
}
