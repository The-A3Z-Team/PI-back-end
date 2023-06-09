package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entities.Cash;
import org.sid.paymentservice.repositories.CashRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CashServiceImpl implements CashService{
    private CashRepository cashRepository;

    @Override
    public Cash saveCash(Cash cash) {
        return cashRepository.save(cash);
    }

    @Override
    public Cash updateCash(Long id, Cash cash) {
        Optional<Cash> optionalCash = cashRepository.findById(id);

        Cash existingCash = optionalCash.get();

        existingCash.setMontant(cash.getMontant());
        existingCash.setDate(new Date());
        existingCash.setPaymentProcess(cash.getPaymentProcess());
        existingCash.setStudentEmail(cash.getStudentEmail());

        existingCash.setMontant(cash.getMontant());

        Cash updatedCash = cashRepository.save(existingCash);

        return updatedCash;
    }

    @Override
    public List<Cash> getCashs() {
        return cashRepository.findAll();
    }

    @Override
    public Cash getCashById(Long id) {
        return cashRepository.findById(id).get();
    }

    @Override
    public void deleteCash(Long id) {
        Cash cash=new Cash();
        cash.setId(id);
        cashRepository.delete(cash);
    }

    @Override
    public Cash validateCash(Long id, Boolean isvalide) {
        Optional<Cash> optionalCash = cashRepository.findById(id);

        Cash existingCash = optionalCash.get();

        existingCash.setIsValid(isvalide);

        Cash updatedCash = cashRepository.save(existingCash);

        return updatedCash;
    }

}
