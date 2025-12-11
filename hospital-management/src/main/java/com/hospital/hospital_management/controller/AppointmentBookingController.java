package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.AppointmentRequestDTO;
import com.hospital.hospital_management.model.*;
import com.hospital.hospital_management.repository.*;
import com.hospital.hospital_management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentBookingController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequestDTO request) {
        try {
            System.out.println("Received appointment booking request: " + request);

            // Step 1: Create or find patient
            Patient patient = patientRepository.findByEmail(request.getPatientEmail())
                    .orElseGet(() -> {
                        Patient newPatient = new Patient(
                                request.getPatientName(),
                                request.getPatientEmail(),
                                request.getPatientPhone()
                        );
                        return patientRepository.save(newPatient);
                    });

            // Step 2: Validate doctor exists
            Doctor doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + request.getDoctorId()));

            // Step 3: Validate department exists
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + request.getDepartmentId()));

            // Step 4: Create appointment
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setDepartment(department);
            appointment.setAppointmentDate(request.getAppointmentDate());
            appointment.setAppointmentTime(request.getAppointmentTime());
            appointment.setStatus(request.getStatus() != null ? request.getStatus() : "BOOKED");

            Appointment savedAppointment = appointmentService.createAppointment(appointment);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Appointment booked successfully!");
            response.put("appointmentId", savedAppointment.getId());
            response.put("patientName", patient.getName());
            response.put("doctorName", doctor.getName());
            response.put("appointmentDate", savedAppointment.getAppointmentDate());
            response.put("appointmentTime", savedAppointment.getAppointmentTime());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error in bookAppointment: " + e.getMessage());
            e.printStackTrace();

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to book appointment: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to fetch appointments: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Appointment not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO request) {
        try {
            Appointment existing = appointmentService.getAppointmentById(id);

            // Update fields if provided
            if (request.getAppointmentDate() != null) {
                existing.setAppointmentDate(request.getAppointmentDate());
            }
            if (request.getAppointmentTime() != null) {
                existing.setAppointmentTime(request.getAppointmentTime());
            }
            if (request.getStatus() != null) {
                existing.setStatus(request.getStatus());
            }

            Appointment updated = appointmentService.updateAppointment(id, existing);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to update appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        try {
            appointmentService.cancelAppointment(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment cancelled successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to cancel appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}