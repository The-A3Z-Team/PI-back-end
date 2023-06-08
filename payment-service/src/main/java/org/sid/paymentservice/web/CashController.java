package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.paymentservice.entity.Cash;
import org.sid.paymentservice.services.CashService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cash")
@AllArgsConstructor
public class CashController {
    private CashService cashService;

    //@PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @GetMapping("")
    public ResponseEntity<List<Cash>> getAllCashs() {
        List<Cash> cashs = cashService.getCashs();
        return ResponseEntity.ok(cashs);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Cash> getCashById(@PathVariable Long id) {
        Cash cash = cashService.getCashById(id);

        if (cash != null) {
            return ResponseEntity.ok(cash);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PostMapping("/")
    public ResponseEntity<Cash> createCash(@RequestBody Cash cash) {
        Cash savedCash = cashService.saveCash(cash);
        return new ResponseEntity<>(savedCash, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<Cash> updateCash(@PathVariable Long id, @RequestBody Cash cash) {
        Cash updatedCash = cashService.updateCash(id, cash);
        if (updatedCash != null) {
            return new ResponseEntity<>(updatedCash, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE') or hasAuthority('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCash(@PathVariable Long id) {
        cashService.deleteCash(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('RESPONSABLE_FINANCIERE')")
    @PutMapping("/validate/{id}")
    public ResponseEntity<Cash> validateCash(@PathVariable Long id, @RequestBody Cash cash) {
        Cash updatedCash = cashService.validateCash(id, cash.getIsValid());
        return new ResponseEntity<>(updatedCash, HttpStatus.OK);
    }
}
