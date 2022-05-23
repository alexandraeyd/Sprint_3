package com.ya;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginCourierTest {

    CourierClient courierClient;
    Courier courier;
    int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = DataGenerator.getRandomCourier();
        courierClient.create(courier);
    }

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }


    @Test
    @DisplayName("Check that login request with valid credentials returns 200 status code and courier id")
    public void courierCanLoginWithValidCredentials(){

        ValidatableResponse loginResponse = courierClient.login(courier.getCredentials());
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");

        assertThat("Courier cannot login", statusCode, equalTo(SC_OK));
        assertThat("Courier id is invalid", courierId, greaterThan(0));

    }

    @Test
    @DisplayName("Check that login request with invalid login returns 404 status code")
    public void courierCanNotLoginWithInvalidLogin(){
        CourierCredentials credentialsWithInvalidLogin = courier.getCredentials();
        credentialsWithInvalidLogin.setLogin("invalidLogin");
        ValidatableResponse loginResponse = courierClient.login(credentialsWithInvalidLogin);
        int statusCode = loginResponse.extract().statusCode();

        assertThat("Courier logged in with invalid login", statusCode, not(equalTo(SC_OK)));
        assertThat("Wrong error code", statusCode, equalTo(SC_NOT_FOUND));
    }

    @Test
    @DisplayName("Check that login request with invalid password returns 404 status code")
    public void courierCanNotLoginWithInvalidPassword(){
        CourierCredentials credentialsWithInvalidPassword = courier.getCredentials();
        credentialsWithInvalidPassword.setPassword("invalidPassword");
        ValidatableResponse loginResponse = courierClient.login(credentialsWithInvalidPassword);
        int statusCode = loginResponse.extract().statusCode();

        assertThat("Courier logged in with invalid password", statusCode, not(equalTo(SC_OK)));
        assertThat("Wrong error code", statusCode, equalTo(SC_NOT_FOUND));
    }

    @Test
    @DisplayName("Check that login request with nonexistent user data returns 404 status code")
    public void courierCanNotLoginWithNonexistentUserData(){
        courier = DataGenerator.getRandomCourier();
        ValidatableResponse loginResponse = courierClient.login(courier.getCredentials());
        int statusCode = loginResponse.extract().statusCode();

        assertThat("Courier logged in with nonexistent user data", statusCode, not(equalTo(SC_OK)));
        assertThat("Wrong error code", statusCode, equalTo(SC_NOT_FOUND));
    }

    @Test
    @DisplayName("Check that login request without login returns 400 status code")
    public void courierCanNotLoginWithoutLogin(){
        CourierCredentials credentialsWithoutLogin = courier.getCredentials();
        credentialsWithoutLogin.setLogin(null);
        ValidatableResponse loginResponse = courierClient.login(credentialsWithoutLogin);
        int statusCode = loginResponse.extract().statusCode();

        assertThat("Courier logged in without login", statusCode, not(equalTo(SC_OK)));
        assertThat("Wrong error code", statusCode, equalTo(SC_BAD_REQUEST));
    }

    @Test
    @DisplayName("Check that login request without password returns 400 status code")
    public void courierCanNotLoginWithoutPassword(){
        CourierCredentials credentialsWithoutPassword = courier.getCredentials();
        credentialsWithoutPassword.setPassword(null);
        ValidatableResponse loginResponse = courierClient.login(credentialsWithoutPassword);
        int statusCode = loginResponse.extract().statusCode();

        assertThat("Courier logged in without password", statusCode, not(equalTo(SC_OK)));
        assertThat("Wrong error code", statusCode, equalTo(SC_BAD_REQUEST));
    }
}
