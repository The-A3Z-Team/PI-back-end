package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDTO {
    private Long id;
    private Date start_date;
    private int duration;
    private String description;
    private String diploma;
    private double education_price;
}
