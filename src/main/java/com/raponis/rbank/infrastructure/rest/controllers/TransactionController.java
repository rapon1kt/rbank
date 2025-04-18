package com.raponis.rbank.infrastructure.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raponis.rbank.application.services.TransactionService;
import com.raponis.rbank.domain.entities.Transaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  @Autowired
  public TransactionService transactionService;

  @PostMapping("/new/{accountOriginId}/to/{accountDestinyId}")
  public ResponseEntity<Transaction> releaseNewTransaction(@RequestBody Transaction transaction,
      @PathVariable("accountOriginId") String accountOriginId,
      @PathVariable("accountDestinyId") String accountDestinyId) {
    try {
      Transaction newTransaction = transactionService.releaseNewTransaction(transaction, accountOriginId,
          accountDestinyId);
      return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public Optional<Transaction> findTransactionById(@PathVariable("id") String id) {
    return this.transactionService.findTransactionById(id);
  }

  @GetMapping
  public List<Transaction> findTransactions(@RequestParam("accountId") Optional<String> accountId) {
    return this.transactionService.findTransactions(accountId);
  }

  @DeleteMapping("/{id}")
  public List<Transaction> deleteTransactionById(String transactionId,
      @RequestParam("accountId") Optional<String> accountId) {
    return this.transactionService.deleteTransactionById(transactionId, accountId);
  }

  @DeleteMapping("/{id}/{accounId}")
  public List<Transaction> reverseTransaction(@PathVariable("id") String id,
      @PathVariable("accountId") Optional<String> accountId) {
    return this.transactionService.reverseTransaction(id, accountId);
  }

  @DeleteMapping
  public List<Transaction> deleteTransactions() {
    return this.transactionService.deleteTransactions();
  }

}
