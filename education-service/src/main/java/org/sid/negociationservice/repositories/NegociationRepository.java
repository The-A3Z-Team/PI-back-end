package org.sid.negociationservice.repositories;

import org.sid.negociationservice.entity.Negociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegociationRepository extends JpaRepository<Negociation,Long> {
}