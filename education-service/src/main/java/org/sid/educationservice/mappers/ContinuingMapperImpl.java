package org.sid.educationservice.mappers;

import org.sid.educationservice.dtos.ContinuingDTO;
import org.sid.educationservice.entities.Continuing;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContinuingMapperImpl {
    public ContinuingDTO fromContinuing(Continuing continuing){
        ContinuingDTO continuingDTO=new ContinuingDTO();
        BeanUtils.copyProperties(continuing,continuingDTO);
        return  continuingDTO;
    }

    public Continuing fromContinuingDTO(ContinuingDTO continuingDTO){
        Continuing continuing=new Continuing();
        BeanUtils.copyProperties(continuingDTO,continuing);
        return  continuing;
    }

    public List<ContinuingDTO> toContinuingDTOs(List<Continuing> continuings) {
        return continuings.stream()
                .map(this::fromContinuing)
                .collect(Collectors.toList());
    }
}
