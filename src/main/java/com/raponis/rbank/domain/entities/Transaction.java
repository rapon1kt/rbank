package com.raponis.rbank.domain.entities;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "transactions")
public class Transaction {

  @Id
  private String id;
  private String type;
  private String status;
  private Number value;
  private String accountOriginId;
  private String accountDestinyId;
  private Optional<String> description;

  @CreatedDate
  private Instant createdAt;
  @LastModifiedDate
  private Instant updatedAt;
}
