package org.sid.paymentservice.services;

import org.sid.paymentservice.dtos.PaymentDTO;
import org.sid.paymentservice.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Float calculateTotalMontantByStudentId(Long idStudent);
    List<PaymentDTO> getPaymentsByIdStudent(Long idStudent);
    List<PaymentDTO> getPaymentsByIdContinuingEducationAndDateYear(Long idContinuingEducation,int year);
    List<PaymentDTO> getPaymentsByPaymentYear(int year);
    List<PaymentDTO> getPayments();
    PaymentDTO getPaymentById(Long idPayment);
}
