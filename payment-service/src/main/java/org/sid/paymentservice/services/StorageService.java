package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.Image;
import org.sid.paymentservice.entities.Recue;
import org.sid.paymentservice.repositories.ImageRepository;
import org.sid.paymentservice.repositories.RecueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RecueRepository recueRepository;

    public String uploadImage(MultipartFile file, Long recueId) throws IOException {
        Optional<Recue> optionalRecue = recueRepository.findById(recueId);
        if (optionalRecue.isEmpty()) {
            return "Recue not found";
        }

        Recue recue = optionalRecue.get();
        Image image = new Image();
        String fileName = recueId + "_" + file.getOriginalFilename();
        image.setName(fileName); // Use the combined file name as the image name in the database
        image.setType(file.getContentType());
        image.setImageData(file.getBytes());
        image.setRecue(recue);

        recue.setImage(image);
        recueRepository.save(recue);

        // Create the uploads directory if it doesn't exist
        Path uploadPath = Path.of("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Store the image file in the uploads directory
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "File uploaded successfully";
    }

    public byte[] downloadImage(Long recueId) {
        Optional<Recue> optionalRecue = recueRepository.findById(recueId);
        if (optionalRecue.isPresent()) {
            Image image = optionalRecue.get().getImage();
            return image.getImageData();
        }
        return null;
    }

    // Other methods...
}
