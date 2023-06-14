package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MajorResponseDTO {
    private Long id;
    private String name;
    private Long headOfDepartementId;
    private DepartementDTO departement;
    private EducationDTO education;
}
