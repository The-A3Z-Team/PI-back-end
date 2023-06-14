package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.ennumerations.PaymentProcess;
import org.sid.paymentservice.entities.Payment;
import org.sid.paymentservice.entities.Recue;
import org.sid.paymentservice.entities.Transfer;
import org.sid.paymentservice.services.RecueService;
import org.sid.paymentservice.services.TransferService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin("*")
@AllArgsConstructor
public class TransferController {
    private TransferService transferService;
    private RecueService recueService;

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

    @PostMapping("")
    public ResponseEntity<Transfer> saveTransferWithRecue(MultipartHttpServletRequest request) throws IOException {
        MultipartFile image = request.getFile("image");
        Payment payment = extractPaymentFromRequest(request);

        Transfer transfer = new Transfer();
        transfer.setIsWithTransfer(true);

        Recue recue = new Recue();
        try{

        } catch (Error e){
            System.out.println();
        }
        recue.setName(image.getOriginalFilename());
        recue = recueService.saveRecue(recue);
        String filename = recueService.uploadRecue(recue.getId(), image);
        recue.setName(filename);

        transfer.setRecue(recue);

        transfer.setMontant(payment.getMontant());
        transfer.setIdStudent(payment.getIdStudent());
        transfer.setPaymentProcess(payment.getPaymentProcess());
        transfer.setIdContinuingEducation(payment.getIdContinuingEducation());

        transfer.setDate(new Date());

        Transfer savedTransfer = transferService.saveTransfer(transfer);

        return ResponseEntity.ok(savedTransfer);
    }



    private Payment extractPaymentFromRequest(MultipartHttpServletRequest request) {
        Payment payment = new Payment();
        payment.setMontant(Float.parseFloat(request.getParameter("montant")));
        payment.setIdStudent(Long.parseLong(request.getParameter("idStudent")));
        payment.setIdContinuingEducation(Long.parseLong(request.getParameter("idContinuingEducation")));
        payment.setPaymentProcess(PaymentProcess.valueOf(request.getParameter("paymentProcess")));
        payment.setDate(new Date());
        // Extract other properties as needed
        return payment;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        Transfer updatedTransfer = transferService.updateTransfer(id, transfer);
        return ResponseEntity.ok(updatedTransfer);
    }

    @GetMapping("")
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
