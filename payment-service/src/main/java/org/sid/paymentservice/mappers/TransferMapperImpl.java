package org.sid.paymentservice.mappers;


import org.sid.paymentservice.dtos.TransferDTO;
import org.sid.paymentservice.entities.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferMapperImpl implements TransferMapper{

    @Override
    public TransferDTO toTransferDTO(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setId(transfer.getId());
        transferDTO.setDate(transfer.getDate());
        transferDTO.setMontant(transfer.getMontant());
        transferDTO.setIdStudent(transfer.getIdStudent());
        transferDTO.setIdContinuingEducation(transfer.getIdContinuingEducation());
        transferDTO.setIsValid(transfer.getIsValid());
        return transferDTO;
    }

    @Override
    public List<TransferDTO> toTransferDTOs(List<Transfer> Transfers) {
        return Transfers.stream()
                .map(this::toTransferDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Transfer toTransfer(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        transfer.setId(transferDTO.getId());
        transfer.setDate(transferDTO.getDate());
        transfer.setMontant(transferDTO.getMontant());
        transfer.setIdStudent(transferDTO.getIdStudent());
        transfer.setIdContinuingEducation(transferDTO.getIdContinuingEducation());
        transfer.setIsValid(transferDTO.getIsValid());
        return transfer;
    }

    @Override
    public List<Transfer> toTransfers(List<TransferDTO> TransferDTOs) {
        return TransferDTOs.stream()
                .map(this::toTransfer)
                .collect(Collectors.toList());
    }
}
