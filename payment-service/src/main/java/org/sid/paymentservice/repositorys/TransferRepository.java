package org.sid.paymentservice.repositorys;

import org.sid.paymentservice.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

}
