package org.sid.paymentservice.services;

import org.sid.paymentservice.entities.UserResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String STUDENT_SERVICE_URL = "http://localhost:8888/authentification/student";
    private static final UriTemplate STUDENT_BY_CODE_URI_TEMPLATE = new UriTemplate(STUDENT_SERVICE_URL);

    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserResponse getStudentByPayment(String token, String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(headers);

        String url = STUDENT_BY_CODE_URI_TEMPLATE.expand().toString() + "?email=" + email;
        System.out.println(url);

        ResponseEntity<UserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserResponse.class
        );

        return response.getBody();
    }
}
