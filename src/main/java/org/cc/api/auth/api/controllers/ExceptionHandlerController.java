package org.cc.api.auth.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.cc.api.auth.api.dto.ErrorResponse;
import org.cc.api.auth.api.excepcion.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<ErrorResponse> onResourceFound(ResourceNotFoundException exception) {
        log.error("No resource found exception occurred: {} ", exception.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .code("2")
                .message(exception.getMessage())
                .build();

        return Mono.just(response);
    }
}