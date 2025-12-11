package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, Appointment appointmentDetails);
    void deleteAppointment(Long id);
    void cancelAppointment(Long id); // Add this method
}