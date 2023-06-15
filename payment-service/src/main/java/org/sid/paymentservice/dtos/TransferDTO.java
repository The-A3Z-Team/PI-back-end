package org.sid.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
    private Long id;
    private Date date;
    private float montant;
    private Long idStudent;
    private Long idContinuingEducation;
    private Boolean isValid=false;
    private Boolean isWithTransfer = true;
}
