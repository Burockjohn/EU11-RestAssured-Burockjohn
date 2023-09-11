package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        // get the first spartan from content list and put inside spartan object
        Spartan firstSpartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println(firstSpartan);
        System.out.println("firstSpartan.getName() = " + firstSpartan.getName());

    }

    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);
        System.out.println(searchResult.getContent().get(0).getName());
    }

    @DisplayName("FET /spartans/search and save as List<Spartan>")
    @Test
    public void test4() {

        List<Spartan> spartanList = given().accept(ContentType.JSON)
                                            .and().queryParams("nameContains", "a", "gender", "Male")
                                    .when()
                                            .get("api/spartans/search")
                                    .then()
                                            .statusCode(200)
                                            .extract().jsonPath().getList("content", Spartan.class);

        System.out.println(spartanList.get(0).getName());

    }

}
