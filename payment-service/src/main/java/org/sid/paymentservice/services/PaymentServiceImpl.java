package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entities.Payment;
import org.sid.paymentservice.entities.UserResponse;
import org.sid.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    private static final String STUDENT_SERVICE_URL = "http://localhost:8888/authentification/student";
    @Override
    public Float calculateTotalMontantByStudentId(Long idStudent) {
        return paymentRepository.calculateTotalMontantByStudentId(idStudent);
    }
    @Override
    public List<Payment> getPaymentsByIdStudent(Long idStudent) {
        return paymentRepository.getPaymentsByIdStudent(idStudent);
    }

    @Override
    public List<Payment> getPaymentsByIdContinuingEducation(Long idContinuingEducation) {
        return paymentRepository.getPaymentsByIdContinuingEducation(idContinuingEducation);
    }

}
