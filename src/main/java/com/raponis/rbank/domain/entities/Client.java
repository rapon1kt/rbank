package com.raponis.rbank.domain.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "clients")
public class Client {

  @Id
  private String id;
  private String photo;
  private String name;
  private String email;
  private String password;
  private Number phone;
  private Address address;
  private String officialDocument;
  private Number rent;
  private List<Account> accounts;
}
