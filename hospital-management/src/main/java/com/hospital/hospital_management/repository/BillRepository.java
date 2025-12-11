package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByAppointmentId(Long appointmentId); // Add this
    boolean existsByAppointmentId(Long appointmentId); // Add this
}