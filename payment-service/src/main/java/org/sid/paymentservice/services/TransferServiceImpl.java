package org.sid.paymentservice.services;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.entity.Transfer;
import org.sid.paymentservice.repositorys.RecueRepository;
import org.sid.paymentservice.repositorys.TransferRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    private final FileService fileService;
    private final RecueRepository recueRepository;
    private final TransferRepository transferRepository;

    @Value("${project.image}")
    private String imagePath;

    public TransferServiceImpl(FileService fileService, RecueRepository recueRepository, TransferRepository transferRepository) {
        this.fileService = fileService;
        this.recueRepository = recueRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public Resource readRecueByName(String filename) {
        Path filePath = Paths.get(imagePath + filename);
        return new FileSystemResource(filePath.toFile());
    }

    @Override
    public String uploadRecue(MultipartFile image) throws IOException {
        String filename = fileService.uploadImage(imagePath, image);
        Recue r = new Recue();
        r.setName(filename);
        r.setFile(image);
        recueRepository.save(r);

        Transfer transfer = new Transfer();
        transfer.setRecue(r);
        transferRepository.save(transfer);
        return filename;
    }

    @Override
    public List<Recue> readRecues() {
        return recueRepository.findAll();
    }

    @Override
    @Transactional
    public Transfer updateRecue(Long transferId) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transfer ID: " + transferId));

        Recue recue = transfer.getRecue();
        recue.setName("Updated Recue"); // Update the recue properties as needed
        recueRepository.save(recue);

        return transfer;
    }
}
