package com.raponis.rbank.infrastructure.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raponis.rbank.application.services.CardService;
import com.raponis.rbank.domain.entities.Card;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/cards")
public class CardController {

  @Autowired
  public CardService cardService;

  @PostMapping
  public Card registerNewCard(@RequestParam("accountId") String accountId, @RequestBody Card newCard) {
    return this.cardService.registerNewCard(accountId, newCard);
  }

  @GetMapping
  public List<Card> findAllCards() {
    return this.cardService.findAllCards();
  }

  @GetMapping("/of/{accountId}")
  public List<Card> findAccountCards(@PathVariable("accountId") String accountId) {
    return this.cardService.findAccountCards(accountId);
  }

  @GetMapping("/{id}")
  public Optional<Card> findCardById(@PathVariable("id") String id) {
    return this.cardService.findCardById(id);
  }

  @PutMapping("/{id}")
  public Card updateCardById(@PathVariable("id") String id, @RequestBody Card cardToUpdate) {
    return this.cardService.updateCardById(id, cardToUpdate);
  }

  @DeleteMapping("/{id}")
  public List<Card> deleteCardById(@PathVariable("id") String id,
      @RequestParam("accountId") Optional<String> accountId) {
    return this.cardService.deleteCardById(id, accountId);
  }

}
