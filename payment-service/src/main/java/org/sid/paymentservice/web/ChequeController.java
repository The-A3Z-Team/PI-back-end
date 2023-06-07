package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Cheque;
import org.sid.paymentservice.services.ChequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheque")
@AllArgsConstructor
@NoArgsConstructor
public class ChequeController {
    private ChequeService chequeService;

    @GetMapping("")
    public ResponseEntity<List<Cheque>> getAllcheques() {
        List<Cheque> cheques = chequeService.getCheques();
        return ResponseEntity.ok(cheques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cheque> getchequeById(@PathVariable Long id) {
        Cheque cheque = chequeService.getChequeById(id);

        if (cheque != null) {
            return ResponseEntity.ok(cheque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Cheque> createcheque(@RequestBody Cheque cheque) {
        Cheque savedcheque = chequeService.saveCheque(cheque);
        return new ResponseEntity<>(savedcheque, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cheque> updatecheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        Cheque updatedcheque = chequeService.updateCheque(id, cheque);
        if (updatedcheque != null) {
            return new ResponseEntity<>(updatedcheque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletecheque(@PathVariable Long id) {
        chequeService.deleteCheque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/validate/{id}")
    public ResponseEntity<Cheque> validatecheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        Cheque updatedcheque = chequeService.validateCheque(id, cheque.getIsValid());
        return new ResponseEntity<>(updatedcheque, HttpStatus.OK);
    }
}
