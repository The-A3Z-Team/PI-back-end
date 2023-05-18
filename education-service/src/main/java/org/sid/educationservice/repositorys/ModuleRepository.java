package org.sid.educationservice.repositorys;

import org.sid.educationservice.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    // Custom repository methods, if needed
}
