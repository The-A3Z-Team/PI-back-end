package org.sid.educationservice.mappers;

import org.sid.educationservice.dtos.MajorDTO;
import org.sid.educationservice.entities.Major;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorMapperImpl {
    public MajorDTO fromMajor(Major major){
        MajorDTO majorDTO=new MajorDTO();
        BeanUtils.copyProperties(major,majorDTO);
        return  majorDTO;
    }

    public Major fromMajorDTO(MajorDTO majorDTO){
        Major major=new Major();
        BeanUtils.copyProperties(majorDTO,major);
        return  major;
    }

    public List<MajorDTO> toMajorDTOs(List<Major> Majors) {
        return Majors.stream()
                .map(this::fromMajor)
                .collect(Collectors.toList());
    }
}
