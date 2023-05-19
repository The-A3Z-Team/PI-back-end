package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
}
