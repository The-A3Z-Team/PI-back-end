package org.sid.educationservice.web;

import lombok.AllArgsConstructor;
import org.sid.educationservice.dtos.ContinuingDTO;
import org.sid.educationservice.exceptions.ContinuingNotFoundException;
import org.sid.educationservice.services.ContinuingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/continuing")
@AllArgsConstructor
public class ContinuingController {
    private ContinuingService continuingService;

    @GetMapping("/")
    public ResponseEntity<List<ContinuingDTO>> getAllContinuings() {
        List<ContinuingDTO> continuings = continuingService.getContinuings();
        return new ResponseEntity<>(continuings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContinuingDTO> getContinuingById(@PathVariable("id") Long id) throws ContinuingNotFoundException {
        ContinuingDTO continuing = continuingService.getContinuingById(id);

        if (continuing != null) {
            return ResponseEntity.ok(continuing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ContinuingDTO> createContinuing(@RequestBody ContinuingDTO continuing) {
        ContinuingDTO createdContinuing = continuingService.saveContinuing(continuing);
        return new ResponseEntity<>(createdContinuing, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContinuingDTO> updateContinuing(@PathVariable("id") Long id, @RequestBody ContinuingDTO continuing) throws ContinuingNotFoundException {
        ContinuingDTO updatedContinuing = continuingService.updateContinuing(id, continuing);
        if (updatedContinuing != null) {
            return new ResponseEntity<>(updatedContinuing, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinuing(@PathVariable("id") Long id) throws ContinuingNotFoundException {
        continuingService.deleteContinuing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}