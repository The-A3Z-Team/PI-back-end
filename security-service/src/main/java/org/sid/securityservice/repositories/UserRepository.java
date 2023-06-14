package org.sid.securityservice.repositories;

import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
  List<User> findByIdMajor(Long idMajor);

  @Query("SELECT u FROM User u JOIN u.roles r " +
          "WHERE (u.firstName LIKE %:keyword% OR " +
          "u.lastName LIKE %:keyword% OR " +
          "u.phone LIKE %:keyword% OR " +
          "u.adresse LIKE %:keyword% OR " +
          "u.nationality LIKE %:keyword% OR " +
          "u.cne LIKE %:keyword% OR " +
          "u.cni LIKE %:keyword% OR " +
          "u.username LIKE %:keyword% OR " +
          "u.email LIKE %:keyword%) AND " +
          "r.name = :roleName")
  List<User> getUsersBykeywordAndRole(String keyword, ERole roleName);

}
