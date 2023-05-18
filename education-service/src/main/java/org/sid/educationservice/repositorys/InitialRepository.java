package org.sid.educationservice.repositorys;

import org.sid.educationservice.entity.Education;
import org.sid.educationservice.entity.Initial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitialRepository extends JpaRepository<Initial, Education> {
}
