package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findByAppointmentId(Long appointmentId); // Add this
    boolean existsByAppointmentId(Long appointmentId); // Add this
}