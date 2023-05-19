package org.sid.usersservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.usersservice.entities.Image;
import org.sid.usersservice.enums.Gender;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private Gender gender;
    private Image imageProfile;
    private String email;
    private String phone;
    private String address;
    private String nationality;
    private String cne;
    private String login;
    private String password;
}

