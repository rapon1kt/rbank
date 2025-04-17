package com.raponis.rbank.infrastructure.database.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raponis.rbank.domain.entities.Account;
import com.raponis.rbank.domain.repositories.AccountRespository;

@Repository
public interface MongoAccountRepository extends MongoRepository<Account, String>, AccountRespository {
}
