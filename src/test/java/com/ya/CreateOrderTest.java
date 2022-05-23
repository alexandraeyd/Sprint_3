package com.ya;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String[] color;
    private OrderClient orderClient;
    private Order order;
    private int expectedStatusCode;

    public CreateOrderTest(String[] color, int expectedStatusCode) {
        this.color = color;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][] {
                {new String[]{"BLACK"}, 201},
                {new String[]{"CREY"}, 201},
                {new String[]{"CREY", "BLACK"}, 201},
                {new String[]{""}, 201}
        };
    }

    @Before
    public void setUp(){
        orderClient = new OrderClient();
        order = DataGenerator.getRandomOrder();
        order.setColor(color);
    }

    @Test
    @DisplayName("Check that order create request returns 201 status code and track number")
    public void orderCanBeCreated() {

        ValidatableResponse createResponse = orderClient.create(order);
        int statusCode = createResponse.extract().statusCode();
        int track = createResponse.extract().path("track");

        assertThat("Order is not created", statusCode, equalTo(expectedStatusCode));
        assertThat("No track number in response", track, greaterThan(0));

    }

}
