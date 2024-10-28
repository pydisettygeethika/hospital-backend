package com.example.hospital.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity  // Marks this class as a JPA entity representing a table in the database
@Table(name = "appointments")  // Specifies the table name for this entity as "appointments"
public class Appointment {

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates a unique identifier for each record
    private Long id;

    @ManyToOne  // Specifies a many-to-one relationship with the Doctor entity
    @JoinColumn(name = "doctor_id", nullable = false)  // Creates a foreign key column "doctor_id" for doctor association
    private Doctor doctor;

    @ManyToOne  // Specifies a many-to-one relationship with the Patient entity
    @JoinColumn(name = "patient_id", nullable = false)  // Creates a foreign key column "patient_id" for patient association
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)  // Specifies the date and time type for appointmentDate
    private Date appointmentDate;

    private String reason;  // The reason for the appointment provided by the patient

    // Patient details fields for quick reference on the appointment
    private String patientName;  // Patient's name associated with the appointment
    private String patientPhoneNumber;  // Patient's contact number
    private String patientGender;  // Patient's gender
    private String bloodGroup;  // Patient's blood group

    private String preferredAppointmentTime;  // Patient's preferred appointment time
    private String preferredMode;  // Patient's preferred mode of consultation (e.g., in-person or virtual)

    private String status;  // Status of the appointment (e.g., "assigned," "completed," or "pending")

    // Getters and Setters for each field
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientPhoneNumber() { return patientPhoneNumber; }
    public void setPatientPhoneNumber(String patientPhoneNumber) { this.patientPhoneNumber = patientPhoneNumber; }

    public String getPatientGender() { return patientGender; }
    public void setPatientGender(String patientGender) { this.patientGender = patientGender; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getPreferredAppointmentTime() { return preferredAppointmentTime; }
    public void setPreferredAppointmentTime(String preferredAppointmentTime) { this.preferredAppointmentTime = preferredAppointmentTime; }

    public String getPreferredMode() { return preferredMode; }
    public void setPreferredMode(String preferredMode) { this.preferredMode = preferredMode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
