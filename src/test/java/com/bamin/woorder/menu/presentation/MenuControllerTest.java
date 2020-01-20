package com.bamin.woorder.menu.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MenuControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("[GET] /menu/enroll, 200 요청 성공")
    void successfullyGetLoginView() {
        webTestClient
                .get()
                .uri("/menu/enroll")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("[GET] /menu/edit, 200 요청 성공")
    void successfullyGetSignUpView() {
        webTestClient
                .get()
                .uri("/menu/edit")
                .exchange()
                .expectStatus().isOk();
    }
}