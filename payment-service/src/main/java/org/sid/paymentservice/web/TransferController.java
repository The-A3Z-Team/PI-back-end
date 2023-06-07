package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entity.Transfer;
import org.sid.paymentservice.services.RecueService;
import org.sid.paymentservice.services.TransferService;
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
@RequestMapping("/transfer")
@CrossOrigin("*")
@AllArgsConstructor
public class TransferController {
    private TransferService transferService;
    private RecueService recueService;

    @GetMapping(value = "/recue/{filename}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> getRecueByName(@PathVariable String filename) {
        Resource resource = recueService.readRecueByName(filename);

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
            String filename = recueService.uploadRecue(image);
            return ResponseEntity.ok().body(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    @PostMapping("/")
    public ResponseEntity<Transfer> saveTransfer(@RequestBody Transfer transfer) {
        Transfer savedTransfer = transferService.saveTransfer(transfer);
        return ResponseEntity.ok(savedTransfer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        Transfer updatedTransfer = transferService.updateTransfer(id, transfer);
        return ResponseEntity.ok(updatedTransfer);
    }

    @GetMapping("/")
    public ResponseEntity<List<Transfer>> getTransfers() {
        List<Transfer> transfers = transferService.getTransfers();
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable Long id) {
        Transfer transfer = transferService.getTransferById(id);
        return ResponseEntity.ok(transfer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<Transfer> validateTransfer(@PathVariable Long id, @RequestParam("isValid") Boolean isValid) {
        Transfer validatedTransfer = transferService.validateTransfer(id, isValid);
        return ResponseEntity.ok(validatedTransfer);
    }

}
