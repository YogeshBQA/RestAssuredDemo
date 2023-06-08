package api.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.File;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidations {
@Test
    void jsonSchemaValidations()
    {

        //base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/2";

        //obtain response
        given()
                .when().get()

                //verify JSON Schema
                .then().assertThat()
                .body(JsonSchemaValidator
                 .matchesJsonSchema(new File("C:\\Users\\Yogesh Badhe\\Desktop\\WorkSpace_Yogesh\\RestAssuredApiAutomationDemo\\src\\test\\resources\\storeJsonSchema.json")));
    }
}


