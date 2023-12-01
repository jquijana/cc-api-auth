package org.cc.api.auth.api.services;

import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.domain.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> createUser(UserRq authRq);

    Flux<User> findAll();
}
