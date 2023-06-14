package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartementDTO {
    private Long id;
    private String name;
    private String intitule;
    private List<MajorDTO> majors;
}
