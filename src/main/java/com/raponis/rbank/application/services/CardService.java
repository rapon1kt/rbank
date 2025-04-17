package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.raponis.rbank.domain.entities.Card;
import com.raponis.rbank.infrastructure.database.repositories.MongoCardRepository;

@Service
public class CardService implements CardServiceInterface {

  @Autowired
  public MongoCardRepository mongoCardRepository;
  public MongoTemplate mongoTemplate;

  public CardService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Card registerNewCard(String accountId, Card newCard) {
    newCard.setAccountId(accountId);
    return this.mongoCardRepository.save(newCard);
  }

  @Override
  public List<Card> findAllCards() {
    return this.mongoCardRepository.findAll();
  }

  @Override
  public List<Card> findAccountCards(String accountId) {
    Query query = new Query(Criteria.where("accountId").is(accountId));
    return mongoTemplate.find(query, Card.class);
  }

  @Override
  public Optional<Card> findCardById(String id) {
    return this.mongoCardRepository.findById(id);
  }

  @Override
  public Card updateCardById(String id, Card cardToUpdate) {
    cardToUpdate.setId(id);
    return mongoTemplate.save(cardToUpdate);
  }

  @Override
  public List<Card> deleteCardById(String id, Optional<String> accountId) {
    this.mongoCardRepository.deleteById(id);
    if (accountId.isPresent()) {
      List<Card> accountCards = this.findAccountCards(accountId.get());
      if (accountCards.size() < 1) {
        throw new Error("Opa! Parece que você não possui contas ainda...");
      } else {
        return accountCards;
      }
    } else
      return this.findAllCards();
  }

}
