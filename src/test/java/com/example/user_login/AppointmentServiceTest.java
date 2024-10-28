package com.example.user_login;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.service.AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AppointmentService.
 */
@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment appointment;
    private Doctor doctor;
    private Patient patient;

    /**
     * Sets up test data before each test.
     */
    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);

        patient = new Patient();
        patient.setId(1L);

        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(new Date());
        appointment.setReason("Routine Checkup");
    }

    /**
     * Tests the creation of an appointment.
     */
    @Test
    void testCreateAppointment() {
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.createAppointment(appointment);

        assertNotNull(createdAppointment);
        assertEquals(appointment.getId(), createdAppointment.getId());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    /**
     * Tests retrieving appointments by doctor ID.
     */
    @Test
    void testGetAppointmentsByDoctorId() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        when(appointmentRepository.findByDoctorId(doctor.getId())).thenReturn(appointments);

        List<Appointment> fetchedAppointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());

        assertEquals(1, fetchedAppointments.size());
        assertEquals(doctor.getId(), fetchedAppointments.get(0).getDoctor().getId());
        verify(appointmentRepository, times(1)).findByDoctorId(doctor.getId());
    }

    /**
     * Tests retrieving appointments by patient ID.
     */
    @Test
    void testGetAppointmentsByPatientId() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        when(appointmentRepository.findByPatientId(patient.getId())).thenReturn(appointments);

        List<Appointment> fetchedAppointments = appointmentService.getAppointmentsByPatientId(patient.getId());

        assertEquals(1, fetchedAppointments.size());
        assertEquals(patient.getId(), fetchedAppointments.get(0).getPatient().getId());
        verify(appointmentRepository, times(1)).findByPatientId(patient.getId());
    }

    /**
     * Tests assigning a doctor to an appointment.
     */
    @Test
    void testAssignDoctor() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment updatedAppointment = appointmentService.assignDoctor(appointment);

        assertNotNull(updatedAppointment);
        assertEquals("Assigned", updatedAppointment.getStatus());
        assertEquals(doctor, updatedAppointment.getDoctor());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    /**
     * Tests assigning a doctor to an appointment when the appointment is not found.
     */
    @Test
    void testAssignDoctorAppointmentNotFound() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.assignDoctor(appointment);
        });

        assertEquals("Appointment not found", exception.getMessage());
        verify(appointmentRepository, times(1)).findById(appointment.getId());
    }
}
