package com.example.user_login;

import com.example.hospital.model.Doctor;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.service.DoctorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DoctorService.
 */
@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;

    /**
     * Sets up a sample Doctor object before each test.
     */
    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");
        doctor.setSpecialization("Cardiology");
        doctor.setContactNumber("1234567890");
        doctor.setEmail("john.doe@example.com");
        doctor.setExperienceYears(10);
        doctor.setPassword("password123");
    }

    /**
     * Tests doctor registration functionality.
     */
    @Test
    void testRegisterDoctor() {
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor registeredDoctor = doctorService.registerDoctor(doctor);

        assertNotNull(registeredDoctor, "Doctor should be registered successfully");
        assertEquals(doctor.getId(), registeredDoctor.getId(), "Doctor ID should match");
        assertEquals(doctor.getEmail(), registeredDoctor.getEmail(), "Doctor email should match");
        verify(doctorRepository, times(1)).save(doctor);
    }

    /**
     * Tests doctor login by email.
     */
    @Test
    void testLoginDoctorByEmail() {
        when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor foundDoctor = doctorService.login(doctor.getEmail());

        assertNotNull(foundDoctor, "Doctor should be found by email");
        assertEquals(doctor.getEmail(), foundDoctor.getEmail(), "Emails should match");
        verify(doctorRepository, times(1)).findByEmail(doctor.getEmail());
    }

    /**
     * Tests login attempt with an email that does not exist in the repository.
     */
    @Test
    void testLoginDoctorByEmailNotFound() {
        when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(null);

        Doctor foundDoctor = doctorService.login(doctor.getEmail());

        assertNull(foundDoctor, "Doctor should not be found if email does not exist");
        verify(doctorRepository, times(1)).findByEmail(doctor.getEmail());
    }

    /**
     * Tests retrieval of all doctors.
     */
    @Test
    void testGetAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);

        when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> fetchedDoctors = doctorService.getAllDoctors();

        assertEquals(1, fetchedDoctors.size(), "Should return exactly one doctor");
        assertEquals(doctor.getEmail(), fetchedDoctors.get(0).getEmail(), "Doctor email should match");
        verify(doctorRepository, times(1)).findAll();
    }
}
