package com.bamin.woorder.common.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("[GET] /, 200 요청 성공")
    void successfullyLoadIndexPage() {
        webTestClient
                .get()
                .uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("[GET] /admin, 200 요청 성공")
    void successfullyLoadAdminIndexPage() {
        webTestClient
                .get()
                .uri("/admin")
                .exchange()
                .expectStatus().isOk();
    }
}