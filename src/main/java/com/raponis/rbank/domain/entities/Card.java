package com.raponis.rbank.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cards")
public class Card {
  @Id
  private String id;
  private String accountId;
  private Number pin;
  private Number cardNumber;
  private Number cardVerificationValue;
  private String type;
  private String status;
  private String validity;
}
