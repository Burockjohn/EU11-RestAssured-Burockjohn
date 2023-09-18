package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @Test
    @DisplayName("GET request to /api/spartans and verify xml")
    public void getSpartanXml() {

        // we will ask for xml response
        // assert status code 200
        // assert content type is xml
        // verify first spartan name is Meade

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .log().all();

    }

    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath() {

        Response response = given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        // get response xml body/payload and save inside the xmlPath object
        XmlPath xmlPath = response.xmlPath();

        // get first spartan name
        String name = xmlPath.getString("List.item[0].name");
        System.out.println(name);

        // get the 3rd spartan id number
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println(id);

        // how to get all names and save into list of string
        List<String> names = xmlPath.getList("List.item.name");
        System.out.println(names);


    }


}
