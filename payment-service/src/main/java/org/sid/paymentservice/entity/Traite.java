package org.sid.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.ennumeration.TraiteForme;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Traite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TraiteForme traiteForme;
}
