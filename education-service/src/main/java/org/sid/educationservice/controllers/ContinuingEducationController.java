package org.sid.educationservice.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entity.Continuing;
import org.sid.educationservice.repositorys.ContinuingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/educations/continuing")
@AllArgsConstructor
@NoArgsConstructor
public class ContinuingEducationController {
    @Autowired
    private ContinuingRepository continuingRepository;

    @GetMapping("/")
    public ResponseEntity<List<Continuing>> getAll() {
        List<Continuing> continuingEducations = continuingRepository.findAll();
        System.out.println(continuingEducations.size());
        return new ResponseEntity<>(continuingEducations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Continuing> getById(@PathVariable("id") Long id) {
        Optional<Continuing> continuingEducationOptional = continuingRepository.findById(id);
        if (continuingEducationOptional.isPresent()) {
            return new ResponseEntity<>(continuingEducationOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Continuing> updateContinuingEducation(@PathVariable("id") Long id, @RequestBody Continuing continuing) {
        Optional<Continuing> existingContinuingEducation = continuingRepository.findById(id);
        if (existingContinuingEducation.isPresent()) {
            continuing.setId(id);
            Continuing updatedContinuingEducation = continuingRepository.save(continuing);
            return new ResponseEntity<>(updatedContinuingEducation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinuingEducation(@PathVariable("id") Long id) {
        Optional<Continuing> continuingEducationOptional = continuingRepository.findById(id);
        if (continuingEducationOptional.isPresent()) {
            continuingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Continuing> saveContinuingEducation(@RequestBody Continuing continuing) {
        Continuing savedContinuingEducation = continuingRepository.save(continuing);
        return new ResponseEntity<>(savedContinuingEducation, HttpStatus.CREATED);
    }
}
