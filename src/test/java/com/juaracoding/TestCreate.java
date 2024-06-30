package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCreate {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzYwNzQ3LCJleHAiOjE3MTk3NjQzNDd9.0wfFbzS-4g8jF-YCSxaITz9siuZjNjmRvnusxIG7U-n9jgs7wyUIP8LL-Qpb73xyjuvq--M0Tx0p10QE_VXyaw";

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
