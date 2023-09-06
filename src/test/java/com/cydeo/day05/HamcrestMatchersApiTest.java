package com.cydeo.day05;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

       /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("http://54.152.219.47:8000/api/spartans/{id}")
                .then().log().all()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106)).log().all();
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 14)
                .when()
                .get("https://api.training.cydeo.com/student/{id}")
                .then().log().all()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("students[0].firstName", is("Glen"))
                .body("students[0].lastName", is("Funk"))
                .body("students[0].gender", equalTo("Female"));

    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest() {

        //verify Alexander,Darleen,Sean inside the all teachers
        given()
                .accept(ContentType.JSON).when()
                .get("https://api.training.cydeo.com/student/all")
                .then().log().all()
                .statusCode(200)
                .and()
                .body("students.firstName", hasItems("Glen", "Mike"));


    }

}
