package org.sid.educationservice.services;

import org.sid.educationservice.dtos.HeadOfDepartement;
import org.sid.educationservice.dtos.MajorDTO;
import org.sid.educationservice.exceptions.MajorNotFoundException;

import java.util.List;

public interface MajorService {
    MajorDTO saveMajor(MajorDTO majorDTO);

    MajorDTO updateMajor(Long id, MajorDTO majorDTO) throws MajorNotFoundException;

    List<MajorDTO> getMajors();

    MajorDTO getMajorById(Long id) throws MajorNotFoundException;

    void deleteMajor(Long id) throws MajorNotFoundException;

    List<MajorDTO> getMajorsByHeadOfDepartment(Long id);
}
