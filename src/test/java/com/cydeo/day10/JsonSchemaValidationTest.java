package com.cydeo.day10;

import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.*;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",15)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema

    @DisplayName("POST request and verify Schema")
    @Test
    public void postResponseSchemaValidation() {

        given()
                    .auth().basic("admin","admin")
                    .and()
                    .accept(ContentType.JSON) //what we are asking from api which is JSON response
                    .and()
                    .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                    .and()
                    .body(SpartanUtils.createSpartan()).log().all()
                .when()
                    .post("/api/spartans")
                .then().statusCode(201)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("spartanPostJsonSchema.json"))
                    .log().all();

    }




}