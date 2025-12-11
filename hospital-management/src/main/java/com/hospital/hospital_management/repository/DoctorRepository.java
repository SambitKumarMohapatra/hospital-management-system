package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Department;
import com.hospital.hospital_management.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);
    List<Doctor> findByDepartment(Department department); // Add this
}