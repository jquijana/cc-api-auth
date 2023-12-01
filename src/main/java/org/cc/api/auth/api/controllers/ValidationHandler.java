package org.cc.api.auth.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.cc.api.auth.api.dto.ResponseDTO;
import org.cc.api.auth.api.excepcion.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<ResponseDTO> onResourceFound(ResourceNotFoundException exception) {
        log.error("No resource found exception occurred: {} ", exception.getMessage());

        ResponseDTO response = ResponseDTO.builder()
                .code("2")
                .message(exception.getMessage())
                .build();

        return Mono.just(response);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ResponseDTO> handleException(WebExchangeBindException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseDTO response = ResponseDTO.builder()
                .code("2")
                .message("Errores de validacion")
                .data(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}