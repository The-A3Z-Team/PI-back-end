package org.sid.paymentservice.repositorys;

import org.sid.paymentservice.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<Cash,Long> {
}
