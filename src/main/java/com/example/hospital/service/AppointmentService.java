package com.example.hospital.service;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing appointments in the hospital system.
 * Provides methods to create, retrieve, and manage appointments.
 */
@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;  

    // Creates a new appointment.
     
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

   //Retrieves all appointments for a given doctor by their ID.
     
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Retrieves all appointments for a given patient by their ID.
    
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Assigns a doctor to an existing appointment.
     
    public Appointment assignDoctor(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setStatus("Assigned"); 
        return appointmentRepository.save(existingAppointment);
    }


//Assigns a doctor to a specific appointment by their IDs.
    
    public Appointment assignDoctorToAppointment(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    
        appointment.setDoctor(doctor);
        appointment.setStatus("Assigned");
        return appointmentRepository.save(appointment);
    }
}
