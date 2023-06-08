package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Cheque;
import org.sid.paymentservice.services.ChequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheque")
@AllArgsConstructor
@NoArgsConstructor
public class ChequeController {
    private ChequeService chequeService;

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @GetMapping("")
    public ResponseEntity<List<Cheque>> getAllcheques() {
        List<Cheque> cheques = chequeService.getCheques();
        return ResponseEntity.ok(cheques);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Cheque> getchequeById(@PathVariable Long id) {
        Cheque cheque = chequeService.getChequeById(id);

        if (cheque != null) {
            return ResponseEntity.ok(cheque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PostMapping("/")
    public ResponseEntity<Cheque> createcheque(@RequestBody Cheque cheque) {
        Cheque savedcheque = chequeService.saveCheque(cheque);
        return new ResponseEntity<>(savedcheque, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<Cheque> updatecheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        Cheque updatedcheque = chequeService.updateCheque(id, cheque);
        if (updatedcheque != null) {
            return new ResponseEntity<>(updatedcheque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletecheque(@PathVariable Long id) {
        chequeService.deleteCheque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @PutMapping("/validate/{id}")
    public ResponseEntity<Cheque> validatecheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        Cheque updatedcheque = chequeService.validateCheque(id, cheque.getIsValid());
        return new ResponseEntity<>(updatedcheque, HttpStatus.OK);
    }
}
