package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.dtos.TransferDTO;
import org.sid.paymentservice.entities.Transfer;
import org.sid.paymentservice.mappers.TransferMapper;
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
    private TransferMapper transferMapper;

    @Override
    public TransferDTO saveTransfer(Transfer transfer) {
        transfer.setDate(new Date());
        return transferMapper.toTransferDTO(transferRepository.save(transfer));
    }

    @Override
    public TransferDTO updateTransfer(Long id, Transfer transfer) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);

        Transfer existingTransfer = optionalTransfer.get();

        existingTransfer.setMontant(transfer.getMontant());
        existingTransfer.setDate(new Date());
        existingTransfer.setPaymentProcess(transfer.getPaymentProcess());
        existingTransfer.setIdStudent(transfer.getIdStudent());

        existingTransfer.setMontant(transfer.getMontant());

        Transfer updatedTransfer = transferRepository.save(existingTransfer);

        return transferMapper.toTransferDTO(updatedTransfer);
    }

    @Override
    public List<TransferDTO> getTransfers() {
        return transferMapper.toTransferDTOs(transferRepository.findAll());
    }

    @Override
    public TransferDTO getTransferById(Long id) {
        return transferMapper.toTransferDTO(transferRepository.findById(id).get());
    }

    @Override
    public void deleteTransfer(Long id) {
        Transfer transfer=new Transfer();
        transfer.setId(id);
        transferRepository.delete(transfer);
    }

    @Override
    public TransferDTO validateTransfer(Long id, Boolean isvalide) {
        System.out.println(isvalide);
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);
        Transfer existingTransfer = optionalTransfer.get();
        existingTransfer.setIsValid(isvalide);
        Transfer updatedTransfer = transferRepository.save(existingTransfer);
        return transferMapper.toTransferDTO(updatedTransfer);
    }
}
