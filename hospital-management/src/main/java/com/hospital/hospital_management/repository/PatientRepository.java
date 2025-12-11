package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);
    Optional<Patient> findByEmail(String email); // Add this
}