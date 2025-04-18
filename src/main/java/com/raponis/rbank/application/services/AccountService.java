package com.raponis.rbank.application.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.raponis.rbank.domain.entities.Account;
import com.raponis.rbank.infrastructure.database.repositories.MongoAccountRepository;

@Service
public class AccountService implements AccountServiceInterface {

  @Autowired
  public MongoAccountRepository mongoAccountRepository;
  public MongoTemplate mongoTemplate;

  public AccountService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Account createNewAccount(String ownerId, Account newAccount) {
    newAccount.setOwnerId(ownerId);
    newAccount.setCash(new BigDecimal(0));
    return this.mongoAccountRepository.save(newAccount);
  }

  @Override
  public List<Account> findAllAccounts() {
    return this.mongoAccountRepository.findAll();
  }

  @Override
  public Optional<Account> findAccountById(String id) {
    return this.mongoAccountRepository.findById(id);
  }

  @Override
  public Account updateAccountById(String id, Account account) {
    account.setId(id);
    return this.mongoAccountRepository.save(account);
  }

  @Override
  public List<Account> findAccountsByOwnerId(String ownerId) {
    Query query = new Query(Criteria.where("ownerId").is(ownerId));
    return this.mongoTemplate.find(query, Account.class);
  }

  @Override
  public List<Account> deleteAccountById(String id, Optional<String> ownerId) {
    this.mongoAccountRepository.deleteById(id);
    if (ownerId.isPresent()) {
      List<Account> ownerAccounts = this.findAccountsByOwnerId(ownerId.get());
      return ownerAccounts;
    }
    return this.findAllAccounts();
  }
}
