package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	Response response;
	String BaseURI = "http://localhost:3000/employees";
	
	@Test
	public void test1() {
		
		response = GetAllMethod();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Sam", "2000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int EmpId = jpath.get("id");
		System.out.println("id=" +EmpId);
		
		response = PutMethod(EmpId, "Cheese", "503");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("name"), "Cheese");
		
		response = DeleteMethod(EmpId);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = GetSingleMethod(EmpId);
		Assert.assertEquals(response.getStatusCode(), 404);
		
		
		
		
	}

	public Response GetAllMethod() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		
		return response;
		
	}
	
	public Response PostMethod(String name, String salary) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", name);
		jobj.put("salary", salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(jobj.toString())
						.post("/create");
		
		return response;
		
	}
	
	public Response PutMethod(int EmpId, String name, String salary) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", name);
		jobj.put("salary", salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(jobj.toString())
						.put("/" + EmpId);
		
		return response;
		
	}
	
	public Response DeleteMethod(int EmpId) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();		
		Response response = request.delete("/" + EmpId);
		return response;
		
	}
	
	public Response GetSingleMethod(int EmpId) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();	
		Response response = request.get("/" + EmpId);
		return response;
		
	}
}
