package org.cc.api.auth.api.services;

import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.api.dto.UserRs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<UserRs> createUser(UserRq authRq);

    Flux<UserRs> findAll();
}
