package org.sid.educationservice.repositorys;

import org.sid.educationservice.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
}
