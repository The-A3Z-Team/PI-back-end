package org.sid.educationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinuingDTO {
    private Long id;
    private Date start_date;
    private int duration;
    private String description;
    private String diploma;
    private Long education_price;
}
