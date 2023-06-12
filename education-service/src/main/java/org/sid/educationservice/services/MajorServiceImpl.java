package org.sid.educationservice.services;

import org.sid.educationservice.dtos.HeadOfDepartement;
import org.sid.educationservice.dtos.MajorDTO;
import org.sid.educationservice.entities.Major;
import org.sid.educationservice.exceptions.MajorNotFoundException;
import org.sid.educationservice.mappers.MajorMapperImpl;
import org.sid.educationservice.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorServiceImpl implements MajorService {
    private MajorRepository majorRepository;
    private MajorMapperImpl majorMapper;
    private RestTemplate restTemplate;

    @Value("${headOfDepartmentUrl}")
    private String headOfDepartmentUrl;

    public MajorServiceImpl(MajorRepository majorRepository, MajorMapperImpl majorMapper, RestTemplate restTemplate) {
        this.majorRepository = majorRepository;
        this.majorMapper = majorMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public MajorDTO saveMajor(MajorDTO majorDTO) {
        Major major = majorMapper.fromMajorDTO(majorDTO);
        Major savedMajor = majorRepository.save(major);
        return majorMapper.fromMajor(savedMajor);
    }

    @Override
    public MajorDTO updateMajor(Long id, MajorDTO majorDTO) throws MajorNotFoundException {
        Major existingMajor = majorRepository.findById(id)
                .orElseThrow(() -> new MajorNotFoundException("Major not found with ID: " + id));

        existingMajor.setName(majorDTO.getName());

        Major updatedMajor = majorRepository.save(existingMajor);
        return majorMapper.fromMajor(updatedMajor);
    }

    @Override
    public List<MajorDTO> getMajors() {
        List<Major> majors = majorRepository.findAll();
        return majors.stream()
                .map(majorMapper::fromMajor)
                .collect(Collectors.toList());
    }

    @Override
    public MajorDTO getMajorById(Long id) throws MajorNotFoundException {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new MajorNotFoundException("Major not found with ID: " + id));
        return majorMapper.fromMajor(major);
    }

    @Override
    public void deleteMajor(Long id) throws MajorNotFoundException {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new MajorNotFoundException("Major not found with ID: " + id));
        majorRepository.delete(major);
    }

    @Override
    public HeadOfDepartement getHeadOfDepartment(Long id) {
        String url = headOfDepartmentUrl + id;
        ResponseEntity<HeadOfDepartement> response = restTemplate.exchange(url, HttpMethod.GET, null, HeadOfDepartement.class);
        return response.getBody();
    }
}
