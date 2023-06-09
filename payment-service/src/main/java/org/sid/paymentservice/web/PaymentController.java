package org.sid.paymentservice.web;

import lombok.AllArgsConstructor;
import org.sid.paymentservice.entities.UserResponse;
import org.sid.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping("/student")
    public UserResponse getStudentByPayment(@RequestHeader("Authorization") String authorization, @RequestParam String emailStudent) {
        // Extract the token from the Authorization header
        String token = authorization.replace("Bearer ", "");
        return paymentService.getStudentByPayment(token,emailStudent);
    }
}
