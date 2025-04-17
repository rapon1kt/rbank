package com.raponis.rbank.domain.entities;

import org.springframework.data.annotation.Id;

public class Address {

  @Id
  private String id;
  private String cep;
  private String country;
  private String state;
  private String city;
  private String street;
  private Number houseNumber;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Number getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(Number houseNumber) {
    this.houseNumber = houseNumber;
  }

}
