package org.sid.negociationservice.repositorys;

import org.sid.negociationservice.entity.Negociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegociationRepository extends JpaRepository<Negociation,Long> {
}
