package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.Major;
import org.sid.educationservice.entities.Semester;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private Long id;
    private Date start_date;
    private int duree;
    private String description;
    private String diplome;
    private List<Major> majors;
    private List<Semester> semesters;
}
