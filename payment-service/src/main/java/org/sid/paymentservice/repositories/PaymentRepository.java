package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment getPaymentById(Long idPayment);
    List<Payment> getPaymentsByIdStudent(Long idStudent);
    List<Payment> getPaymentsByIdContinuingEducation(Long idContinuingEducation);

    @Query("SELECT SUM(p.montant) FROM Payment p WHERE p.idStudent = :idStudent")
    Float calculateTotalMontantByStudentId(@Param("idStudent") Long idStudent);

    @Query("SELECT p FROM Payment p WHERE YEAR(p.date) = :year")
    List<Payment> getPaymentsByPaymentYear(@Param("year") int year);
}
