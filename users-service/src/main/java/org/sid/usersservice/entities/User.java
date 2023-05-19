package org.sid.usersservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("USER")
public class User extends Person {
    @Column(nullable = true)
    private String login;

    @Column(nullable = true)
    private String password;

    @ManyToMany
    private List<Role> roles;

    public void addRole(Role r){
        roles.add(r);
    }
}
