package TestScript.com;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetSample 
{
	
	@Test
	public void Test1() {
	
	
		System.out.println("Started");
		RestAssured.baseURI = "https://api.github.com";
		given().
		param("q", "language:python").
		param("sort","star").
		when().get("/search/repositories").then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("items[0].id",equalTo(83222441)).and().
		body("items[0].private", equalTo(false)).and().
		header("Server", "GitHub.com")
		
		;
	
		System.out.println("Test Done ");
	}

}
