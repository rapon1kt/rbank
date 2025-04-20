package com.raponis.rbank.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
  @Id
  private String id;
  @DBRef
  private Client owner;

  private String agency;
  private int cash;
  private String type;
}
