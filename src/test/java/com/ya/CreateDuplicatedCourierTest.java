package com.ya;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateDuplicatedCourierTest {

    CourierClient courierClient;
    Courier courier;
    int courierId;


    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = DataGenerator.getRandomCourier();
        courierClient.create(courier);
    }

    @Test
    @DisplayName("Check that courier create request returns 409 status code when courier already exists")
    public void courierCanNotBeDuplicated(){

        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();

        assertThat("The same courier is created", statusCode, equalTo(SC_CONFLICT));
    }

    @Test
    @DisplayName("Check that courier create request returns 409 status code when login already exists")
    public void courierWithTheSameLoginCanNotBeCreated(){
        courier.setPassword(courier.getPassword()+"NEW");
        courier.setFirstName(courier.getFirstName()+"NEW");
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();

        assertThat("Courier with the same login is created", statusCode, equalTo(SC_CONFLICT));
    }


}
