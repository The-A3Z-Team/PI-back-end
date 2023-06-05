package org.sid.paymentservice.entity;

import javax.persistence.*;

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
