package restAPI;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonObject {
	
	@Test
	public void Test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", "Greg");
		jobj.put("salary", "50607080");
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(jobj.toString())
						.post("/create");
				
		
		String body = response.getBody().asString();
		System.out.println(body);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);
		
		Assert.assertTrue(body.contains("Greg"));
		
		JsonPath jpath = response.jsonPath();
		int id = jpath.get("id");
		
		System.out.println("id=" +id);
		
	}

}
