package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.Recue;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface RecueService {
    Resource readRecueByName(String filename);
    String uploadRecue(Long id_transfer,MultipartFile image) throws IOException;
    List<Recue> readRecues();
    Resource readRecueById(Long id);
    public Resource readRecueByTransferId(Long transferId);
}