package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.dtos.TransferDTO;
import org.sid.paymentservice.ennumerations.PaymentProcess;
import org.sid.paymentservice.entities.Cash;
import org.sid.paymentservice.entities.Image;
import org.sid.paymentservice.entities.Recue;
import org.sid.paymentservice.entities.Transfer;
import org.sid.paymentservice.repositories.RecueRepository;
import org.sid.paymentservice.repositories.TransferRepository;
import org.sid.paymentservice.services.StorageService;
import org.sid.paymentservice.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transfer")
@CrossOrigin("*")
@AllArgsConstructor
public class TransferController {
    @Autowired
    private TransferService transferService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private RecueRepository recueRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    @PostMapping()
    public ResponseEntity<String> makeTransferPayment(@RequestParam("montant") Float montant,
                                                      @RequestParam("idStudent") Long idStudent,
                                                      @RequestParam("idContinuingEducation") Long idContinuingEducation,
                                                      @RequestParam("paymentProcess") String paymentProcess,
                                                      @RequestParam("recueImage") MultipartFile recueImage) {
        try {
            // Save Recue
            Recue recue = new Recue();
            Recue savedRecue = recueRepository.save(recue);

            // Upload Recue Image
            String recueUploadResult = storageService.uploadImage(recueImage, savedRecue.getId());
            if (!recueUploadResult.equals("File uploaded successfully")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recue image upload failed");
            }

            // Create Transfer and associate it with the Recue
            Transfer transfer = new Transfer();
            transfer.setDate(new Date());
            transfer.setIdStudent(idStudent);
            transfer.setIdContinuingEducation(idContinuingEducation);
            transfer.setPaymentProcess(PaymentProcess.valueOf(paymentProcess));
            transfer.setMontant(montant);
            transfer.setRecue(savedRecue);
            Transfer savedTransfer = transferRepository.save(transfer);

            // Update the Recue with the correct Transfer ID
            savedRecue.setTransfer(savedTransfer);
            recueRepository.save(savedRecue);

            return ResponseEntity.status(HttpStatus.OK).body("Payment successful");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during payment");
        }
    }


    @GetMapping("/{transferId}/recue")
    public ResponseEntity<?> getRecueByTransferId(@PathVariable Long transferId) {
        Optional<Recue> optionalRecue = recueRepository.getRecueByTransferId(transferId);
        if (optionalRecue.isPresent()) {
            Recue recue = optionalRecue.get();
            Image image = recue.getImage();

            // Build the response object with recue information and image path
            RecueInfoResponse recueInfo = new RecueInfoResponse();
            recueInfo.setId(recue.getId());
            recueInfo.setName(recue.getName());
            recueInfo.setImagePath(getRecueImagePath(recue.getImage().getName()));

            return ResponseEntity.status(HttpStatus.OK).body(recueInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recue not found");
        }
    }

    private String getRecueImagePath(String recueImageName) {
        return System.getProperty("user.dir")+"\\uploads" + "\\" + recueImageName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class RecueInfoResponse {
        private Long id;
        private String name;
        private String imagePath;
    }

    @GetMapping("")
    public ResponseEntity<List<TransferDTO>> getAllCashs() {
        List<TransferDTO> transfers = transferService.getTransfers();
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable Long id) {
        TransferDTO transfer = transferService.getTransferById(id);

        if (transfer != null) {
            return ResponseEntity.ok(transfer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        TransferDTO updatedTransfer = transferService.updateTransfer(id, transfer);
        if (updatedTransfer != null) {
            return new ResponseEntity<>(updatedTransfer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransfer(@PathVariable Long id) {
        transferService.deleteTransfer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/validate/{id}")
    public ResponseEntity<TransferDTO> validateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        TransferDTO updatedTransfer = transferService.validateTransfer(id, transfer.getIsValid());
        return new ResponseEntity<>(updatedTransfer, HttpStatus.OK);
    }
}
