package org.sid.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Date date;
    private float montant;
    private Long idStudent;
    private Long idContinuingEducation;
    private Boolean isValid;
    private Boolean isWithCash;
    private Boolean isWithCheque;
    private Boolean isWithTransfer;
}
