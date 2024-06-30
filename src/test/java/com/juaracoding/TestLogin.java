package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin {

    String baseUrl = "http://localhost:8081/api";
    String token;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void testLogin() {
        String endpoint = baseUrl + "/auth/signin"; // Memperbaiki endpoint

        JSONObject requestBody = new JSONObject();
        requestBody.put("usernameOrEmail", "zen");
        requestBody.put("password", "password");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        token = response.getBody().jsonPath().getString("accessToken");
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testLoginInvalid() {
        String endpoint = baseUrl + "/auth/signin"; // Memperbaiki endpoint

        JSONObject requestBody = new JSONObject();
        requestBody.put("usernameOrEmail", "aselole");
        requestBody.put("password", "password");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 401); // Memastikan status code yang benar
        String error = response.getBody().jsonPath().getString("error");
        System.out.println(error);
        Assert.assertNotNull(error);
    }
}
