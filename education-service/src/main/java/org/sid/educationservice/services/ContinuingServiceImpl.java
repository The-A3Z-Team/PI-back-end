package org.sid.educationservice.services;

import lombok.AllArgsConstructor;
import org.sid.educationservice.dtos.ContinuingDTO;
import org.sid.educationservice.entities.Continuing;
import org.sid.educationservice.exceptions.ContinuingNotFoundException;
import org.sid.educationservice.mappers.ContinuingMapperImpl;
import org.sid.educationservice.repositories.ContinuingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContinuingServiceImpl implements ContinuingService {
    private ContinuingRepository continuingRepository;
    private ContinuingMapperImpl continuingMapper;
    @Override
    public ContinuingDTO saveContinuing(ContinuingDTO continuingDTO) {
        Continuing continuing = continuingMapper.fromContinuingDTO(continuingDTO);
        Continuing savedContnuing = continuingRepository.save(continuing);
        return continuingMapper.fromContinuing(savedContnuing);
    }

    @Override
    public ContinuingDTO updateContinuing(Long id, ContinuingDTO continuingDTO) throws ContinuingNotFoundException {
        Continuing existingContinuing = continuingRepository.findById(id)
                .orElseThrow(() -> new ContinuingNotFoundException("Continuing not found with ID: " + id));

        existingContinuing.setDescription(continuingDTO.getDescription());
        existingContinuing.setEducation_price(continuingDTO.getEducation_price());
        existingContinuing.setDiploma(continuingDTO.getDiploma());
        existingContinuing.setDuration(continuingDTO.getDuration());
        existingContinuing.setEducation_price(continuingDTO.getEducation_price());

        Continuing updatedContinuing = continuingRepository.save(existingContinuing);
        return continuingMapper.fromContinuing(updatedContinuing);
    }

    @Override
    public List<ContinuingDTO> getContinuings() {
        List<Continuing> continuings = continuingRepository.findAll();
        return continuingMapper.toContinuingDTOs(continuings);
    }

    @Override
    public ContinuingDTO getContinuingById(Long id) throws ContinuingNotFoundException {
        Continuing continuing = continuingRepository.findById(id)
                .orElseThrow(() -> new ContinuingNotFoundException("Continuing not found with ID: " + id));
        return continuingMapper.fromContinuing(continuing);
    }

    @Override
    public void deleteContinuing(Long id) throws ContinuingNotFoundException {
        Continuing continuing = continuingRepository.findById(id)
                .orElseThrow(() -> new ContinuingNotFoundException("Continuing not found with ID: " + id));
        continuingRepository.delete(continuing);
    }
}
