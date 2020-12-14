package com.example.product.exception.handler;

import com.example.product.exception.ProductDBException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        log.error("exception details {0}", exception);
        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductDBException.class)
    public final ResponseEntity<Object> handleProductDBException(ProductDBException exception) {
        log.error("exception details {0}", exception);
        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
