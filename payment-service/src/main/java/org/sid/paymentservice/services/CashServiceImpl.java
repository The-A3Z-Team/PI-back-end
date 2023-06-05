package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Cash;
import org.sid.paymentservice.repositorys.CashRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
}