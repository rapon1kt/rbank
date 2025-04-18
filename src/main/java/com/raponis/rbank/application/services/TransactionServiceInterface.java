package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import com.raponis.rbank.domain.entities.Transaction;

public interface TransactionServiceInterface {

  Transaction releaseNewTransaction(Transaction transaction, String accountOriginId, String accountDestinyId);

  Optional<Transaction> findTransactionById(String transactionId);

  List<Transaction> findTransactions(Optional<String> accountId);

  List<Transaction> deleteTransactionById(String transactionId, Optional<String> accountId);

  List<Transaction> reverseTransaction(String transactionId, Optional<String> accountId);

  List<Transaction> deleteTransactions();

}
