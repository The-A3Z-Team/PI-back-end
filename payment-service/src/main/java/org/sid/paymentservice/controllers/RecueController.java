package org.sid.paymentservice.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.repositorys.RecueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/recues")
@AllArgsConstructor
@NoArgsConstructor
public class RecueController {
    @Autowired
    private RecueRepository recueRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public ResponseEntity<List<Recue>> getAllRecues() {
        List<Recue> recues = recueRepository.findAll();
        return new ResponseEntity<>(recues, HttpStatus.OK);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> readByFileName(@PathVariable("filename") String filename) {
        String projectImage = environment.getProperty("project.image");
        Path filePath = Paths.get(projectImage + filename);
        Resource resource = new FileSystemResource(filePath.toFile());

        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
