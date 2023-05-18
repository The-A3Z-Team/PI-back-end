package org.sid.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer extends Payment{
    private Boolean isWithTransfer=true;
    @OneToOne
    private Recue recue;
}
