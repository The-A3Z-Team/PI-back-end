package org.sid.paymentservice.mappers;

import org.sid.paymentservice.dtos.TransferDTO;
import org.sid.paymentservice.entities.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface TransferMapper {
    TransferDTO toTransferDTO(Transfer Transfer);
    List<TransferDTO> toTransferDTOs(List<Transfer> Transfers);
    Transfer toTransfer(TransferDTO TransferDTO);

    List<Transfer> toTransfers(List<TransferDTO> TransferDTOs);
}
