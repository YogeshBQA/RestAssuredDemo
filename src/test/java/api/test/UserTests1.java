package api.test;

import api.endpoint.UserEndpoints1;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests1 {
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
        Response response=UserEndpoints1.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void testGetUser( )
    {
        Response response=UserEndpoints1.readUser(this.userPayload.getUsername());
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

        Response response=UserEndpoints1.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        response.then().log().body().statusCode(200);//chai assetions comes with restassured
        Assert.assertEquals(response.getStatusCode(),200);//testng assertion

    }

    @Test(priority = 4)
    public void testDeleteUser( )
    {
        Response response=UserEndpoints1.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);


    }

}
