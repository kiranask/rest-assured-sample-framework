import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class Basic 
{
	public static void main(String[] args) {
		System.out.println("Started");
		RestAssured.baseURI = "https://api.github.com";
		given().
		param("q", "language:python").
		param("sort","star").
		when().get("/search/repositories").then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("items[0].id",equalTo(83222441));
		
		
		System.out.println("Test Done ");
	}

}
