package com.example.hospital.controller;

import com.example.hospital.model.Appointment;
import com.example.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")  // Allows requests from localhost:3000 (React frontend) to this API
@RestController  // Marks this class as a REST controller to handle HTTP requests
@RequestMapping("/api/appointments")  // Defines a base URL for all endpoints in this controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;  // Injects the AppointmentService to handle appointment-related operations

    // Endpoint to create a new appointment
    @PostMapping("/create")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        // Calls the service method to create an appointment and returns the created appointment
        return appointmentService.createAppointment(appointment);
    }
    
    // Endpoint to get appointments by a specific doctor ID
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        // Retrieves and returns a list of appointments assigned to a specific doctor
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    // Endpoint to get appointments by a specific patient ID
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
        // Retrieves and returns a list of appointments for a specific patient
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    // Endpoint to assign a doctor to an appointment
    @PutMapping("/assign")
    public Appointment assignDoctorToAppointment(@RequestBody Map<String, Object> requestData) {
        // Extracts appointment ID and doctor ID from the request data
        Long appointmentId = Long.valueOf(requestData.get("id").toString());
        Long doctorId = Long.valueOf(requestData.get("doctorId").toString());

        // Calls the service method to assign the doctor to the appointment and returns the updated appointment
        return appointmentService.assignDoctorToAppointment(appointmentId, doctorId);
    }
}
