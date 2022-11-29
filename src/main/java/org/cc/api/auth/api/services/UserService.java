package org.cc.api.auth.api.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.api.dto.UserRs;
import org.cc.api.auth.api.excepcion.ResourceNotFoundException;
import org.cc.api.auth.domain.entities.User;
import org.cc.api.auth.domain.repositories.UserRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@PropertySource(value = {"classpath:application.yaml"})
@ConfigurationProperties(prefix = "messsages")
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Getter
    @Setter
    private String emailExistsMessage;

    @Override
    public Mono<UserRs> createUser(UserRq userRq) {
        return userRepository.findByEmail(userRq.getEmail())
                .switchIfEmpty(userRepository.save(parseEntity(userRq)))
                .map(this::parseDto)
                .onErrorResume(throwable -> Mono.error(new ResourceNotFoundException(throwable.getMessage())));
    }

    @Override
    public Flux<UserRs> findAll() {
        return userRepository.findAll().map(this::parseDto);
    }

    private User parseEntity(UserRq userRq) {
        return User.builder()
                .name(userRq.getName())
                .password(userRq.getPassword())
                .createdDate(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .email(userRq.getEmail())
                .isActive(true)
                .token(UUID.randomUUID())
                .build();
    }

    private UserRs parseDto(User x) {
        return UserRs.builder()
                .userId(x.getUserId())
                .name(x.getName())
                .email(x.getEmail())
                .password(x.getPassword())
                .createdDate(x.getCreatedDate())
                .isActive(x.getIsActive())
                .lastLoginDate(x.getLastLoginDate())
                .modifiedDate(x.getModifiedDate())
                .token(x.getToken())
                .build();
    }
}
