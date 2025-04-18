package com.raponis.rbank.domain.entities;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
  @Id
  private String id;
  private String ownerId;
  private String agency;
  private BigDecimal cash;
  private String type;
}
