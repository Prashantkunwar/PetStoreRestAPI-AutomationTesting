package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class TC230_StoreTest {
	
	Faker faker;
	Store store_payload;
	Logger logger;
	
	@BeforeClass
	void setup() {
		
		faker = new Faker();
		
		store_payload = new Store();
		
		
		store_payload.setPetId(faker.idNumber().hashCode());
		store_payload.setQuantity(faker.number().randomDigit());
		store_payload.setShipDate("2025-06-30T13:09:12.384+0000");
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	void testPostOrder() {
		logger.info("*********Creating Order ******************");
		Response response = StoreEndPoints.createOrder(store_payload);
		
		response.then().log().all();
		long generated_id = response.jsonPath().getLong("id");
		store_payload.setId(generated_id);

		  
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********* Order Creation Completed ******************");
		
	}

	@Test(priority=2, dependsOnMethods = {"testPostOrder"})
	void testGetOrder() {
	    logger.info("*********Getting Order ******************");
	    
	    Response response = StoreEndPoints.getOrder(store_payload.getId());
	    
	   
	    
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(), 200, "GET order failed");
	    Assert.assertEquals(response.jsonPath().getLong("id"), store_payload.getId());
	    
	    logger.info("*********Order Creation Retrieved ******************");
	}

	
	@Test(priority=3)
	void testGetInventory() {
		
		logger.info("*********Getting Order Inventory ******************");
		
		Response response = StoreEndPoints.getInventoryDetail();
		response.then().log().body();
		response.then().log().body().statusCode(200);
		logger.info("********* Order Invntory Retrived ******************");
	}
	
	@Test(priority=4)
	void testDeleteOrder() {
		logger.info("*********Deleting Order ******************");
		
		Response response =StoreEndPoints.deleteOrder(store_payload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********* Order Deletion Complete ******************");
		
	}
}
