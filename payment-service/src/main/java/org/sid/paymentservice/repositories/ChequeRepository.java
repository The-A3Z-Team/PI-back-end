package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque,Long> {
}
