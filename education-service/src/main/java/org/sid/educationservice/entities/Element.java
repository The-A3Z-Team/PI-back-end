package org.sid.educationservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long nbrHours;
}
