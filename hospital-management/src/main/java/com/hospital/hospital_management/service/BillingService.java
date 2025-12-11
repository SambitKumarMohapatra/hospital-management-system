package com.hospital.hospital_management.service;

import com.hospital.hospital_management.model.Bill;
import java.util.List;

public interface BillingService {
    Bill createBill(Bill bill);
    Bill getBillById(Long id);
    Bill getBillByAppointmentId(Long appointmentId);
    List<Bill> getAllBills();
    Bill updateBill(Long id, Bill billDetails);
    void deleteBill(Long id);
}