package com.api.sample.exception;

import com.api.sample.common.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

  private final HttpStatus httpStatus;
  private final String errorCode;
  private final String message;
  private final transient Object data;

  public BusinessException(HttpStatus httpStatus, String errorCode, String message,
      Object customObject, String identifier) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.message = "[" + StringUtils.defaultIfBlank(identifier, "No identifier") + "] - " + message;
    this.data = customObject;
  }

  public static BusinessException fromErrorCode(HttpStatus httpStatus,
      ErrorCodeEnum errorCodeEnum, Object customObject, String identifier) {
    return new BusinessException(
        httpStatus,
        errorCodeEnum.getCode(),
        errorCodeEnum.getMessage(),
        customObject,
        identifier);
  }

  public static BusinessException fromErrorCode(HttpStatus httpStatus,
      ErrorCodeEnum errorCodeEnum, String identifier) {
    return fromErrorCode(httpStatus, errorCodeEnum, null, identifier);
  }
}
