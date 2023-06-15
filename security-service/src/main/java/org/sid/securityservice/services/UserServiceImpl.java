package org.sid.securityservice.services;

import lombok.AllArgsConstructor;
import org.sid.securityservice.config.PasswordEncoding;
import org.sid.securityservice.dtos.*;
import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.sid.securityservice.exceptions.RoleAlreadyAssignedException;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.mappers.RoleMapper;
import org.sid.securityservice.mappers.UserDTOMapper;
import org.sid.securityservice.mappers.UserResponseDTOMapperImpl;
import org.sid.securityservice.repositories.RoleRepository;
import org.sid.securityservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;


import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDTOMapper userDTOMapper;
    private final UserResponseDTOMapperImpl userResponseDTOMapper;
    private final RoleMapper roleMapper;
    @Autowired
    private final RestTemplate restTemplate; // Inject RestTemplate or HttpClient
    private final HttpServletRequest request;

    private final String notificationsUrl = "http://localhost:8888/notifications/notifications/user";
    private final String major_url = "http://localhost:8888/educations/major";
    private final String education_url = "http://localhost:8888/educations/continuing";
    private final String payment_url = "http://localhost:8888/payments";

    @Override
    public UserResponseDTO saveUser(UserDTO userDTO, String role) throws RoleNotFoundException {
        userDTO.setPassword(new PasswordEncoding().getEncodedPassword(userDTO.getPassword()));
        User user = userDTOMapper.fromUserDTO(userDTO);

        Role role1 = roleRepository.findByName(ERole.valueOf(role))
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + role));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        user.setRoles(roleSet);

        User savedUser = userRepository.save(user);
        return userResponseDTOMapper.fromUser(savedUser);
    }


    @Override
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        if (userDTO.getCne() != null) {
            existingUser.setCne(userDTO.getCne());
        } else {
            existingUser.setCne(existingUser.getCne());
        }
        if (userDTO.getAdresse() != null) {
            existingUser.setAdresse(userDTO.getAdresse());
        } else {
            existingUser.setAdresse(existingUser.getAdresse());
        }
        if (userDTO.getCni() != null) {
            existingUser.setCni(userDTO.getCni());
        } else {
            existingUser.setCni(existingUser.getCni());
        }
        if (userDTO.getGender() != null) {
            existingUser.setGender(userDTO.getGender());
        } else {
            existingUser.setGender(existingUser.getGender());
        }
        if (userDTO.getNationality() != null) {
            existingUser.setNationality(userDTO.getNationality());
        } else {
            existingUser.setNationality(existingUser.getNationality());
        }
        if (userDTO.getFirstName() != null) {
            existingUser.setFirstName(userDTO.getFirstName());
        } else {
            existingUser.setFirstName(existingUser.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        } else {
            existingUser.setLastName(existingUser.getLastName());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        } else {
            existingUser.setEmail(existingUser.getEmail());
        }
        if (userDTO.getDateNaissance() != null) {
            existingUser.setDateNaissance(userDTO.getDateNaissance());
        } else {
            existingUser.setDateNaissance(existingUser.getDateNaissance());
        }
        if (userDTO.getPhone() != null) {
            existingUser.setPhone(userDTO.getPhone());
        } else {
            existingUser.setPhone(existingUser.getPhone());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(new PasswordEncoding().getEncodedPassword(userDTO.getPassword()));
        } else {
            existingUser.setPassword(existingUser.getPassword());
        }
        if (userDTO.getIdMajorOfStudent() != null) {
            existingUser.setIdMajorOfStudent(userDTO.getIdMajorOfStudent());
        } else {
            existingUser.setIdMajorOfStudent(existingUser.getIdMajorOfStudent());
        }
        if (userDTO.getIdEducationOfStudent() != null) {
            existingUser.setIdEducationOfStudent(userDTO.getIdEducationOfStudent());
        } else {
            existingUser.setIdEducationOfStudent(existingUser.getIdEducationOfStudent());
        }
        if (userDTO.getIdHeadOfDepartementManagerOfStudent() != null) {
            existingUser.setIdHeadOfDepartementManagerOfStudent(userDTO.getIdHeadOfDepartementManagerOfStudent());
        } else {
            existingUser.setIdHeadOfDepartementManagerOfStudent(existingUser.getIdHeadOfDepartementManagerOfStudent());
        }
        if (userDTO.getIdMajorOfHeadOfDepartement() != null) {
            existingUser.setIdMajorOfHeadOfDepartement(userDTO.getIdMajorOfHeadOfDepartement());
        } else {
            existingUser.setIdMajorOfHeadOfDepartement(existingUser.getIdMajorOfHeadOfDepartement());
        }

        User updatedUser = userRepository.save(existingUser);
        return userResponseDTOMapper.fromUser(updatedUser);
    }


    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return userResponseDTOMapper.fromUser(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByMajor(Long idMajor){
        List<User> users = userRepository.findByIdMajorOfStudent(idMajor);
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public UserResponseDTO removeUser(Long idUser) throws UserNotFoundException {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));
        userRepository.delete(existingUser);
        return userResponseDTOMapper.fromUser(existingUser);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with Username: " + username));
        return userResponseDTOMapper.fromUser(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(ERole roleName) throws RoleNotFoundException {
        List<User> users = userRepository.findByRoles_Name(roleName);
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }


    @Override
    public UserResponseDTO addRoleToUser(Long idUser, String roleName) throws UserNotFoundException, RoleNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));

        // Check if the user already has the role
        if (user.getRoles().stream().anyMatch(existingRole -> existingRole.getName().equals(roleName))) {
            throw new RoleAlreadyAssignedException("Role already assigned to the user.");
        }

        Role newRole = roleRepository.findByName(ERole.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName));

        user.getRoles().add(newRole);
        userRepository.save(user);

        return userResponseDTOMapper.fromUser(user);
    }



    @Override
    public UserResponseDTO removeRoleFromUser(Long idUser, RoleDTO roleDTO) throws UserNotFoundException, RoleNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));

        // Find the role by name
        Role role = roleRepository.findByName(ERole.valueOf(roleDTO.getName()))
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleDTO.getName()));

        // Remove the role from the user's roles
        user.getRoles().removeIf(existingRole -> existingRole.getName().equals(role.getName()));
        userRepository.save(user);

        return userResponseDTOMapper.fromUser(user);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUser(Long idStudent) throws UserNotFoundException {
        UserResponseDTO user = getUserById(idStudent);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", request.getHeader("Authorization")); // Set the Authorization header from the request

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(payment_url+"/student/"+idStudent+"/payments")
                .queryParam("idStudent", idStudent);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<PaymentDTO[]> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                PaymentDTO[].class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch notifications for user: " + user.getUsername());
        }
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUser(Long id) throws UserNotFoundException {
        UserResponseDTO user = getUserById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", request.getHeader("Authorization")); // Set the Authorization header from the request

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(notificationsUrl+"/"+id)
                .queryParam("userId", id);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<NotificationResponseDTO[]> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                NotificationResponseDTO[].class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch notifications for user: " + user.getUsername());
        }
    }

    @Override
    public List<UserResponseDTO> getUsersByKeyword(String keyword,String role) {
        List<User> users = userRepository.getUsersBykeywordAndRole(keyword,ERole.valueOf(role));
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public List<UserResponseDTO> findAllWithoutStudentRoleSorted() {
        List<User> users = userRepository.findAllWithoutStudentRoleSorted();
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public MajorResponseDTO getMajorOfHeadOfDepartement(Long id) throws UserNotFoundException {
        UserResponseDTO user = getUserById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", request.getHeader("Authorization")); // Set the Authorization header from the request

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(major_url + "/" + user.getIdMajorOfHeadOfDepartement())
                .queryParam("id", id);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<MajorResponseDTO> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                MajorResponseDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch major for head of department: " + user.getUsername());
        }
    }

    @Override
    public MajorResponseDTO getMajorOfStudent(Long id) throws UserNotFoundException {
        UserResponseDTO user = getUserById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", request.getHeader("Authorization")); // Set the Authorization header from the request

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(major_url + "/" + user.getIdMajorOfStudent())
                .queryParam("id", id);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<MajorResponseDTO> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                MajorResponseDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch major for student: " + user.getUsername());
        }
    }

    public EducationDTO getEducationOfStudent(Long id) throws UserNotFoundException{
        UserResponseDTO user = getUserById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", request.getHeader("Authorization")); // Set the Authorization header from the request

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(education_url + "/" + user.getIdEducationOfStudent())
                .queryParam("id", id);
        System.out.println(education_url);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<EducationDTO> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                EducationDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch education for student: " + user.getUsername());
        }
    }

    public Map<Double, Double> getPaymentStateOfStudent(Long idStudent) throws UserNotFoundException {
        List<PaymentDTO> paymentDTOS = getPaymentsByUser(idStudent);
        EducationDTO educationDTO = getEducationOfStudent(idStudent);
        Double educationPrice = educationDTO.getEducation_price();
        Map map=new HashMap<>();
        map.put("payments sum",calculatePaymentsSolde(paymentDTOS));
        map.put("continuing education price",educationDTO.getEducation_price());
        return new HashMap<>(map);
    }

    private Double calculatePaymentsSolde(List<PaymentDTO> payments) {
        double paymentsSolde = 0.00;
        for (PaymentDTO payment : payments) {
            paymentsSolde += payment.getMontant();
        }

        return paymentsSolde;
    }
}
