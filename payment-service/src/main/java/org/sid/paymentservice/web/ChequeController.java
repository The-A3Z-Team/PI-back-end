package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Cheque;
import org.sid.paymentservice.repositorys.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cheque")
@AllArgsConstructor
@NoArgsConstructor
public class ChequeController {
    @Autowired
    private ChequeRepository chequeRepository;

    @GetMapping("/")
    public ResponseEntity<List<Cheque>> getAllCheques() {
        List<Cheque> cheques = chequeRepository.findAll();
        return new ResponseEntity<>(cheques, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cheque> getChequeById(@PathVariable Long id) {
        Optional<Cheque> cheque = chequeRepository.findById(id);
        if (cheque.isPresent()) {
            return new ResponseEntity<>(cheque.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Cheque> createCheque(@RequestBody Cheque Cheque) {
        Cheque createdCheque = chequeRepository.save(Cheque);
        return new ResponseEntity<>(createdCheque, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cheque> updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        Optional<Cheque> existingCheque = chequeRepository.findById(id);
        if (existingCheque.isPresent()) {
            cheque.setId(id);
            Cheque updatedCheque = chequeRepository.save(cheque);
            return new ResponseEntity<>(updatedCheque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCheque(@PathVariable Long id) {
        Optional<Cheque> cheque = chequeRepository.findById(id);
        if (cheque.isPresent()) {
            chequeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
