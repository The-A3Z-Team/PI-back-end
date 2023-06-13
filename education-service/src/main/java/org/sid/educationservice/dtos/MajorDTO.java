package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.Departement;
import org.sid.educationservice.entities.Education;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MajorDTO {
    private Long id;
    private String name;
    private Long headOfDepartementId;
    private Departement departement;
    private Education education;
}
