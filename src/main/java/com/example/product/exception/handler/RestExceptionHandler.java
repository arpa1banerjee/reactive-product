package com.example.product.exception.handler;

import com.example.product.dto.ErrorModel;
import com.example.product.exception.ProductDBException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    private static final String EXCEPTION_MSG = "exception details {}";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception exception) {
        log.error(EXCEPTION_MSG, exception);
        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public final ResponseEntity<Object> handleMethodWebExchangeBindException(WebExchangeBindException exception) {
        log.error(EXCEPTION_MSG, exception);
        List<ErrorModel> errorModels = exception.getBindingResult()
                .getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.BAD_REQUEST.value(), "Invalid parameter(s)", errorModels), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        log.error(EXCEPTION_MSG, exception);
        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductDBException.class)
    public final ResponseEntity<Object> handleProductDBException(ProductDBException exception) {
        log.error(EXCEPTION_MSG, exception);
        return new ResponseEntity<>(ApiResponse.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
