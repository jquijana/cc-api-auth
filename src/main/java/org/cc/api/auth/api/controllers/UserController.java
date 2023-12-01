package org.cc.api.auth.api.controllers;

import lombok.RequiredArgsConstructor;
import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.api.services.IUserService;
import org.cc.api.auth.domain.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {
    private final IUserService userService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> createUser(@RequestBody @Valid UserRq userRq) {
        return userService.createUser(userRq);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<User> findAllUser() {
        return userService.findAll();
    }
}
