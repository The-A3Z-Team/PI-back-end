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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @GetMapping("/recue/{transferId}")
    public ResponseEntity<Resource> getRecueByTransferId(@PathVariable Long transferId) {
        Resource resource = recueService.readRecueByTransferId(transferId);

        if (resource != null && resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PostMapping("/recue/{id_transfer}")
    public ResponseEntity<String> uploadRecue(@PathVariable Long id_transfer, @RequestParam("image") MultipartFile image) {
        try {
            String filename = recueService.uploadRecue(id_transfer, image);
            return ResponseEntity.ok().body(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PostMapping("")
    public ResponseEntity<Transfer> saveTransfer(@RequestBody Transfer transfer) {
        Transfer savedTransfer = transferService.saveTransfer(transfer);
        return ResponseEntity.ok(savedTransfer);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        Transfer updatedTransfer = transferService.updateTransfer(id, transfer);
        return ResponseEntity.ok(updatedTransfer);
    }

    //@PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @GetMapping("")
    public ResponseEntity<List<Transfer>> getTransfers() {
        List<Transfer> transfers = transferService.getTransfers();
        return ResponseEntity.ok(transfers);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable Long id) {
        Transfer transfer = transferService.getTransferById(id);
        return ResponseEntity.ok(transfer);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @PostMapping("/{id}/validate")
    public ResponseEntity<Transfer> validateTransfer(@PathVariable Long id, @RequestParam("isValid") Boolean isValid) {
        Transfer validatedTransfer = transferService.validateTransfer(id, isValid);
        return ResponseEntity.ok(validatedTransfer);
    }
}
