package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Patient;
import com.hospital.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient existing = getPatientById(id);

        if (patientDetails.getName() != null) {
            existing.setName(patientDetails.getName());
        }
        if (patientDetails.getEmail() != null) {
            existing.setEmail(patientDetails.getEmail());
        }
        if (patientDetails.getPhone() != null) {
            existing.setPhone(patientDetails.getPhone());
        }

        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}