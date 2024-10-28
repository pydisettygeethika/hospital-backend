package com.example.user_login;

import com.example.hospital.model.Patient;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.service.PatientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PatientService.
 */
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    /**
     * Sets up a sample Patient object before each test.
     */
    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setName("Alice Smith");
        patient.setAge(30);
        patient.setGender("Female");
        patient.setContactNumber("1234567890");
        patient.setAddress("123 Main St");
        patient.setMedicalHistory("No known conditions");
        patient.setEmail("alice@example.com");
        patient.setPassword("password123");
        patient.setHeight(165.0);
        patient.setWeight(55.0);
    }

    /**
     * Tests patient registration functionality.
     */
    @Test
    void testRegisterPatient() {
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.registerPatient(patient);

        assertNotNull(savedPatient, "Patient should be registered successfully");
        assertEquals(patient.getId(), savedPatient.getId(), "Patient ID should match");
        assertEquals(patient.getEmail(), savedPatient.getEmail(), "Patient email should match");
        verify(patientRepository, times(1)).save(patient);
    }

    /**
     * Tests successful login with valid credentials.
     */
    @Test
    void testLoginWithValidCredentials() {
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.of(patient));

        Patient loggedInPatient = patientService.login(patient.getEmail(), patient.getPassword());

        assertNotNull(loggedInPatient, "Login should succeed with valid credentials");
        assertEquals(patient.getEmail(), loggedInPatient.getEmail(), "Emails should match");
        verify(patientRepository, times(1)).findByEmail(patient.getEmail());
    }

    /**
     * Tests login attempt with incorrect password.
     */
    @Test
    void testLoginWithInvalidCredentials() {
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.of(patient));

        Patient loggedInPatient = patientService.login(patient.getEmail(), "wrongpassword");

        assertNull(loggedInPatient, "Login should fail with incorrect password");
        verify(patientRepository, times(1)).findByEmail(patient.getEmail());
    }

    /**
     * Tests retrieval of all patients.
     */
    @Test
    void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> fetchedPatients = patientService.getAllPatients();

        assertEquals(1, fetchedPatients.size(), "Should return exactly one patient");
        assertEquals(patient.getEmail(), fetchedPatients.get(0).getEmail(), "Patient email should match");
        verify(patientRepository, times(1)).findAll();
    }

    /**
     * Tests saving a patient with the date of registration set to the current date.
     */
    @Test
    void testSavePatientWithDateOfRegistration() {
        patientService.savePatient(patient);

        assertNotNull(patient.getDateOfRegistration(), "Date of registration should be set");
        LocalDate registrationDate = LocalDate.parse(patient.getDateOfRegistration(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(LocalDate.now(), registrationDate, "Registration date should match the current date");

        verify(patientRepository, times(1)).save(patient);
    }

    /**
     * Tests retrieval of the registration date as a LocalDate.
     */
    @Test
    void testGetRegistrationDate() {
        String testDate = "2023-10-26";
        patient.setDateOfRegistration(testDate);

        LocalDate registrationDate = patientService.getRegistrationDate(patient);

        assertNotNull(registrationDate, "Registration date should not be null");
        assertEquals(LocalDate.parse(testDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")), registrationDate, "Registration date should match test date");
    }
}
