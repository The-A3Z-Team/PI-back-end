package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Cash;
import org.sid.paymentservice.repositorys.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments/cash")
@AllArgsConstructor
@NoArgsConstructor
public class CashController {
    @Autowired
    private CashRepository cashRepository;

    @GetMapping("/")
    public ResponseEntity<List<Cash>> getAllCashs() {
        List<Cash> Cashs = cashRepository.findAll();
        return new ResponseEntity<>(Cashs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cash> getCashById(@PathVariable Long id) {
        Optional<Cash> Cash = cashRepository.findById(id);
        if (Cash.isPresent()) {
            return new ResponseEntity<>(Cash.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Cash> createCash(@RequestBody Cash Cash) {
        Cash createdCash = cashRepository.save(Cash);
        return new ResponseEntity<>(createdCash, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cash> updateCash(@PathVariable Long id, @RequestBody Cash Cash) {
        Optional<Cash> existingCash = cashRepository.findById(id);
        if (existingCash.isPresent()) {
            Cash.setId(id);
            Cash updatedCash = cashRepository.save(Cash);
            return new ResponseEntity<>(updatedCash, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCash(@PathVariable Long id) {
        Optional<Cash> Cash = cashRepository.findById(id);
        if (Cash.isPresent()) {
            cashRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
