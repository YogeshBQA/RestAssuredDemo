package api.test;

import api.endpoint.UserEndpoints;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import api.payload.User;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class UserTests {
Faker faker;
User userPayload;
    @BeforeClass
    public void setupData(){
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
   public void testPostUser( )
    {
        Response response=UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void testGetUser( )
    {
        Response response=UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test(priority = 3)
    public void testUpdateUser( )
    {
//update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        response.then().log().body().statusCode(200);//chai assetions comes with restassured
        Assert.assertEquals(response.getStatusCode(),200);//testng assertion

    }

    @Test(priority = 4)
    public void testDeleteUser( )
    {
        Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);


    }

}
