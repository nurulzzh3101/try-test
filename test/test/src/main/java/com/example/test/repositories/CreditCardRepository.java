package com.example.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entities.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String>{
    CreditCard findCreditCardById(String id);
    List<CreditCard> findCreditCardByuserId(String userId);
    CreditCard findCreditCardByCardId(String cardId);
    CreditCard findCreditCardByName(String name);
    CreditCard findCreditCardByCVV(String cvv);
}
