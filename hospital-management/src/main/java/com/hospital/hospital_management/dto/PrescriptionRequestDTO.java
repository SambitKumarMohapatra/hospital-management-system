package com.hospital.hospital_management.dto;

public class PrescriptionRequestDTO {
    private Long appointmentId;
    private String medicine;
    private String notes;

    public PrescriptionRequestDTO() {}

    public PrescriptionRequestDTO(Long appointmentId, String medicine, String notes) {
        this.appointmentId = appointmentId;
        this.medicine = medicine;
        this.notes = notes;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}