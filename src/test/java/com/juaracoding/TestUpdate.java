package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUpdate {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzYwNzQ3LCJleHAiOjE3MTk3NjQzNDd9.0wfFbzS-4g8jF-YCSxaITz9siuZjNjmRvnusxIG7U-n9jgs7wyUIP8LL-Qpb73xyjuvq--M0Tx0p10QE_VXyaw";


    @Test
    public void testUpdateAlbum() {
        String endpoint = baseUrl + "/albums/8";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Updated Album Title");
        requestBody.put("description", "Updated Album Description");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.put(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        String updatedTitle = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(updatedTitle, "Mayor Bintang 5");
    }
}
