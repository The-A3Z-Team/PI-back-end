package org.sid.negociationservice.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.negociationservice.entity.Negociation;
import org.sid.negociationservice.repositorys.NegociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/negociations")
@AllArgsConstructor
@NoArgsConstructor
public class NegociationController {

    @Autowired
    private NegociationRepository negociationRepository;

    @GetMapping("/")
    public ResponseEntity<List<Negociation>> getAllNegociations() {
        List<Negociation> negociations = negociationRepository.findAll();
        return new ResponseEntity<>(negociations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Negociation> getNegociationById(@PathVariable("id") Long id) {
        Optional<Negociation> optionalNegociation = negociationRepository.findById(id);
        return optionalNegociation.map(negociation -> new ResponseEntity<>(negociation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Negociation> createNegociation(@RequestBody Negociation negociation) {
        Negociation createdNegociation = negociationRepository.save(negociation);
        return new ResponseEntity<>(createdNegociation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Negociation> updateNegociation(@PathVariable("id") Long id, @RequestBody Negociation negociation) {
        Optional<Negociation> optionalNegociation = negociationRepository.findById(id);
        if (optionalNegociation.isPresent()) {
            Negociation existingNegociation = optionalNegociation.get();
            existingNegociation.setDescription(negociation.getDescription());
            Negociation updatedNegociation = negociationRepository.save(existingNegociation);
            return new ResponseEntity<>(updatedNegociation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNegociation(@PathVariable("id") Long id) {
        Optional<Negociation> optionalNegociation = negociationRepository.findById(id);
        if (optionalNegociation.isPresent()) {
            negociationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
