package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Department;
import com.hospital.hospital_management.model.Doctor;
import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    List<Doctor> getDoctorsByDepartment(Long departmentId);
}