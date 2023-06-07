package api.test;

import api.endpoint.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {

    @Test(priority = 1,dataProvider="Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String UserID,String UserName,String FirstName,String LastName,String Email,String Password,String Phone )
    {
        User userPayload=new User();
        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

        Response response=UserEndpoints.createUser(userPayload);
       // response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider="UserNames",dataProviderClass = DataProviders.class)
    public void deleteTestUser(String userName){
       Response response= UserEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
