package com.ya;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;



import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateCourierTest {

    CourierClient courierClient;
    Courier courier;
    int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = DataGenerator.getRandomCourier();
    }

    @Test
    @DisplayName("Check 201 status code and response for creating courier")
    public void courierCanBeCreated(){

        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        boolean responseOk = createResponse.extract().path("ok");

        assertThat("Courier is not created", statusCode, equalTo(SC_CREATED));
        assertThat("Ok message is not true", responseOk, equalTo(true));
    }


    @After
    public void tearDown(){
        courierId = courierClient.login(courier.getCredentials()).extract().path("id");
        courierClient.delete(courierId);
    }
}
