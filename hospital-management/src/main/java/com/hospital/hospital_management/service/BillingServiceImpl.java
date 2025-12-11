package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.model.Bill;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Bill createBill(Bill bill) {
        try {
            System.out.println("Creating bill for appointment: " + bill.getAppointment().getId());

            // Get the full appointment from database
            Long appointmentId = bill.getAppointment().getId();
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

            // Set the full appointment object
            bill.setAppointment(appointment);

            // Validate amount
            if (bill.getAmount() == null || bill.getAmount() <= 0) {
                throw new RuntimeException("Invalid bill amount: " + bill.getAmount());
            }

            Bill savedBill = billRepository.save(bill);
            System.out.println("Bill created successfully with ID: " + savedBill.getId());
            return savedBill;

        } catch (Exception e) {
            System.err.println("Error creating bill: " + e.getMessage());
            throw new RuntimeException("Failed to create bill: " + e.getMessage());
        }
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    @Override
    public Bill getBillByAppointmentId(Long appointmentId) {
        Bill bill = billRepository.findByAppointmentId(appointmentId);
        if (bill == null) {
            throw new RuntimeException("No bill found for appointment id: " + appointmentId);
        }
        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill updateBill(Long id, Bill billDetails) {
        Bill existing = getBillById(id);

        if (billDetails.getAmount() != null) {
            existing.setAmount(billDetails.getAmount());
        }

        return billRepository.save(existing);
    }

    @Override
    public void deleteBill(Long id) {
        Bill bill = getBillById(id);
        billRepository.delete(bill);
    }
}