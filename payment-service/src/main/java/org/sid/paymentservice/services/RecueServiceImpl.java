package org.sid.paymentservice.services;

import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.repositorys.RecueRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RecueServiceImpl implements RecueService {
    private final FileService fileService;
    private final RecueRepository recueRepository;

    @Value("${project.image}")
    private String imagePath;

    public RecueServiceImpl(FileService fileService, RecueRepository recueRepository) {
        this.fileService = fileService;
        this.recueRepository = recueRepository;
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
        return filename;
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
}
