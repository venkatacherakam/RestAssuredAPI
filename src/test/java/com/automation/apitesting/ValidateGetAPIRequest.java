package com.automation.apitesting;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateGetAPIRequest {
	
	@Test()
	
	public static void ValidateGetRequest()
	{
		try
		
		{
		Response response =
			RestAssured 
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.get()
			.then()
				.assertThat()
				.statusCode(200)

			.extract()
				.response();
		
		Assert.assertTrue(response.getBody().asString().contains("bookingid"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
	}	

}
