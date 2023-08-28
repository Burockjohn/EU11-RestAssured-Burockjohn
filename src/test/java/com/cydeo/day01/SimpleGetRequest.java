package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class SimpleGetRequest {

    String url = "http://54.166.75.122:8000/api/spartans";

    @Test
    public void test1() {

        //send a request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }

}
