package com.bamin.woorder.common.utils.easyrestassured;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

class EasyValidatableResponse {

    private ValidatableResponse validatableResponse;

    EasyValidatableResponse(final ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
    }

    void statusCode(final int statusCode) {
        validatableResponse.statusCode(statusCode);
    }

    void contentType(final ContentType contentType) {
        validatableResponse.contentType(contentType);
    }

    void expectBody(final EasyExpectBodies bodyExpects) {
        bodyExpects.keySet()
                .forEach(jsonPath ->
                        validatableResponse.body(jsonPath, bodyExpects.get(jsonPath)));
    }
}
