package api.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {

    @Test(priority = 1)
    void testBasicAuthentication(){
        given()
                .auth().basic("postman","password")
                .when()
                .get ("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
           }

    @Test(priority = 2)
    void testDigestAuthentication(){
        given()
                .auth().digest("postman","password")
                .when()
                .get ("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    void testPreemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get ("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
}
