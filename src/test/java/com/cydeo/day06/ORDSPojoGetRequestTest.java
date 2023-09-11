package com.cydeo.day06;

import com.cydeo.pojo.Region;
import com.cydeo.utilities.HRTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

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

        System.out.println(region1.getRegion_id());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

}
