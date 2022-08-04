package oAuth;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class oAuthExample {
	
	@Test
	public void test1() {
		
		Response response = RestAssured.given().baseUri("http://18.206.229.7:8088")
												.auth().preemptive().basic("rest-assured", "password")
												.contentType("application/x-www-form-urlencoded")
												.formParam("grant type", "password")
												.formParam("username", "onlyfullstack")
												.formParam("password", "secret")
												.when()
												.post("/oath/token");
		
		
		System.out.println(response.getBody().asString());							   
		
		
		JsonPath Jpath = response.jsonPath();
		String token = Jpath.getString("access_token");
		
		System.out.println("The token is: " +token);
		
		response = RestAssured.given().baseUri("http://18.206.229.7:8088")
										.auth().oauth2(token)
										.when()
										.get("/students/2");
		
		System.out.println(response.getBody().asString());
	}			

}
