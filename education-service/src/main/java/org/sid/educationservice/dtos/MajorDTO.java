package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorDTO {
    private Long id;
    private String name;
    private String intitule;
}
