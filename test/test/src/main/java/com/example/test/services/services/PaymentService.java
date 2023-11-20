package com.example.test.services.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.test.entities.Payment;
import com.example.test.entities.User;
import com.example.test.repositories.PaymentRepository;
import com.example.test.repositories.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import com.stripe.model.Charge;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;


@Service
public class PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    public OTPService otpService;

    @Autowired
    public UserActivityService userActivityService;

    @Autowired
    public UserRepository userRepository;

    @Value("${stripe.secret.key}")
    public String secretKey;


    public Charge chargeCreditCard(String cardId, BigDecimal amount, String currency) throws StripeException{
        Stripe.apiKey = secretKey;
        Map<String, Object> mp = new HashMap<>();
        mp.put("amount", amount);
        mp.put("currency", currency);
        mp.put("source", cardId);

        return Charge.create(mp);
    }
    
    @Transactional
    public String sendPaymentByEmail(String email, BigDecimal amount, String cardId){
        String otp = otpService.createOTP();

        try {
            otpService.emailPayment(email, otp, amount, cardId);
        } catch (MessagingException e) {
            throw new RuntimeException("cannot send otp");
        }

        User user = userRepository.findUserByEmail(email);

        Payment payment = new Payment();
        payment.createdDate = new Timestamp(System.currentTimeMillis());
        payment.amount = amount;
        payment.userId = user.id;
        payment.otp = otp;
        payment.otpCreated = new Timestamp(System.currentTimeMillis());
        paymentRepository.save(payment);

        userActivityService.addActivity(user.id, "SEND PAYMENT TO EMAIL", "user send payment info to email");
        return "Successfull";        
    }
}
