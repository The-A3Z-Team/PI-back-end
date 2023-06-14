package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.Cash;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CashService {
    Cash saveCash(Cash cash);
    Cash updateCash(Long id,Cash cash);
    List<Cash> getCashs();
    Cash getCashById(Long id);
    void deleteCash(Long id);
    Cash validateCash(Long id,Boolean isvalide);
    Float calculateTotalMontantByStudentId(Long idStudent);
}
