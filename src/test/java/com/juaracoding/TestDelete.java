package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDelete {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzcxMDY1LCJleHAiOjE3MTk3NzQ2NjV9.H6cfviL4uonH-lfYZqYWNRZwcfeVEfm4erGf43npMFArHzxqkrG69UaTtWJWZpamXnXgDIYd9VjT8t_JBLXBLg";
    String albumId = "8";

    @Test
    public void testDeleteAlbum() {
        String endpoint = baseUrl + "/albums/" + albumId;

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.delete(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
    }
}
