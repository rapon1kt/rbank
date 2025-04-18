package com.raponis.rbank.application.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.raponis.rbank.domain.entities.Transaction;
import com.raponis.rbank.infrastructure.database.repositories.MongoTransactionRepository;

@Service
public class TransactionService implements TransactionServiceInterface {

  @Autowired
  public MongoTransactionRepository mongoTransactionRepository;
  public MongoTemplate mongoTemplate;

  @Override
  public Transaction releaseNewTransaction(Transaction transaction) {
    transaction.setCreatedAt(Instant.now());
    transaction.setUpdatedAt(Instant.now());
    return this.mongoTransactionRepository.save(transaction);
  }

  @Override
  public Optional<Transaction> findTransactionById(String transactionId) {
    return this.mongoTransactionRepository.findById(transactionId);
  }

  @Override
  public List<Transaction> findTransactions(Optional<String> accountId) {
    if (accountId.isPresent()) {
      Query query = new Query(Criteria.where("accountOriginId").is(accountId.get()));
      List<Transaction> accountTransactions = mongoTemplate.find(query, Transaction.class);
      return accountTransactions;
    }
    return this.mongoTransactionRepository.findAll();
  }

  @Override
  public List<Transaction> deleteTransactionById(String transactionId, Optional<String> accountId) {
    this.mongoTransactionRepository.deleteById(transactionId);
    return this.findTransactions(accountId);
  }

  @Override
  public List<Transaction> reverseTransaction(String transactionId, Optional<String> accountId) {
    return this.deleteTransactionById(transactionId, accountId);
  }

  @Override
  public List<Transaction> deleteTransactions() {
    this.mongoTransactionRepository.deleteAll();
    return this.mongoTransactionRepository.findAll();
  }

}
