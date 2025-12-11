package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.PrescriptionRequestDTO;
import com.hospital.hospital_management.model.Prescription;
import com.hospital.hospital_management.dto.PrescriptionRequestDTO;
import com.hospital.hospital_management.dto.ErrorResponse;
import com.hospital.hospital_management.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionRequestDTO request) {
        try {
            Prescription prescription = prescriptionService.createPrescriptionWithNotes(request);
            return ResponseEntity.ok(prescription);
        } catch (RuntimeException e) {
            // Return proper JSON error response
            ErrorResponse errorResponse = new ErrorResponse("PRESCRIPTION_CREATION_ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long id) {
        try {
            Prescription prescription = prescriptionService.getPrescriptionById(id);
            return ResponseEntity.ok(prescription);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("PRESCRIPTION_NOT_FOUND", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }
}