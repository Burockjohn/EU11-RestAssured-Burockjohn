package com.cydeo.day06;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
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
        Spartan spartan8 = response.as(Spartan.class);
        System.out.println(spartan8);
        System.out.println("spartan8.getId() = " + spartan8.getId());
        System.out.println("spartan8.getGender() = " + spartan8.getGender());



    }

}
