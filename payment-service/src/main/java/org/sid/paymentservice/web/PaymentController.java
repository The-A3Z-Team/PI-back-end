package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.dtos.PaymentDTO;
import org.sid.paymentservice.entities.Payment;
import org.sid.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;
    @GetMapping("/student/{studentId}/total_montant")
    public ResponseEntity<Float> calculateTotalMontantByStudentId(@PathVariable("studentId") Long studentId) {
        Float totalMontant = paymentService.calculateTotalMontantByStudentId(studentId);
        return ResponseEntity.ok(totalMontant);
    }

    @GetMapping("/student/{studentId}/payments")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByIdStudent(@PathVariable("studentId") Long studentId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByIdStudent(studentId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/education/{educationId}/payments/{year}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByIdContinuingEducation(@PathVariable("educationId") Long educationId,@PathVariable("year") int year) {
        List<PaymentDTO> payments = paymentService.getPaymentsByIdContinuingEducationAndDateYear(educationId,year);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/year/{year}/payments")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByPaymentYear(@PathVariable("year") int year) {
        List<PaymentDTO> payments = paymentService.getPaymentsByPaymentYear(year);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        List<PaymentDTO> payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("paymentId") Long paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }
}
