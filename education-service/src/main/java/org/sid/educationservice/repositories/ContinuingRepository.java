package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.ContinuingEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinuingRepository extends JpaRepository<ContinuingEducation,Long> {
}
