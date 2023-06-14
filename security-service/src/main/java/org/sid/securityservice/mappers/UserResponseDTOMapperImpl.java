package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResponseDTOMapperImpl implements UserResponseDTOMapper {
    private final RoleMapper roleMapper;

    public UserResponseDTOMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserResponseDTO fromUser(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setDateNaissance(user.getDateNaissance());
        userResponseDTO.setGender(user.getGender());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setAdresse(user.getAdresse());
        userResponseDTO.setNationality(user.getNationality());
        userResponseDTO.setCne(user.getCne());
        userResponseDTO.setCni(user.getCni());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setIdMajorOfStudent(user.getIdMajorOfStudent());
        userResponseDTO.setIdEducationOfStudent(user.getIdEducationOfStudent());
        userResponseDTO.setIdHeadOfDepartementManagerOfStudent(user.getIdHeadOfDepartementManagerOfStudent());
        userResponseDTO.setIdMajorOfHeadOfDepartement(user.getIdMajorOfHeadOfDepartement());
        userResponseDTO.setRoleDTOList(roleMapper.toRoleDTOs(user.getRoles()));
        return userResponseDTO;
    }

    @Override
    public User fromUserResponseDTO(UserResponseDTO userResponseDTO) {
        User user = new User();
        user.setId(userResponseDTO.getId());
        user.setFirstName(userResponseDTO.getFirstName());
        user.setLastName(userResponseDTO.getLastName());
        user.setDateNaissance(userResponseDTO.getDateNaissance());
        user.setGender(userResponseDTO.getGender());
        user.setPhone(userResponseDTO.getPhone());
        user.setAdresse(userResponseDTO.getAdresse());
        user.setNationality(userResponseDTO.getNationality());
        user.setCne(userResponseDTO.getCne());
        user.setCni(userResponseDTO.getCni());
        user.setUsername(userResponseDTO.getUsername());
        user.setEmail(userResponseDTO.getEmail());
        user.setIdMajorOfStudent(userResponseDTO.getIdMajorOfStudent());
        user.setIdEducationOfStudent(userResponseDTO.getIdEducationOfStudent());
        user.setIdHeadOfDepartementManagerOfStudent(userResponseDTO.getIdHeadOfDepartementManagerOfStudent());
        user.setIdMajorOfHeadOfDepartement(userResponseDTO.getIdMajorOfHeadOfDepartement());
        user.setRoles(roleMapper.toRoles(userResponseDTO.getRoleDTOList()));
        return user;
    }

    @Override
    public List<UserResponseDTO> toUserResponseDTOs(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }
}
