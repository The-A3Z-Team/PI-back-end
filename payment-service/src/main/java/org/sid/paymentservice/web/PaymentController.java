package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<Payment>> getPaymentsByIdStudent(@PathVariable("studentId") Long studentId) {
        List<Payment> payments = paymentService.getPaymentsByIdStudent(studentId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/continuing_education/{educationId}/payments")
    public ResponseEntity<List<Payment>> getPaymentsByIdContinuingEducation(@PathVariable("educationId") Long educationId) {
        List<Payment> payments = paymentService.getPaymentsByIdContinuingEducation(educationId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/year/{year}/payments")
    public ResponseEntity<List<Payment>> getPaymentsByPaymentYear(@PathVariable("year") int year) {
        List<Payment> payments = paymentService.getPaymentsByPaymentYear(year);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> getPaymentsByPaymentYear(@PathVariable("paymentId") Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }
}
