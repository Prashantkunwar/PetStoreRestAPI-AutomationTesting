package api.endpoints;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoint {

	
	//CRUD methods implementations
	
	public static Response createUser(User payload){
		
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
		return response;	
	}
	
	public static Response getUser(String userName) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.get(Routes.get_url);
		
		return response;
		
	}
	
	public static Response updateUser(String userName,User payload) {
		
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
		
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
		return response;
	}
}
