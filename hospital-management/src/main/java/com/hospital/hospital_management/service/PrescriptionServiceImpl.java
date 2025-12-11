package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PrescriptionRequestDTO;
import com.hospital.hospital_management.model.Prescription;
import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImpl(AppointmentRepository appointmentRepository,
                                   PrescriptionRepository prescriptionRepository) {
        this.appointmentRepository = appointmentRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        if (prescription.getAppointment() == null || prescription.getAppointment().getId() == null) {
            throw new RuntimeException("Appointment is required for prescription");
        }

        Long appointmentId = prescription.getAppointment().getId();
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        prescription.setAppointment(appointment);
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription createPrescriptionWithNotes(PrescriptionRequestDTO request) {
        // Validate request
        if (request.getAppointmentId() == null) {
            throw new RuntimeException("Appointment ID is required");
        }
        if (request.getMedicine() == null || request.getMedicine().trim().isEmpty()) {
            throw new RuntimeException("Medicine is required");
        }

        // Find appointment
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + request.getAppointmentId()));

        // Create prescription
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedicine(request.getMedicine());
        prescription.setNotes(request.getNotes() != null ? request.getNotes() : "None");

        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        Prescription prescription = getPrescriptionById(id);

        if (prescriptionDetails.getMedicine() != null) {
            prescription.setMedicine(prescriptionDetails.getMedicine());
        }
        if (prescriptionDetails.getNotes() != null) {
            prescription.setNotes(prescriptionDetails.getNotes());
        }

        return prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long id) {
        Prescription prescription = getPrescriptionById(id);
        prescriptionRepository.delete(prescription);
    }
}