package org.sid.educationservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.educationservice.entities.ContinuingEducation;
import org.sid.educationservice.repositories.ContinuingRepository;
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
    public ResponseEntity<List<ContinuingEducation>> getAll() {
        List<ContinuingEducation> continuingEducations = continuingRepository.findAll();
        System.out.println(continuingEducations.size());
        return new ResponseEntity<>(continuingEducations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContinuingEducation> getById(@PathVariable("id") Long id) {
        Optional<ContinuingEducation> continuingEducationOptional = continuingRepository.findById(id);
        if (continuingEducationOptional.isPresent()) {
            return new ResponseEntity<>(continuingEducationOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContinuingEducation> updateContinuingEducation(@PathVariable("id") Long id, @RequestBody ContinuingEducation continuing) {
        Optional<ContinuingEducation> existingContinuingEducation = continuingRepository.findById(id);
        if (existingContinuingEducation.isPresent()) {
            continuing.setId(id);
            ContinuingEducation updatedContinuingEducation = continuingRepository.save(continuing);
            return new ResponseEntity<>(updatedContinuingEducation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinuingEducation(@PathVariable("id") Long id) {
        Optional<ContinuingEducation> continuingEducationOptional = continuingRepository.findById(id);
        if (continuingEducationOptional.isPresent()) {
            continuingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ContinuingEducation> saveContinuingEducation(@RequestBody ContinuingEducation continuing) {
        ContinuingEducation savedContinuingEducation = continuingRepository.save(continuing);
        return new ResponseEntity<>(savedContinuingEducation, HttpStatus.CREATED);
    }
}
