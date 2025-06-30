package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User user_payload;
	public Logger logger;
	@BeforeClass
	public void setup() {
		
		user_payload = new User();
		faker = new Faker();
		
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUsername(faker.name().username()+ System.currentTimeMillis()); 
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().safeEmailAddress());
		user_payload.setPassword(faker.internet().password(6, 12));
		user_payload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser() throws InterruptedException {
		
		
		logger.info("**************Creating User******************");
		Response response = UserEndPoint.createUser(user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**************User is created ******************");
	}
	
	
	@Test(priority=2)
	public void testGetUser() throws InterruptedException {
		
		logger.info("**************Reading User Data ******************");
		Response response = UserEndPoint.getUser(this.user_payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.jsonPath().getString("firstName"), user_payload.getFirstName());
		logger.info("**************User Info is Displayed******************");
		Thread.sleep(3000);
	}
	
	@Test(priority=3)
	public void testUpdateUser() throws InterruptedException {
		
		logger.info("**************Updating User Credentials******************");
		//update data using payload
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoint.updateUser(this.user_payload.getUsername(), user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		
		Thread.sleep(3000);
		logger.info("**************User is Updated******************");
		
		logger.info("**************Reading User Data After Update is completed******************");
		//Check the data after update
		Response response_after_update = UserEndPoint.getUser(this.user_payload.getUsername());
		response_after_update.then().log().body();
		response_after_update.then().log().body().statusCode(200);
		System.out.println("Expected First Name: " + user_payload.getFirstName());
		System.out.println("Actual First Name: " + response_after_update.jsonPath().get("firstName"));
		Assert.assertEquals(response_after_update.jsonPath().get("firstName"),user_payload.getFirstName());
		Assert.assertEquals(response_after_update.jsonPath().get("lastName"),user_payload.getLastName());
		Assert.assertEquals(response_after_update.jsonPath().get("email"),user_payload.getEmail());
		logger.info("**************Reading User Completed Successfully******************");
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		logger.info("**************Deleting User******************");
		Response response = UserEndPoint.deleteUser(this.user_payload.getUsername());
		response.then().log().all();
		response.then().log().body().statusCode(200);
		logger.info("**************User Successfully Deleted******************");
	}
	
	
}
