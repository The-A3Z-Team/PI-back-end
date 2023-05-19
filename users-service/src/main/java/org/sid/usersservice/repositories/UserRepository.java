package org.sid.usersservice.repositories;

import org.sid.usersservice.entities.Role;
import org.sid.usersservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    interface RoleRepository extends JpaRepository<Role,Long> {
    }
}
