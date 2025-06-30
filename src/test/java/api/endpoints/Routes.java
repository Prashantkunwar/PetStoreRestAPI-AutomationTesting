package api.endpoints;

/*

Swagger URL --> https://petstore.swagger.io


Create User  --> https://petstore.swagger.io/v2/user
GET  User    --> https://petstore.swagger.io/v2/user/{username}
Update User  --> https://petstore.swagger.io/v2/user/{username} 
Delete User  --> https://petstore.swagger.io/v2/user/{username}

 
Base Url :  https://petstore.swagger.io/v2

* */

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	// User model
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	


}
