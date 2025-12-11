package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id,Patient patientDetails);
    void deletePatient(Long id);
}
