package com.example.test.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.services.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    public PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<String> sendPaymentByEmail(@RequestBody String email, BigDecimal amount, String cardId) {
        String response = paymentService.sendPaymentByEmail(email, amount, cardId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/charge")
    public ResponseEntity<Charge> chargeCreditCard(@RequestBody String cardId, BigDecimal amount, String currency)
            throws StripeException {
        Charge cg = paymentService.chargeCreditCard(cardId, amount, currency);
        return new ResponseEntity<Charge>(cg, HttpStatus.OK);
    }
}
