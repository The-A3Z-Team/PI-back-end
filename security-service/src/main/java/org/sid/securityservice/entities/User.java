package org.sid.securityservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.securityservice.config.PasswordEncoding;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Person{
    private String username;
    private String email;
    private String password = /* This is the default password {1234} */ new PasswordEncoding().getEncodedPassword("1234");

    //Link any user with the major
    private Long idMajor;

    //Link between Student and (Major,Education,Head of departement)
    private Long idMajorOfStudent;
    private Long idEducationOfStudent;
    private Long idHeadOfDepartementManagerOfStudent;

    //Link between Head of departement and (Major)
    private Long idMajorOfHeadOfDepartement;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
