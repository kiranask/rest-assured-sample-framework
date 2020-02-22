package TestScript.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ListOutAllNames 
{
	@Test
	public void Test1() {
	
	
		System.out.println("Started");
		RestAssured.baseURI = "https://api.github.com";
		Response resp = given().
		param("q", "language:python").
		param("sort","star").
		when().get("/search/repositories").then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).log().body().
		extract().response();
		JsonPath jp =ReusableMethods.rawToJson(resp);
		int count =jp.get("items.size");
		System.out.println(count);
	
		for (int i = 0; i < count; i++) 
		{
			String name = jp.get("items[+" +i+"].name");
			System.out.println(name);
			
		}

		;
	
		System.out.println("Test Done ");
	}

}
