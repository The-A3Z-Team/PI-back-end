package org.sid.paymentservice.services;

import org.sid.paymentservice.dtos.FileResponse;
import org.sid.paymentservice.entity.Recue;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface RecueService {
    Resource readRecueByName(String filename);
    String uploadRecue(MultipartFile image) throws IOException;
    List<Recue> readRecues();
}