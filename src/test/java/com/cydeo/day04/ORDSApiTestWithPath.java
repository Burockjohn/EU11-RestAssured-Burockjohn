package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                                    .and().queryParam("q", "{\"region_id\":2}")
                            .when()
                                    .get("/countries");

        assertEquals(200, response.statusCode());
        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print http://54.166.75.122:1000/ords/hr/countries/CA
        String canadaHref = response.path("items[2].links[0].href");
        System.out.println("canadaHref = " + canadaHref);

        //get me all country names
        List<String> countries = response.path("items.country_name");
        System.out.println(countries);

        //assert that all regions ids are equal to 2
        List<Integer> regionIDs = response.path("items.region_id");
        for (Integer each : regionIDs) {
            assertEquals(2, each);
        }

    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job id
        List<String> allJobIDs = response.path("items.job_id");
        for (String jobID : allJobIDs) {
            System.out.println(jobID);
            assertEquals("IT_PROG",jobID);
        }

    }

}
