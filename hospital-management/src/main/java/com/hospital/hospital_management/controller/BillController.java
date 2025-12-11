package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.model.Bill;
import com.hospital.hospital_management.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody Map<String, Object> requestMap) {
        try {
            System.out.println("🎯 Received bill request: " + requestMap);

            // Extract data from the map
            Map<String, Object> appointmentMap = (Map<String, Object>) requestMap.get("appointment");
            Long appointmentId = Long.valueOf(appointmentMap.get("id").toString());
            Double amount = Double.valueOf(requestMap.get("amount").toString());

            System.out.println("Creating bill - Appointment: " + appointmentId + ", Amount: " + amount);

            // Create bill object
            Bill bill = new Bill();

            // Create appointment with just ID
            com.hospital.hospital_management.model.Appointment appointment = new com.hospital.hospital_management.model.Appointment();
            appointment.setId(appointmentId);
            bill.setAppointment(appointment);

            bill.setAmount(amount);

            Bill createdBill = billingService.createBill(bill);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Bill generated successfully");
            response.put("billId", createdBill.getId());
            response.put("amount", createdBill.getAmount());
            response.put("appointmentId", createdBill.getAppointment().getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("❌ Error creating bill: " + e.getMessage());
            e.printStackTrace();

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billingService.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable Long id) {
        try {
            Bill bill = billingService.getBillById(id);
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<?> getBillByAppointmentId(@PathVariable Long appointmentId) {
        try {
            Bill bill = billingService.getBillByAppointmentId(appointmentId);
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable Long id) {
        try {
            billingService.deleteBill(id);
            return ResponseEntity.ok(Map.of("message", "Bill deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Failed to delete bill: " + e.getMessage()));
        }
    }
}