package org.sid.paymentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String STUDENT_SERVICE_URL = "http://localhost:8888/authentification/students";
    private static final UriTemplate STUDENT_BY_CODE_URI_TEMPLATE = new UriTemplate(STUDENT_SERVICE_URL);

    @Autowired
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getWalo(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(STUDENT_BY_CODE_URI_TEMPLATE.expand().toString());

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
