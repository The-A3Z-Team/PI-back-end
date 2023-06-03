package org.sid.paymentservice.web;

import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.services.RecueService;
import org.sid.paymentservice.dtos.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recues")
public class RecueController {
    private final RecueService recueService;

    @Autowired
    public RecueController(RecueService recueService) {
        this.recueService = recueService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Recue>> getAllRecues() {
        List<Recue> recues = recueService.readRecues();
        return new ResponseEntity<>(recues, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadRecue(@RequestParam("image") MultipartFile image) {
        try {
            String filename = recueService.uploadRecue(image);
            FileResponse response = new FileResponse(filename, "Image is successfully uploaded");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            FileResponse response = new FileResponse(null, "Image is not uploaded");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{filename}")
    public ResponseEntity<Resource> readRecueByName(@PathVariable("filename") String filename) {
        Resource resource = recueService.readRecueByName(filename);

        if (resource != null && resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
