package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    //Student getStudentByPayment(String codeStudent);
    public UserResponse getStudentByPayment(String token, String email);
}
