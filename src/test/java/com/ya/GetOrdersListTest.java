package com.ya;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetOrdersListTest {

    OrderClient orderClient;

    public GetOrdersListTest(){
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check that get orders list request returns 200 status code and list of orders")
    public void ordersListCanBeReturned(){
        ValidatableResponse response = orderClient.getOrders();
        int statusCode = response.extract().statusCode();
        ArrayList orders = response.extract().path("orders");


        assertThat("Orders list is not returned", statusCode, equalTo(SC_OK));
        assertThat("Ok message is not true", orders, notNullValue());
    }
}
