package org.sid.paymentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer extends Payment {
    private Boolean isWithTransfer = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recue_id")
    private Recue recue;
}
