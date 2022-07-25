package gitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	@Test
	public void test1() {
		
		RestAssured.baseURI = "https://api.github.com/users/cassidyamason1/repos";
		RequestSpecification request = RestAssured.given();
		Response response = request.accept(ContentType.JSON).get();
		
		String body = response.getBody().asString();
		System.out.println(body);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
	}
	@Test
	public void test2() throws IOException {
		
		RestAssured.baseURI = "https://api.github.com/user/repos";
		
		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.auth()
						.oauth2("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")
						.body(dataBytes)
						.post();
				
		
		String body = response.getBody().asString();
		System.out.println(body);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);

	}

}
