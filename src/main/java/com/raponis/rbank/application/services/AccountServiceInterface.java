package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import com.raponis.rbank.domain.entities.Account;

public interface AccountServiceInterface {

  Account createNewAccount(String ownerId, Account newAccount);

  List<Account> findAllAccounts();

  Optional<Account> findAccountById(String id);

  Account updateAccountById(String id, Account account);

  List<Account> deleteAccountById(String id, Optional<String> ownerId);

  List<Account> findAccountsByOwnerId(String ownerId);

}