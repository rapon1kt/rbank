package com.raponis.rbank.infrastructure.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raponis.rbank.application.services.AccountService;
import com.raponis.rbank.domain.entities.Account;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  public AccountService accountService;

  @PostMapping("/{ownerId}/new")
  public Account createNewAccount(@PathVariable("ownerId") String ownerId, @RequestBody Account newAccount) {
    return this.accountService.createNewAccount(ownerId, newAccount);
  }

  @GetMapping
  public List<Account> findAllAccounts() {
    return this.accountService.findAllAccounts();
  }

  @GetMapping("/{id}")
  public Optional<Account> findAccountById(@PathVariable("id") String id) {
    return this.accountService.findAccountById(id);
  }

  @PutMapping("/{id}")
  public Account updateAccountById(@PathVariable("id") String id, @RequestBody Account account) {
    return this.accountService.updateAccountById(id, account);
  }

  @GetMapping("/of/{ownerId}")
  public List<Account> findAccountByOwnerId(@PathVariable("ownerId") String ownerId) {
    return this.accountService.findAccountsByOwnerId(ownerId);
  }

  @DeleteMapping("/{id}")
  public List<Account> deleteAccountById(@PathVariable("id") String id,
      @RequestParam("ownerId") Optional<String> ownerId) {
    return this.accountService.deleteAccountById(id, ownerId);
  }
}
