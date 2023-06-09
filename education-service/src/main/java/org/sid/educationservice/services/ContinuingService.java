package org.sid.educationservice.services;

import org.sid.educationservice.dtos.ContinuingDTO;
import org.sid.educationservice.entities.Continuing;
import org.sid.educationservice.exceptions.ContinuingNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContinuingService {
    ContinuingDTO saveContinuing(ContinuingDTO continuingDTO);
    ContinuingDTO updateContinuing(Long id,ContinuingDTO continuingDTO) throws ContinuingNotFoundException;
    List<ContinuingDTO> getContinuings();
    ContinuingDTO getContinuingById(Long id) throws ContinuingNotFoundException;
    void deleteContinuing(Long id) throws ContinuingNotFoundException;
}
