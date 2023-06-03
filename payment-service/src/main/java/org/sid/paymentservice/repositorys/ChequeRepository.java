package org.sid.paymentservice.repositorys;

import org.sid.paymentservice.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque,Long> {
}
