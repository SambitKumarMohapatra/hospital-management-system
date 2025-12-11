package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Prescription;
import com.hospital.hospital_management.dto.PrescriptionRequestDTO;
import java.util.List;

public interface PrescriptionService {
    Prescription createPrescription(Prescription prescription);
    Prescription createPrescriptionWithNotes(PrescriptionRequestDTO request);
    Prescription getPrescriptionById(Long id);
    List<Prescription> getAllPrescriptions();
    Prescription updatePrescription(Long id, Prescription prescriptionDetails);
    void deletePrescription(Long id);
}