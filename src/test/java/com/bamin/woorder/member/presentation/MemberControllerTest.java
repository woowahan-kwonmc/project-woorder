package com.bamin.woorder.member.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("[GET] /member/login, 200 요청 성공")
    void successfullyGetLoginView() {
        webTestClient
                .get()
                .uri("/member/login")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("[GET] /member/signup, 200 요청 성공")
    void successfullyGetSignUpView() {
        webTestClient
                .get()
                .uri("/member/signup")
                .exchange()
                .expectStatus().isOk();
    }
}