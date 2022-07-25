package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
	
		@Test
		public void Test1() {
			
			RestAssured.baseURI = "http://localhost:3000/employees";
			RequestSpecification request = RestAssured.given();
			
			Response response =	request.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body("  {\r\n"
								+ "        \"name\": \"Rob\",\r\n"
								+ "        \"salary\": \"1000000000\"\r\n"
								+ "    }")
							.post("/create");
					
			
			String body = response.getBody().asString();
			System.out.println(body);
			
			int ResponseCode = response.getStatusCode();
			Assert.assertEquals(ResponseCode, 201);
			
			Assert.assertTrue(body.contains("Rob"));
			
			JsonPath jpath = response.jsonPath();
			int id = jpath.get("id");
			
			System.out.println("id=" +id);
			
		}
}
