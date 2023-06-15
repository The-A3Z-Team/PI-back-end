package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.dtos.PaymentDTO;
import org.sid.paymentservice.entities.Payment;
import org.sid.paymentservice.mappers.PaymentMapper;
import org.sid.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    private static final String STUDENT_SERVICE_URL = "http://localhost:8888/authentification/student";

    @Override
    public Float calculateTotalMontantByStudentId(Long idStudent) {
        return paymentRepository.calculateTotalMontantByStudentId(idStudent);
    }

    @Override
    public List<PaymentDTO> getPaymentsByIdStudent(Long idStudent) {
        List<Payment> payments = paymentRepository.getPaymentsByIdStudent(idStudent);
        return payments.stream()
                .map(paymentMapper::toPaymentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByIdContinuingEducationAndDateYear(Long idContinuingEducation, int year) {
        List<Payment> payments = paymentRepository.getPaymentsByIdContinuingEducationAndDateYear(idContinuingEducation, year);
        return payments.stream()
                .map(paymentMapper::toPaymentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByPaymentYear(int year) {
        List<Payment> payments = paymentRepository.getPaymentsByPaymentYear(year);
        return payments.stream()
                .map(paymentMapper::toPaymentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long idPayment) {
        Payment payment = paymentRepository.getPaymentById(idPayment);
        return paymentMapper.toPaymentDTO(payment);
    }

    @Override
    public List<PaymentDTO> getPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::toPaymentDTO)
                .collect(Collectors.toList());
    }
}
