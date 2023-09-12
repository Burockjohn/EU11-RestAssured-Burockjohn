package com.cydeo.utilities;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.*;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://54.166.75.122:7000/";

    }

}
