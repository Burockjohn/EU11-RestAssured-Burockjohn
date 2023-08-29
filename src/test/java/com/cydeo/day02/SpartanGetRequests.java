package com.cydeo.day02;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class SpartanGetRequests {

    String baseUrl = "http://54.166.75.122:8000";

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must be 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing?
        //verify status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is JSON
        Assertions.assertEquals("application/json",response.contentType());

    }

//    Given accept type application/json
//    When user sends a get request to /api/spartans/3
//    Then status code must be 200
//    And response Content Type must be application/json
//    And response body should contain Fidole

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing?
        //verify status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is JSON
        Assertions.assertEquals("application/json",response.contentType());

        //verify containing Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

}
