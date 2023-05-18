package org.sid.usersservice.repositorys;

import org.sid.usersservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
