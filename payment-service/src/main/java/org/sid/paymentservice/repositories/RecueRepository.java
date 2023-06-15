package org.sid.paymentservice.repositories;

import org.sid.paymentservice.entities.Recue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecueRepository extends JpaRepository<Recue, Long> {
    Optional<Recue> findByName(String name);
    Optional<Recue> getRecueByTransferId(Long transferId);
}
