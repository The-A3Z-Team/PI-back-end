package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.securityservice.ennumeration.Gender;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
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
    private String username;
    private String email;
    private String error;
    Set<RoleDTO> roleDTOList;

    //Link between Student and (Major,Education,Head of departement)
    private Long idMajorOfStudent;
    private Long idEducationOfStudent;
    private Long idHeadOfDepartementManagerOfStudent;

    //Link between Head of departement and (Major)
    private Long idMajorOfHeadOfDepartement;


    public UserResponseDTO(String error) {
        this.error=error;
    }
}
