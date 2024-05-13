package com.mkiats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mkiats.backtest.exceptions.RatioDependencyCycleException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> jsonProcessingExceptionHandler (JsonProcessingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing json input...");
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<?> jsonMappingExceptionHandler (JsonMappingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error mapping json input...");
    }

    @ExceptionHandler(RatioDependencyCycleException.class)
    public ResponseEntity<?> ratioDependencyCycleExceptionHandler (JsonMappingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cycle detected in FinancialRatioStrategyList");
    }
}
