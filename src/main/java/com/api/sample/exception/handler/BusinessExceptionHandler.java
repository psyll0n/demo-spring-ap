package com.api.sample.exception.handler;

import com.api.sample.exception.BusinessException;
import com.api.sample.model.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<StandardResponse> handleBusinessException(BusinessException e) {
    StandardResponse response = StandardResponse.builder()
        .statusCode(e.getErrorCode())
        .message(e.getMessage())
        .data(e.getData())
        .build();
    return ResponseEntity.status(e.getHttpStatus()).body(response);
  }
}
