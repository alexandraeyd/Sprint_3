package com.ya;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import io.qameta.allure.junit4.DisplayName;

   public class CreateCourierWithInsufficientDataTest {
    CourierClient courierClient;
    Courier courier;
    int courierId;
    int statusCode;

   public CreateCourierWithInsufficientDataTest(){
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Check that courier create request without login returns 400 status code")
    public void courierWithoutLoginCanNotBeCreated(){
        courier = DataGenerator.getRandomCourierWithNullLogin();
        ValidatableResponse createResponse = courierClient.create(courier);
        statusCode = createResponse.extract().statusCode();

        assertThat("Courier without login is created", statusCode, not(equalTo(SC_CREATED)));
        assertThat("Wrong error code", statusCode, equalTo(SC_BAD_REQUEST));

    }

    @Test
    @DisplayName("Check that courier create request without password returns 400 status code")
    public void courierWithoutPasswordCanNotBeCreated(){
        courier = DataGenerator.getRandomCourierWithNullPassword();
        ValidatableResponse createResponse = courierClient.create(courier);
        statusCode = createResponse.extract().statusCode();

        assertThat("Courier without password is created", statusCode, not(equalTo(SC_CREATED)));
        assertThat("Wrong error code", statusCode, equalTo(SC_BAD_REQUEST));

    }

    @Test
    @DisplayName("Check that courier create request without first name returns 400 status code")
    public void courierWithoutFirstNameCanNotBeCreated(){
        courier = DataGenerator.getRandomCourierWithNullFirstName();
        ValidatableResponse createResponse = courierClient.create(courier);
        statusCode = createResponse.extract().statusCode();

        assertThat("Courier without first name is created", statusCode, not(equalTo(SC_CREATED)));
        assertThat("Wrong error code", statusCode, equalTo(SC_BAD_REQUEST));

    }

    @After
    public void tearDown(){
       if (statusCode == SC_CREATED) {
           courierId = courierClient.login(courier.getCredentials()).extract().path("id");
           courierClient.delete(courierId);
       }
    }

}

