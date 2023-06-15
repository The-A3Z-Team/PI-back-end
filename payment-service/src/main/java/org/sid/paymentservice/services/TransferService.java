package org.sid.paymentservice.services;

import org.sid.paymentservice.dtos.TransferDTO;
import org.sid.paymentservice.entities.Transfer;

import java.util.List;

public interface TransferService {
    TransferDTO saveTransfer(Transfer transfer);
    TransferDTO updateTransfer(Long id,Transfer transfer);
    List<TransferDTO> getTransfers();
    TransferDTO getTransferById(Long id);
    void deleteTransfer(Long id);
    TransferDTO validateTransfer(Long id, Boolean isvalide);
}
