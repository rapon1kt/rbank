package com.raponis.rbank.infrastructure.database.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raponis.rbank.domain.entities.Card;
import com.raponis.rbank.domain.repositories.CardRepository;

@Repository
public interface MongoCardRepository extends MongoRepository<Card, String>, CardRepository {
}
