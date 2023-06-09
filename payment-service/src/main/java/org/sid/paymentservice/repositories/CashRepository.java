package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<Cash,Long> {
}
