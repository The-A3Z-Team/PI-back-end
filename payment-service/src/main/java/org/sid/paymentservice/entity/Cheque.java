package org.sid.paymentservice.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cheque extends Payment{
    private Boolean isWithCheque=true;
}
