package com.ya;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;

public class CourierClient extends ScooterRestClient{

    private static final String COURIER_PATH = "/api/v1/courier/";

    @Step ("Login with credentials {credentials}")
    public ValidatableResponse login (CourierCredentials credentials){
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    @Step ("Create courier")
    public ValidatableResponse create (Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();

    }

    @Step ("Delete courier")
    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + ":" + courierId)
                .then();
    }
}
