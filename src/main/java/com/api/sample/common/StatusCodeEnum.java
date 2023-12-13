package com.api.sample.common;

/**
 * This contains only statusCode-to-statusMessage mappings. This is typically used to populate the
 * "statusCode" field in API responses.
 *
 * @see ErrorCodeEnum for unhappy path mappings.
 */

public enum StatusCodeEnum implements BaseCodeEnum {
  STA_5000_SUCCESS("5000", "Request successfully processed");

  private final String statusCode;
  private final String statusMessage;

  StatusCodeEnum(String statusCode, String statusMessage) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
  }

  @Override
  public String getCode() {
    return this.statusCode;
  }

  @Override
  public String getMessage() {
    return this.statusMessage;
  }
  }