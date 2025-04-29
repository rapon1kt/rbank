package com.raponis.rbank.domain.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
  private LocalDateTime timestamp;
  private int statusCode;
  private String errorCode;
  private String message;
  private String path;

  public ErrorResponse(int statusCode, String errorCode, String message, String path) {
    this.timestamp = LocalDateTime.now();
    this.statusCode = statusCode;
    this.errorCode = errorCode;
    this.message = message;
    this.path = path;
  }
}
