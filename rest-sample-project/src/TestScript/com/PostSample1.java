package TestScript.com;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.payLoad;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class PostSample1 
{
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException 
	{
		FileInputStream fis = new FileInputStream("/Users/kkatte/Documents/release_2020/rest-assured/rest-assured-sample-framework/rest-sample-project/src/Files/env.properties");
		prop.load(fis);	
	
	}
	
	@Test
	public void IPManTest() 
	{
//		RestAssured.baseURI = "https://ipman.analytics.edgekey.net";
		RestAssured.baseURI = prop.getProperty("HOST");
		given().
		queryParam("apikey", prop.getProperty("API_KEY")).
		body(payLoad.manPayLoad()).
		when().
		post(resources.placemanPostData()).
		then().
		assertThat().statusCode(200);
	
	}

}
