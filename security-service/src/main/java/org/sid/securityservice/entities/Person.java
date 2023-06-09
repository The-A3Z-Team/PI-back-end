package org.sid.securityservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.securityservice.ennumeration.Gender;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private Gender gender;
    private String phone;
    private String adresse;
    private String nationality;
    private String cne;
    private String cni;
}
