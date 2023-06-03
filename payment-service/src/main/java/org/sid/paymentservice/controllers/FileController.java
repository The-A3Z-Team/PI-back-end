package org.sid.paymentservice.controllers;

import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.file_storage.FileService;
import org.sid.paymentservice.playload.FileResponse;
import org.sid.paymentservice.repositorys.RecueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private RecueRepository recueRepository;
    private final FileService fileService;
    private final String path;

    @Autowired
    public FileController(FileService fileService, @Value("${project.image}") String path) {
        this.fileService = fileService;
        this.path = path;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image) {
        String filename = null;
        try {
            filename = fileService.uploadImage(path, image);
            Recue r=new Recue();
            r.setName(filename);
            r.setFile(image);
            recueRepository.save(r);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Image is not uploaded"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(filename, "Image is successfully uploaded"), HttpStatus.OK);
    }
}
