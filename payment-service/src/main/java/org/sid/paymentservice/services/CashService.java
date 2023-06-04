package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Cash;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CashService {
    Cash saveCash(Cash cash);
    Cash updateCash(Long id,Cash cash);
    List<Cash> getCashs();
    Cash getCashById(Long id);
    void deleteCash(Long id);
}
