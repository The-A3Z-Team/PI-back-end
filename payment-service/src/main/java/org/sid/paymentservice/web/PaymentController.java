package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping("")
    public String getWalo(@RequestHeader("Authorization") String authorization) {
        // Extract the token from the Authorization header
        String token = authorization.replace("Bearer ", "");
        return paymentService.getWalo(token);
    }
}
