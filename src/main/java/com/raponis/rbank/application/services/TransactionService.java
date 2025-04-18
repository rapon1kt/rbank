package com.raponis.rbank.application.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.raponis.rbank.domain.entities.Account;
import com.raponis.rbank.domain.entities.Transaction;
import com.raponis.rbank.infrastructure.database.repositories.MongoAccountRepository;
import com.raponis.rbank.infrastructure.database.repositories.MongoTransactionRepository;

@Service
public class TransactionService implements TransactionServiceInterface {

  @Autowired
  public MongoTransactionRepository mongoTransactionRepository;
  public MongoAccountRepository mongoAccountRepository;
  public MongoTemplate mongoTemplate;

  public TransactionService(MongoAccountRepository mongoAccountRepository) {
    this.mongoAccountRepository = mongoAccountRepository;
  }

  @Override
  public Transaction releaseNewTransaction(Transaction transaction, String accountOriginId, String accountDestinyId) {
    Optional<Account> accountOriginOptional = mongoAccountRepository.findById(accountOriginId);
    Optional<Account> accountDestinyOptional = mongoAccountRepository.findById(accountDestinyId);
    if (accountOriginOptional.isPresent()) {
      Account accountOrigin = accountOriginOptional.get();
      Account accountDestiny = accountDestinyOptional.get();
      transaction.setAccountOriginId(accountOriginId);
      transaction.setAccountDestinyId(accountDestinyId);
      if (accountOrigin.getCash().compareTo(transaction.getValue()) >= 0) {
        // Change cash from both accounts
        accountOrigin.setCash(accountOrigin.getCash().subtract(transaction.getValue()));
        accountDestiny.setCash(accountDestiny.getCash().add(transaction.getValue()));
        // Save accounts
        this.mongoAccountRepository.save(accountOrigin);
        this.mongoAccountRepository.save(accountDestiny);
        // Define timestamp
        transaction.setCreatedAt(Instant.now());
        transaction.setUpdatedAt(Instant.now());
        // Return the new transaction
        return this.mongoTransactionRepository.save(transaction);
      } else {
        throw new RuntimeException("Não foi possível realizar a transação, saldo insuficiente.");
      }
    } else {
      throw new IllegalArgumentException("A conta não foi encontrada.");
    }
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
