package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.Transfer;

import java.util.List;

public interface TransferService {
    Transfer saveTransfer(Transfer transfer);
    Transfer updateTransfer(Long id,Transfer transfer);
    List<Transfer> getTransfers();
    Transfer getTransferById(Long id);
    void deleteTransfer(Long id);
    public Transfer validateTransfer(Long id, Boolean isvalide);
}
