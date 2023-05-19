package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Education;
import org.sid.educationservice.entities.InitialEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitialRepository extends JpaRepository<InitialEducation, Education> {
}
