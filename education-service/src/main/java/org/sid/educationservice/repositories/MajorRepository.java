package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major,Long> {
    List<Major> getMajorByHeadOfDepartementId(Long id);
}
