package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Cheque;
import org.sid.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque,Long> {
    List<Payment> getPaymentsByIdStudent(Long idStudent);
    List<Payment> getPaymentsByIdContinuingEducation(Long idContinuingEducation);
    @Query("SELECT SUM(p.montant) FROM Payment p WHERE p.idStudent = :idStudent")
    Float calculateTotalMontantByStudentId(@Param("idStudent") Long idStudent);
}
