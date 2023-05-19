package org.sid.usersservice.repositories;

import org.sid.usersservice.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
