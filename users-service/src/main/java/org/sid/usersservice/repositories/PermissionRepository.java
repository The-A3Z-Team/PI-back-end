package org.sid.usersservice.repositories;

import org.sid.usersservice.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
