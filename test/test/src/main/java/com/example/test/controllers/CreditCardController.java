package com.example.test.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.entities.CreditCard;
import com.example.test.services.services.CreditCardService;

@RestController
@RequestMapping("/credit/card")
public class CreditCardController {
    @Autowired
    public CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity<Void> addCreditCard(@RequestBody String cardId, String cardName, String userId, String cvv, Timestamp expiredDate){
        creditCardService.addCreditCard(cardId, cardName, userId, cvv, expiredDate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCreditCard(@RequestBody String cardId, String cardName, String userId, String cvv, Timestamp expiredDate){
        creditCardService.updateCreditCard(cardId, cardName, userId, cvv, expiredDate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CreditCard>> findCardByUserId(@PathVariable String userId){
        List<CreditCard> creditCards = creditCardService.findCardByUserId(userId);
        return new ResponseEntity<List<CreditCard>>(creditCards, null, 0);
    }

    @DeleteMapping("/delete/{userId}/{cardId}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable String userId, @PathVariable String cardId){
        creditCardService.deleteCreditCard(userId, cardId);
        return ResponseEntity.ok().build();
    }
    
}
