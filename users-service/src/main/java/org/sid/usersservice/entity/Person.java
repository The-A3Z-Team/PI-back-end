package org.sid.usersservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.usersservice.enumeration.Gender;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private Image imageProfile;

    private String email;
    private String phone;
    private String address;
    private String nationality;
    private String cne;
}
