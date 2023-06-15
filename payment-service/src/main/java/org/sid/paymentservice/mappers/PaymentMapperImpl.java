package org.sid.paymentservice.mappers;

import org.sid.paymentservice.dtos.PaymentDTO;
import org.sid.paymentservice.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDTO toPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setDate(payment.getDate());
        paymentDTO.setMontant(payment.getMontant());
        paymentDTO.setIdStudent(payment.getIdStudent());
        paymentDTO.setIdContinuingEducation(payment.getIdContinuingEducation());
        return paymentDTO;
    }

    @Override
    public List<PaymentDTO> toPaymentDTOs(List<Payment> payments) {
        return payments.stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Payment toPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setDate(paymentDTO.getDate());
        payment.setMontant(paymentDTO.getMontant());
        payment.setIdStudent(paymentDTO.getIdStudent());
        payment.setIdContinuingEducation(paymentDTO.getIdContinuingEducation());
        payment.setIsValid(paymentDTO.getIsValid());
        return payment;
    }

    @Override
    public List<Payment> toPayments(List<PaymentDTO> paymentDTOs) {
        return paymentDTOs.stream()
                .map(this::toPayment)
                .collect(Collectors.toList());
    }
}
