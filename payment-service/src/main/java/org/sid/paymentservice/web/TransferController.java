package org.sid.paymentservice.web;

import org.sid.paymentservice.entity.Recue;
import org.sid.paymentservice.entity.Transfer;
import org.sid.paymentservice.repositorys.TransferRepository;
import org.sid.paymentservice.services.TransferService;
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
@RequestMapping("/api/payments/transfer")
@CrossOrigin("*")
public class TransferController {
    private final TransferRepository transferRepository;
    private final TransferService transferService;

    @Autowired
    public TransferController(TransferRepository transferRepository, TransferService transferService) {
        this.transferRepository = transferRepository;
        this.transferService = transferService;
    }

    @GetMapping("/")
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @GetMapping("/recues")
    public List<Recue> getAllRecues() {
        return transferService.readRecues();
    }

    @GetMapping("/recue/{filename}")
    public ResponseEntity<Resource> getRecueByName(@PathVariable String filename) {
        Resource resource = transferService.readRecueByName(filename);

        if (resource != null && resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/recue")
    public ResponseEntity<String> uploadRecue(@RequestParam("image") MultipartFile image) {
        try {
            String filename = transferService.uploadRecue(image);
            return ResponseEntity.ok().body(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }


    @PutMapping("/recue/{transferId}")
    public ResponseEntity<Transfer> updateRecue(@PathVariable Long transferId) {
        Transfer transfer = transferService.updateRecue(transferId);
        if (transfer != null) {
            return ResponseEntity.ok().body(transfer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
