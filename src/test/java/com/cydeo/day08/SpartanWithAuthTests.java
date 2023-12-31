package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .when()
                .get("api/spartans")
                .then()
                .statusCode(401)
                .log().all();

    }

    @DisplayName("GET /api/spartans as a admin user expect 200")
    @Test
    public void testAdmin() {

        given()
                .auth().basic("admin","admin")
                .when()
                .get("api/spartans")
                .then()
                .statusCode(200)
                .log().all();

    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete(){
        given()
                .auth().basic("editor","editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",30)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
    }

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able to take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */


}
