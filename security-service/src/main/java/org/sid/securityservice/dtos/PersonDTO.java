package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.securityservice.ennumeration.Gender;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDTO {
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
