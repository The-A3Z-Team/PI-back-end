package org.sid.paymentservice.mappers;

import org.sid.paymentservice.dtos.PaymentDTO;
import org.sid.paymentservice.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PaymentMapper {
    PaymentDTO toPaymentDTO(Payment payment);
    List<PaymentDTO> toPaymentDTOs(List<Payment> payments);
    Payment toPayment(PaymentDTO paymentDTO);

    List<Payment> toPayments(List<PaymentDTO> paymentDTOs);
}
