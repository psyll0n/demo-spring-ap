package com.api.sample.common;

/**
 * This contains only statusCode-to-errorMessage mapping. This is typically used to populate the
 * "statusCode" field in API responses.
 *
 * @see StatusCodeEnum for happy path mappings.
 */
public enum ErrorCodeEnum implements BaseCodeEnum {

  // Task related errors
  ERR_1000_TASK_NOT_FOUND("1000", "Task Not found");

  private final String statusCode;
  private final String errorMessage;

  ErrorCodeEnum(String statusCode, String errorMessage) {
    this.statusCode = statusCode;
    this.errorMessage = errorMessage;
  }

  @Override
  public String getCode() {
    return this.statusCode;
  }

  @Override
  public String getMessage() {
    return this.errorMessage;
  }
}
