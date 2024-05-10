package com.alekseyz.testtask.tracktime.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    ResponseEntity<Object> handleAllException(Exception e) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), "Произошла ошибка, идентификатор ошибки: " + logError(e));
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    private String logError(Exception e) {
        String stringUUID = UUID.randomUUID().toString();
        log.error("Ошибка: {} , причина : {} , идентификатор ошибки : {}", e.getMessage(), e.getCause(), stringUUID);
        return stringUUID;
    }




}
