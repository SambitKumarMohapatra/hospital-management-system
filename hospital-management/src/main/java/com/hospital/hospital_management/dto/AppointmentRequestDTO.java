package com.hospital.hospital_management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequestDTO {
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private Long doctorId;
    private Long departmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    // Constructors, Getters and Setters (keep your existing ones)
    // Add a default constructor if missing
    public AppointmentRequestDTO() {}

    public AppointmentRequestDTO(String patientName, String patientEmail, String patientPhone, Long doctorId, Long departmentId, LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientPhone = patientPhone;
        this.doctorId = doctorId;
        this.departmentId = departmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }
    // getter and setters


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}