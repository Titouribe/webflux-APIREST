package com.rest.app.controllers;

import com.rest.app.domain.DTOS.ErrorDTO;
import com.rest.app.exceptions.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = WebExchangeBindException.class)
    public ResponseEntity<Map<String, ErrorDTO>> webExchangeBindExceptionHandler(WebExchangeBindException exception) {

        Map<String, ErrorDTO> errorDTOMap = new HashMap<>();

        exception.getAllErrors().forEach(error -> {
            ErrorDTO errorDTO = ErrorDTO.builder()
                    .errorCode(error.getCode())
                    .errorMessage(error.getDefaultMessage())
                    .build();
            errorDTOMap.put(error.getCode(), errorDTO);
        });

        return new ResponseEntity<>(errorDTOMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException exception){

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
