package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.Element;
import org.sid.educationservice.entities.Semester;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {
    private Long id;
    private String name;
    private List<Element> elements;
    private List<Semester> semesters;
}
