package api.endpoints;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoint2 {

	static ResourceBundle getURL() {
	ResourceBundle bundle = ResourceBundle.getBundle("routes");
	return bundle;
	}
	//CRUD methods implementations
	
	public static Response createUser(User payload){
		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);
		return response;	
	}
	
	public static Response getUser(String userName) {
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.get(get_url);
		
		return response;
		
	}
	
	public static Response updateUser(String userName,User payload) {
		String update_url = getURL().getString("update_url");
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
		
		.when()
			.put(update_url);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url= getURL().getString("delete_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		.when()
			.delete(delete_url);
		return response;
	}
}
