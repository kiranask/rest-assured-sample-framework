package Library.com;

import static io.restassured.RestAssured.given;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;


import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson 

{
	@Test()
	public void addBookTest()  throws IOException 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		Response resp = given().
		headers("Content-Type","application/json").
		body(generateStringFromResource("/Users/kkatte/Documents/release_2020/rest-assured/rest-assured-sample-framework/RestSampleProject/src/files/testLoad.json")).
		when().
		post("/Library/Addbook.php").
		then().
		assertThat().statusCode(200).extract().response();
		
		JsonPath jp = ReusableMethods.rawToJson(resp);
		String id = jp.get("ID");
		System.out.println(id);
	
	}
	
	public static String generateStringFromResource(String path) throws IOException 
	{
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}

}
