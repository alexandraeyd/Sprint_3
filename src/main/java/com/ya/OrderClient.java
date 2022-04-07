package com.ya;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;

public class OrderClient extends ScooterRestClient{
    private static final String ORDER_PATH = "/api/v1/orders/";

    @Step ("Create order")
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step ("Get orders list")
    public ValidatableResponse getOrders(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

}
