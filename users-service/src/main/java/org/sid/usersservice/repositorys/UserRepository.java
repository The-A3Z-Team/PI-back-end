package org.sid.usersservice.repositorys;

import org.sid.usersservice.entity.Role;
import org.sid.usersservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    interface RoleRepository extends JpaRepository<Role,Long> {
    }
}
