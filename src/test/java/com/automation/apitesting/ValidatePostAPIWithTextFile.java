package com.automation.apitesting;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.apitesting.utils.FileNameConstants;
import com.jayway.jsonpath.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*import io.restassured.path.json.JsonPath;*/
import net.minidev.json.JSONArray;


public class ValidatePostAPIWithTextFile {
	
	@Test
	public static void CreateBooking() throws IOException
	{
		String reqBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQ_BODY), "UTF-8");
		Response response =
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking")
				.body(reqBody)
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.log().all()
			.extract()
				.response();
		
		
		
		
		int bookingId = JsonPath.read(response.body().asString(),"$.bookingid");
		
	
		
		
		JSONArray jsonArray = JsonPath.read(response.body().asString(),"$.booking..firstname");
		
		String firstName = (String) jsonArray.get(0);
		
		Assert.assertEquals(firstName, "ashok");
		
					RestAssured
								.given()
									.contentType(ContentType.JSON)
									.baseUri("https://restful-booker.herokuapp.com/booking")
								.when()
									.get("/{bookingId}", bookingId)
								.then()
									.assertThat()
									.log().all()
									.statusCode(200);
		
		
		
		
			
	}
	
}
