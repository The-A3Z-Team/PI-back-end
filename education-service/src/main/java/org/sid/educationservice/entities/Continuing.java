package org.sid.educationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Continuing extends Education{
    private Boolean isContinue;
    private Long education_price;

    @JsonIgnore
    @OneToMany(mappedBy = "continuing")
    private List<Major> majors;
}
