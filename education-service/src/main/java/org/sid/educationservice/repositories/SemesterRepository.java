package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
}
