package org.sid.securityservice.repositories;

import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  User getUserByEmail(String email);
  List<User> findByRoles_Name(ERole roleName);
  List<User> findByIdMajor(Long idMojor);
}
