package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void test1() {
		
		
		RestAssured.given()
					.baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get()
					.then()
					.log()
					.body()
					.statusCode(200);
		}

	@Test
	public void test2() {
		
		
		Response response = RestAssured.given()
										.baseUri("https://chercher.tech/sample/api/books.xml")
										.when()
										.get();
		
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		
		System.out.println("The first book is: " +books.get(0).toString());
		System.out.println("The second book is: " +books.get(1).toString());
		
		for (String b:books) {
			System.out.println(b.toString());
		}
		
		for (int i=0; i<books.size(); i++) {
			System.out.println(books.get(i).toString());
		}
		
		System.out.println("The language of The Nightingale is: " +books.get(0).getAttribute("lang"));
					
		NodeChildrenImpl price = response.then().extract().path("bookstore.book.price");
		
		System.out.println("The first price is: " +price.get(0).children().get("paperback"));
		System.out.println("The second price is: " +price.get(0).children().get("hardcover"));
		System.out.println("The third price is: " +price.get(1).toString());
		}
	}
