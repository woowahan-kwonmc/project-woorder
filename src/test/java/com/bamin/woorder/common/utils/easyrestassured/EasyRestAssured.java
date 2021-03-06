package com.bamin.woorder.common.utils.easyrestassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class EasyRestAssured {

    private static final String METHOD = "method";
    private static final String PATH = "path";
    private static final String STATUS_CODE = "statusCode";
    private static final String TIMESTAMP = "timestamp";

    private RequestSpecification requestSpecification;
    private Response response;
    private String requestUrl;
    private String requestMethod;

    private EasyRestAssured(final RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public static EasyRestAssured givenBody(final Object object) {
        return new EasyRestAssured(
                given().
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        body(object)
        );
    }

    public static EasyRestAssured givenParams(final EasyGivenQueryParameters parameters) {
        return new EasyRestAssured(
                given().
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        queryParams(parameters.getParams())
        );
    }

    public static EasyRestAssured givenPathVariable(final EasyGivenPathVariables pathVariables) {
        return new EasyRestAssured(
                given().
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        pathParams(pathVariables.getVariables())
        );
    }

    public EasyRestAssured whenRequest(final String url, final EasyRestAssuredRequestMethod requestMethod) {
        this.requestUrl = url;
        whenGet(requestMethod);
        whenPost(requestMethod);
        whenPut(requestMethod);
        whenDelete(requestMethod);
        whenPatch(requestMethod);
        return this;
    }

    private void whenGet(final EasyRestAssuredRequestMethod requestMethod) {
        if (requestMethod.equals(GET)) {
            this.response = this.requestSpecification.when().get(this.requestUrl);
            this.requestMethod = GET.toString();
        }
    }

    private void whenPost(final EasyRestAssuredRequestMethod requestMethod) {
        if (requestMethod.equals(POST)) {
            this.response = this.requestSpecification.when().post(this.requestUrl);
            this.requestMethod = POST.toString();
        }
    }

    private void whenPut(final EasyRestAssuredRequestMethod requestMethod) {
        if (requestMethod.equals(PUT)) {
            this.response = this.requestSpecification.when().put(this.requestUrl);
            this.requestMethod = PUT.toString();
        }
    }

    private void whenPatch(final EasyRestAssuredRequestMethod requestMethod) {
        if (requestMethod.equals(PATCH)) {
            this.response = this.requestSpecification.when().patch(this.requestUrl);
            this.requestMethod = PATCH.toString();
        }
    }

    private void whenDelete(final EasyRestAssuredRequestMethod requestMethod) {
        if (requestMethod.equals(DELETE)) {
            this.response = this.requestSpecification.when().delete(this.requestUrl);
            this.requestMethod = DELETE.toString();
        }
    }

    public void thenExpectDescriptiveWith(final int statusCode,
                                          final String path,
                                          final EasyExpectBodies easyExpectBodies) {
        EasyValidatableResponse validatableResponse = new EasyValidatableResponse(
                this.response.then().
                        log().ifValidationFails().
                        log().ifError());
        validatableResponse.statusCode(statusCode);
        validatableResponse.contentType(ContentType.JSON);
        validatableResponse.expectBody(easyExpectBodies
                .insert(METHOD, is(requestMethod))
                .insert(PATH, is(path))
                .insert(STATUS_CODE, is(statusCode))
                .insert(TIMESTAMP, is(notNullValue()))
        );
    }
}
