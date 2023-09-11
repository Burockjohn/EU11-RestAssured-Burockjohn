package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORDSPojoGetRequestTest extends HRTestBase {

    /*

    {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://54.166.75.122:1000/ords/hr/regions/1"
                }
            ]
        }

     */

    @Test
    public void regionTest() {

        List<Region> regions = given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(200).and().contentType("application/json")
                .extract().response().jsonPath().getList("items", Region.class);

        System.out.println(regions.get(0));
        System.out.println(regions.get(0).getLinks().get(0).getHref());

    }

    @Test
    public void regionTest2() {
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {

        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);

    }

     /*

        send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields

     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest() {
        // send a get request and save everything inside the regions object
        // since we prepare pojo also for the all properties we don't need to use any path so as() method is enough
        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        // verify count is 4
        assertThat(regions.getCount(),is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();
        //get list of regions out of regions object
        List<Region> items = regions.getItems();

        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());
        }
        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);
        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));

    }

}
