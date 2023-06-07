package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Cheque;
import org.sid.paymentservice.repositorys.ChequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeServiceImpl implements ChequeService{
    private ChequeRepository chequeRepository;

    @Override
    public Cheque saveCheque(Cheque cheque) {
        return chequeRepository.save(cheque);
    }

    @Override
    public Cheque updateCheque(Long id, Cheque cheque) {
        Optional<Cheque> optionalcheque = chequeRepository.findById(id);

        Cheque existingcheque = optionalcheque.get();

        existingcheque.setMontant(cheque.getMontant());

        Cheque updatedCheque = chequeRepository.save(existingcheque);

        return updatedCheque;
    }

    @Override
    public List<Cheque> getCheques() {
        return chequeRepository.findAll();
    }

    @Override
    public Cheque getChequeById(Long id) {
        return chequeRepository.findById(id).get();
    }

    @Override
    public void deleteCheque(Long id) {
        Cheque cheque=new Cheque();
        cheque.setId(id);
        chequeRepository.delete(cheque);
    }

    @Override
    public Cheque validateCheque(Long id, Boolean isvalide) {
        Optional<Cheque> optionalCheque = chequeRepository.findById(id);

        Cheque existingCheque = optionalCheque.get();

        existingCheque.setIsValid(isvalide);

        Cheque updatedCheque = chequeRepository.save(existingCheque);

        return updatedCheque;
    }
}
