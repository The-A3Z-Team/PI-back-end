package org.sid.educationservice.repositories;

import org.sid.educationservice.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepositort extends JpaRepository<Module,Long> {
}
