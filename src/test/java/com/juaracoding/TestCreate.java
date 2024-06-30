package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreate {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzcxMDY1LCJleHAiOjE3MTk3NzQ2NjV9.H6cfviL4uonH-lfYZqYWNRZwcfeVEfm4erGf43npMFArHzxqkrG69UaTtWJWZpamXnXgDIYd9VjT8t_JBLXBLg";

    @Test
    public void testCreateAlbum() {
        String endpoint = baseUrl + "/albums";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "New Album");
        requestBody.put("description", "Album description");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);

        String albumId = response.getBody().jsonPath().getString("id");
        Assert.assertNotNull(albumId);
    }
}
