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

public class DataDrivenSample1 
{
	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {
		
		FileInputStream fis = new FileInputStream("/Users/kkatte/Documents/release_2020/rest-assured/rest-assured-sample-framework/rest-sample-project/src/Files/env.properties");
		prop.load(fis);	
	}
	
	@Test
	public void testaddPlace() {
		
		RestAssured.baseURI = prop.getProperty("LOCALHOST");
		given().
			queryParam("key", prop.getProperty("KEY")).
			body(payLoad.googleMapJsonPayLoad()).
		when().
			post(resources.placeGoogleMap()).
		then().assertThat().statusCode(200);
	}
	

}
	
