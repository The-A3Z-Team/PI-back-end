package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entities.Transfer;
import org.sid.paymentservice.repositories.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;

    @Override
    public Transfer saveTransfer(Transfer transfer) {
        transfer.setDate(new Date());
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer updateTransfer(Long id, Transfer transfer) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);

        Transfer existingTransfer = optionalTransfer.get();

        existingTransfer.setMontant(transfer.getMontant());
        existingTransfer.setDate(new Date());
        existingTransfer.setPaymentProcess(transfer.getPaymentProcess());
        existingTransfer.setIdStudent(transfer.getIdStudent());

        existingTransfer.setMontant(transfer.getMontant());

        Transfer updatedTransfer = transferRepository.save(existingTransfer);

        return updatedTransfer;
    }

    @Override
    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer getTransferById(Long id) {
        return transferRepository.findById(id).get();
    }

    @Override
    public void deleteTransfer(Long id) {
        Transfer transfer=new Transfer();
        transfer.setId(id);
        transferRepository.delete(transfer);
    }

    @Override
    public Transfer validateTransfer(Long id, Boolean isvalide) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);
        Transfer existingTransfer = optionalTransfer.get();
        existingTransfer.setIsValid(isvalide);
        Transfer updatedTransfer = transferRepository.save(existingTransfer);
        return updatedTransfer;
    }
}
