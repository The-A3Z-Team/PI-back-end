package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElementDTO {
    private Long id;
    private String name;
    private Long nbrHours;
}
