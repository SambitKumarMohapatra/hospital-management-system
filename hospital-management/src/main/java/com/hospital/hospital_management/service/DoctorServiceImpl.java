package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Doctor;
import com.hospital.hospital_management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor existing = getDoctorById(id);

        if (doctorDetails.getName() != null) {
            existing.setName(doctorDetails.getName());
        }
        if (doctorDetails.getSpecialization() != null) {
            existing.setSpecialization(doctorDetails.getSpecialization());
        }
        if (doctorDetails.getEmail() != null) {
            existing.setEmail(doctorDetails.getEmail());
        }
        if (doctorDetails.getPhone() != null) {
            existing.setPhone(doctorDetails.getPhone());
        }

        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}