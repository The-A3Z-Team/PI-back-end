package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.entity.Transfer;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TransferService {
    Resource readRecueByName(String filename);

    String uploadRecue(MultipartFile image) throws IOException;

    Transfer updateRecue(Long transferId);

    List<Recue> readRecues();
}
