package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUpdate {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzcxMDY1LCJleHAiOjE3MTk3NzQ2NjV9.H6cfviL4uonH-lfYZqYWNRZwcfeVEfm4erGf43npMFArHzxqkrG69UaTtWJWZpamXnXgDIYd9VjT8t_JBLXBLg";


    @Test
    public void testUpdateAlbum() {
        String albumId = "9";
        String endpoint = baseUrl + "/albums/" + albumId;

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Mayor Teddy");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.get(endpoint);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Memastikan status code adalah 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code tidak sesuai!");

        String updatedTitle = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(updatedTitle, "Mayor Teddy");
    }
}
