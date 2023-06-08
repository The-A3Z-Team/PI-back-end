package org.sid.paymentservice.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.ennumeration.PaymentProcess;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private float montant;
    private String studentEmail;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'NORMAL'")
    private PaymentProcess paymentProcess;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isValid=false;
}
