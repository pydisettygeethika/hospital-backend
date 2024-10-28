package com.example.hospital.model;

import java.util.Date;

public class AppointmentDto {
   
    private Long doctorId;  // ID of the doctor associated with the appointment
    private Long patientId; // ID of the patient associated with the appointment
    private Date appointmentDate;// Date and time of the appointment
    private String reason;  // Reason provided for the appointment
    private String patientName; // Name of the patient associated with the appointment
    private String patientPhoneNumber; // Contact phone number of the patient
    private String patientGender; // Gender of the patient
    private String bloodGroup;// Blood group of the patient
    private String preferredAppointmentTime;  // Patient's preferred time for the appointment
    private String preferredMode; // Patient's preferred mode of consultation (e.g., in-person or virtual)
    private String status; // Current status of the appointment (e.g., "scheduled", "completed", "canceled")

    // Getters and Setters for each field

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

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
