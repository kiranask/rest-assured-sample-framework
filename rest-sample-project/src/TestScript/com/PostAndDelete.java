package TestScript.com;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.payLoad;
import files.resources;
public class PostAndDelete {
	
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis = new FileInputStream("/Users/kkatte/Documents/release_2020/rest-assured/rest-assured-sample-framework/rest-sample-project/src/Files/env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}

	@Test
	public void AddandDeletePlace()
	{
		
		//Task 1- Grab the response
		RestAssured.baseURI= prop.getProperty("LOCALHOST");
		Response res = given().
		
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.googleMapJsonPayLoad()).
		when().
		post(resources.placeGoogleMap()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		// Task 2- Grab the Place ID from response
		
		String responseString = res.asString();
		System.out.println(responseString);
		
		
		JsonPath js= new JsonPath(responseString);
		String placeid=js.get("place_id");
		System.out.println("Place ID is "+ placeid);
		
		
		//Task 3 place this place id in the Delete request
		given().
		queryParam("key",prop.getProperty("KEY")).
		
		body("{"+
  "\"place_id\": \""+placeid+"\""+
"}").
		when().
		post(resources.deletePlaceGoogleMap()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
		
	}
}
