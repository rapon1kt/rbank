package com.raponis.rbank.infrastructure.database.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raponis.rbank.domain.entities.Transaction;
import com.raponis.rbank.domain.repositories.TransactionRepository;

@Repository
public interface MongoTransactionRepository extends MongoRepository<Transaction, String>, TransactionRepository {

}
