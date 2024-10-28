package com.example.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors") // Maps this entity to the "doctors" table in the database
public class Doctor {

    // Unique identifier for each doctor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// Doctor's name
    private String specialization;// Specialization of the doctor (e.g., cardiology, neurology)
    private String contactNumber; // Contact number of the doctor
    private String email;  // Email address of the doctor, also used for login
    private int experienceYears; // Number of years the doctor has been practicing
    private String password; // Password for doctor authentication

    // Getters and Setters for each field

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
