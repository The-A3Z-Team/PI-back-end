package org.sid.securityservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.securityservice.ennumeration.PaymentProcessDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private float montant;
    private Long idStudent;
    private Long idContinuingEducation;

    @Enumerated(EnumType.STRING)
    private PaymentProcessDTO paymentProcess;

    private Boolean isValid;
}
