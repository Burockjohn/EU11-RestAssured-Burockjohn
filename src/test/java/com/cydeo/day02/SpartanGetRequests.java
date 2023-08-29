package com.cydeo.day02;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(response.statusCode(),200);

        //verify content type is JSON
        Assert.assertEquals(response.contentType(),"application/json");

    }

}
