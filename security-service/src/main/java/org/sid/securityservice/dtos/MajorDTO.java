package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorDTO {
    private Long id;
    private String name;
    private Long headOfDepartementId;
}
