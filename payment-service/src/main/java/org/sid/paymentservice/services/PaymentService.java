package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Float calculateTotalMontantByStudentId(Long idStudent);
    List<Payment> getPaymentsByIdStudent(Long idStudent);
    List<Payment> getPaymentsByIdContinuingEducation(Long idContinuingEducation);
    List<Payment> getPaymentsByPaymentYear(int year);
    List<Payment> getPayments();
}
