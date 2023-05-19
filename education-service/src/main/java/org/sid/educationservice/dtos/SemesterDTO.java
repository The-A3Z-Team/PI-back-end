package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.Module;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private Long id;
    private String intitule;
    private List<Module> modules;
}
