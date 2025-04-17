package com.raponis.rbank.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

  public @Bean MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String mongoUri) {
    return MongoClients.create(mongoUri); // Ou use a URI do application.properties
  }

  public @Bean MongoTemplate mongoTemplate(MongoClient mongoClient,
      @Value("${spring.data.mongodb.database}") String dataBaseName) {
    return new MongoTemplate(mongoClient, dataBaseName);
  }
}