package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTTests {
	
	
	@Test(priority=1,dataProvider= "Data", dataProviderClass = DataProviders.class)
	public void testPostDDTusers(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone) {
		User user_Payload = new User();
		user_Payload.setId(Integer.parseInt(UserID));
		user_Payload.setUsername(UserName);
		user_Payload.setUsername(UserName);
		user_Payload.setFirstName(FirstName);
		user_Payload.setLastName(LastName);
		user_Payload.setEmail(Email);
		user_Payload.setPassword(Password);
		user_Payload.setPhone(Phone);
		user_Payload.setUserStatus(0);
		
		
		
		Response response = UserEndPoint.createUser(user_Payload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
	}

	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class,dependsOnMethods= {"testPostDDTusers"})
	public void testDeleteDDTusers(String UserName) {
		
		Response response = UserEndPoint.deleteUser(UserName);
		response.then().log().body().statusCode(200);
	}

}
