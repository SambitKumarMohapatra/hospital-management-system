package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment existing = getAppointmentById(id);

        if (appointmentDetails.getAppointmentDate() != null) {
            existing.setAppointmentDate(appointmentDetails.getAppointmentDate());
        }
        if (appointmentDetails.getAppointmentTime() != null) {
            existing.setAppointmentTime(appointmentDetails.getAppointmentTime());
        }
        if (appointmentDetails.getStatus() != null) {
            existing.setStatus(appointmentDetails.getStatus());
        }

        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
    }
}