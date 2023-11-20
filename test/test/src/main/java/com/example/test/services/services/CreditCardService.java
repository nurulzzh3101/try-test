package com.example.test.services.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entities.CreditCard;
import com.example.test.repositories.CreditCardRepository;

import jakarta.transaction.Transactional;

@Service
public class CreditCardService {
    @Autowired
    public CreditCardRepository creditCardRepository;


    @Transactional
    public void addCreditCard(String cardId, String cardName, String userId, String cvv, Timestamp expiredDate){
        CreditCard creditCard = new CreditCard();
        creditCard.cardId = cardId;
        creditCard.userId = userId;
        creditCard.name = cardName;
        creditCard.cvv = cvv;
        creditCard.expiredDate = expiredDate;
        creditCardRepository.save(creditCard);
    }

    @Transactional
    public void updateCreditCard(String cardId, String cardName, String userId, String cvv, Timestamp expiredDate){
        List<CreditCard> creditCards = creditCardRepository.findCreditCardByuserId(userId);
        for(int i = 0; i< creditCards.size(); i++){
            if(creditCards.get(i).equals(cardId)){
                creditCards.get(i).cardId = cardId;
                creditCards.get(i).userId = userId;
                creditCards.get(i).name = cardName;
                creditCards.get(i).cvv = cvv;
                creditCards.get(i).expiredDate = expiredDate;
                creditCards.get(i).updatedDate = new Timestamp(System.currentTimeMillis());
                creditCardRepository.save(creditCards.get(i));
            }
        }
       
    }

    public List<CreditCard> findCardByUserId(String userId){
        List<CreditCard> creditCard = new ArrayList<>();
        creditCardRepository.findCreditCardByuserId(userId).forEach(creditCard::add);
        return creditCard;
    }

    public void deleteCreditCard(String userId, String cardId){
         List<CreditCard> creditCards = creditCardRepository.findCreditCardByuserId(userId);
         for(int i = 0; i< creditCards.size(); i++){
            if(creditCards.get(i).equals(cardId)){
                creditCardRepository.deleteById(creditCards.get(i).id);
            }
        }
    }   
}
