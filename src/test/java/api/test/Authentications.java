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
//syntax to authenticate Bearertoken
    @Test(priority = 4)
    void testBearerTokenAuthentication(){
        String bearerToken="";//generate token example as created in github
                given()
                    .headers("Authorization","Bearer"+bearerToken)
                .when()
                    .get ("url")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    //syntax to authenticate oAuth1
    @Test(priority = 5)
    void testoAuth1Authentication(){

                given()
                    .auth().oauth("consumerKey","consumerSecrete","accesstoken","tokenSecrete")
                .when()
                    .get ("url")
                .then()
                        .statusCode(200)
                        .log().all();
    }

    //syntax to authenticate oAuth1
    @Test(priority = 6)
    void testoAuth2Authentication(){

                given()
                    .auth().oauth2("token")//need to generate token manually by following process
                .when()
                    .get ("url")
                .then()
                    .statusCode(200)
                    .log().all();
    }
//syntax for APIkey authentication.
    @Test(priority = 7)
    void testAPIKeyAuthentication(){

                given()
                    .queryParam("appid","")//need to generate token manually by following process
                .when()
                    .get ("url")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
