package com.ya;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;


public class DataGenerator {

    public static Courier getRandomCourier() {
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public static Courier getRandomCourierWithNullLogin(){
        String courierLogin = null;
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public static Courier getRandomCourierWithNullPassword(){
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = null;
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public static Courier getRandomCourierWithNullFirstName(){
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = null;
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public static Order getRandomOrder() {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomAlphabetic(10);
        Random random = new Random();
        int rentTime = random.nextInt(1000);
        String deliveryDate = "2022-10-01";
        String comment = RandomStringUtils.randomAlphabetic(10);
        String[] color = new String[100];
        color[0]= RandomStringUtils.randomAlphabetic(10);

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
