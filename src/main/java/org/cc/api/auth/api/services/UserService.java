package org.cc.api.auth.api.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.cc.api.auth.api.dto.PhoneRq;
import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.api.excepcion.ResourceNotFoundException;
import org.cc.api.auth.domain.entities.Phone;
import org.cc.api.auth.domain.entities.User;
import org.cc.api.auth.domain.repositories.UserRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Mono<User> createUser(UserRq userRq) {
        return userRepository.findByEmail(userRq.getEmail())
                .flatMap(throwable -> Mono.error(new ResourceNotFoundException("El correo ya registrado")))
                .switchIfEmpty(userRepository.save(parseToUser(userRq)))
                .map(x -> (User) x);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    private User parseToUser(UserRq userRq) {
        return User.builder()
                .name(userRq.getName())
                .password(userRq.getPassword())
                .createdDate(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .email(userRq.getEmail())
                .isActive(true)
                .token(UUID.randomUUID())
                .phones(parseToPhone(userRq.getPhones()))
                .build();
    }

    private List<Phone> parseToPhone(List<PhoneRq> phones) {
        return phones != null ? phones.stream()
                .map(x -> Phone.builder()
                        .number(x.getNumber())
                        .cityCode(x.getCityCode())
                        .contryCode(x.getCountryCode())
                        .createdDate(LocalDateTime.now())
                        .modifiedDate(LocalDateTime.now())
                        .isActive(true)
                        .build())
                .collect(Collectors.toList()) : new ArrayList<>();
    }
}
