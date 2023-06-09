package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

}
