package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import com.raponis.rbank.domain.entities.Card;

public interface CardServiceInterface {

  Card registerNewCard(String accountId, Card newCard);

  List<Card> findAllCards();

  List<Card> findAccountCards(String accountId);

  Optional<Card> findCardById(String id);

  Card updateCardById(String id, Card cardToUpdate);

  List<Card> deleteCardById(String id, Optional<String> accountId);

}
