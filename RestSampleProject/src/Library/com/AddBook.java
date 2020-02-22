package Library.com;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.resources;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import files.ReusableMethods;
import files.payLoad;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class AddBook 
{

	@Test(dataProvider="BooksData")
	public void addBookTest(String aisle, String bookid ) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		Response resp = given().
		headers("Content-Type","application/json").
		body(payLoad.addBook(aisle, bookid)).
		when().
		post("/Library/Addbook.php").
		then().
		assertThat().statusCode(200).extract().response();
		
		JsonPath jp = ReusableMethods.rawToJson(resp);
		String id = jp.get("ID");
		System.out.println(id);
	
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"test1","100"},{"test2","101"},{"test3","102"}};

}}
