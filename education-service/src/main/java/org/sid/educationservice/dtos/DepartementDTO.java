package org.sid.educationservice.dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.Major;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDTO {
    private Long id;
    private String name;
    private String intitule;
    private List<Major> majors;
}
