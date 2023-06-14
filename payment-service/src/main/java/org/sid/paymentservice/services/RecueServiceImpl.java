package org.sid.paymentservice.services;

import org.apache.commons.lang.StringUtils;
import org.sid.paymentservice.entities.Recue;
import org.sid.paymentservice.entities.Transfer;
import org.sid.paymentservice.repositories.RecueRepository;
import org.sid.paymentservice.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class RecueServiceImpl implements RecueService {
    private FileService fileService;
    private RecueRepository recueRepository;
    private TransferRepository transferRepository;

    @Value("${project.image}")
    private String imagePath;

    public RecueServiceImpl(FileService fileService, RecueRepository recueRepository,TransferRepository transferRepository) {
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
    public String uploadRecue(Long id_transfer, MultipartFile image) throws IOException {
        Transfer transfer = transferRepository.findById(id_transfer).orElse(null);
        if (transfer == null) {
            // Handle case when transfer with the provided ID doesn't exist
            throw new IllegalArgumentException("Invalid transfer ID");
        }

        Recue existingRecue = transfer.getRecue();
        if (existingRecue != null) {
            // If the transfer already has a recue, delete the existing recue file
            deleteRecueFile(existingRecue);
            transfer.setRecue(null);
        }

        String name = image.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        String filePath = imagePath + File.separator + fileName;

        File folder = new File(imagePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Files.copy(image.getInputStream(), Paths.get(filePath));

        Recue recue = Recue.builder()
                .name(fileName)
                .type(image.getContentType())
                .fileData(filePath)
                .build();

        Recue savedRecue = recueRepository.save(recue);

        transfer.setRecue(savedRecue);
        transferRepository.save(transfer);

        return savedRecue.getId().toString();
    }

    private void deleteRecueFile(Recue recue) {
        String filePath = recue.getFileData();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public List<Recue> readRecues() {
        return recueRepository.findAll();
    }

    @Override
    public Resource readRecueById(Long id){
        Recue recue=recueRepository.findById(id).get();
        Path filePath = Paths.get(imagePath + recue.getName());
        return new FileSystemResource(filePath.toFile());
    }

    @Override
    public Resource readRecueByTransferId(Long transferId) {
        Transfer transfer = transferRepository.findById(transferId).orElse(null);
        if (transfer == null) {
            // Handle case when transfer with the provided ID doesn't exist
            throw new IllegalArgumentException("Invalid transfer ID");
        }

        Recue recue = transfer.getRecue();
        if (recue == null || StringUtils.isEmpty(recue.getName())) {
            // Handle case when the transfer does not have a recue or the recue's file name is empty
            return null;
        }

        String filePath = imagePath + recue.getName();
        Path imagePath = Paths.get(filePath);

        if (Files.exists(imagePath) && Files.isReadable(imagePath)) {
            return new FileSystemResource(imagePath.toFile());
        } else {
            // Handle case when the recue file does not exist or is not readable
            return null;
        }
    }

    @Override
    public Recue saveRecue(Recue recue){
        return recueRepository.save(recue);
    }
}
