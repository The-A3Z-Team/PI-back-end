package org.sid.educationservice.web;

import lombok.AllArgsConstructor;
import org.sid.educationservice.dtos.HeadOfDepartement;
import org.sid.educationservice.dtos.MajorDTO;
import org.sid.educationservice.exceptions.MajorNotFoundException;
import org.sid.educationservice.services.MajorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major")
@AllArgsConstructor
public class MajorController {
    private MajorService majorService;

    @GetMapping("")
    public ResponseEntity<List<MajorDTO>> getAllMajors() {
        List<MajorDTO> majors = majorService.getMajors();
        return new ResponseEntity<>(majors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorDTO> getMajorById(@PathVariable("id") Long id) throws MajorNotFoundException {
        MajorDTO major = majorService.getMajorById(id);

        if (major != null) {
            return ResponseEntity.ok(major);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<MajorDTO> createMajor(@RequestBody MajorDTO major) {
        MajorDTO createdMajor = majorService.saveMajor(major);
        return new ResponseEntity<>(createdMajor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MajorDTO> updateMajor(@PathVariable("id") Long id, @RequestBody MajorDTO major) throws MajorNotFoundException {
        MajorDTO updatedMajor = majorService.updateMajor(id, major);
        if (updatedMajor != null) {
            return new ResponseEntity<>(updatedMajor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable("id") Long id) throws MajorNotFoundException {
        majorService.deleteMajor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/headOfDepartement")
    public ResponseEntity<HeadOfDepartement> getHeadOfDepartement(@PathVariable("id") Long id) {
        HeadOfDepartement headOfDepartement = majorService.getHeadOfDepartment(id);
        if (headOfDepartement != null) {
            return ResponseEntity.ok(headOfDepartement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
