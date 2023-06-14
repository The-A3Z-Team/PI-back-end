package org.sid.educationservice.repositories;

import org.sid.educationservice.dtos.MajorDTO;
import org.sid.educationservice.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorRepository extends JpaRepository<Major,Long> {
}
