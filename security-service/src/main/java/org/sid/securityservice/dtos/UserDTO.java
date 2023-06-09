package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO extends PersonDTO{
    private String username;
    private String email;

    Set<RoleDTO> roleDTOList;
}
