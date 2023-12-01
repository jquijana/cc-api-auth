package org.cc.api.auth;

import org.cc.api.auth.api.dto.UserRq;
import org.cc.api.auth.api.services.UserService;
import org.cc.api.auth.domain.entities.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    private static WebTestClient client;

    @LocalServerPort
    int port;

    @Autowired
    private UserService userService;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.client
                = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl("/users")
                .build();
    }

    @Test
    @Order(20)
    public void testGetAll() {
        createData();
        this.client
                .get()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.[0].name").isNotEmpty()
                .jsonPath("$.[0].email").isNotEmpty()
                .jsonPath("$.[0]..password").isNotEmpty();
    }

    @Test
    @Order(10)
    public void testCreate() {
        UserRq userRq = UserRq.builder()
                .email("jhosefQuijana@dominio.cl")
                .password("Abc12345")
                .name("jhosef")
                .build();

        this.client
                .post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRq), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    private void createData() {
        UserRq userRq = UserRq.builder()
                .email("jhosefQuijana@dominio.cl")
                .password("Abc12345")
                .name("jhosef")
                .build();

        this.client
                .post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRq), User.class)
                .exchange()
                .expectStatus().isCreated();
    }
}
