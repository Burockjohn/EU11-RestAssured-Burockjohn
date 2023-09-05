package com.cydeo.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 14 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Glen
                lastName Funk
                batch 3
                section N/A
                emailAddress lroutham0@opera.com
                companyName Gabtune
                state New Jersey
                zipCode 72475

                using JsonPath
             */

        Response response = given().accept(ContentType.JSON)
                                    .and().pathParam("id", 14)
                            .when().get("/student/{id}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200,response.statusCode());
        assertTrue(response.getHeaders().toString().contains("date"));
        //assertEquals("Glen",jsonPath.getString());



    }

}
