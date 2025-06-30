package api.endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	static ResourceBundle getURL() {
		ResourceBundle bundle = ResourceBundle.getBundle("routes");
		return bundle;
	}

	public static Response createOrder(Store storePayload) {

		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(storePayload)

							.when()
							.post(getURL().getString("store_post_order_url"));

		return response;

	}

	public static Response getOrder(long orderID) {

		String get_url = getURL().getString("store_get_order_url");

		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("orderid", orderID)
							.when()
								.get(get_url);

		return response;
	}
	
	public static Response getInventoryDetail() {
		
		String get_url = getURL().getString("store_get_inventory_url");
		
		Response response = given()
								.accept(ContentType.JSON)
			
							.when()
								.get(get_url);
		
		return response;
	}
	
	public static Response deleteOrder(long OrderID) {
		
		String delete_url = getURL().getString("store_delete_order_url");
		
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("orderid",OrderID)
							
							.when()
								.delete(delete_url);
		
		return response;
				
	}

}
