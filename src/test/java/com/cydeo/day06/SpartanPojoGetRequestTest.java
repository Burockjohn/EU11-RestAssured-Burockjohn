package com.cydeo.day06;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 8)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        // de-serialize --> JSON to POJO (java custom class)
        // 2 different way to do this
        // 1. using as() method
        // we convert json response to spartan object with the help of jackson
        // as() method uses jackson to de-serialize (converting JSON to Java  class)
        Spartan spartan8 = response.as(Spartan.class);
        System.out.println(spartan8);
        System.out.println("spartan8.getId() = " + spartan8.getId());
        System.out.println("spartan8.getGender() = " + spartan8.getGender());

        // 2. way of de-serialize json to java
        // using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s8 = jsonPath.getObject("", Spartan.class);
        System.out.println(s8);
        System.out.println("s8.getName() = " + s8.getName());
        System.out.println("s8.getPhone() = " + s8.getPhone());


    }

}
