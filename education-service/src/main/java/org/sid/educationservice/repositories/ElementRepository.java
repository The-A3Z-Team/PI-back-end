package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element,Long> {
}
