package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    // Custom repository methods, if needed
}
