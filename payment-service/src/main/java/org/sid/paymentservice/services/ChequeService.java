package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Cash;
import org.sid.paymentservice.entity.Cheque;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChequeService {
    Cheque saveCheque(Cheque cheque);
    Cheque updateCheque(Long id,Cheque cheque);
    List<Cheque> getCheques();
    Cheque getChequeById(Long id);
    void deleteCheque(Long id);
    Cheque validateCheque(Long id, Boolean isvalide);
}
