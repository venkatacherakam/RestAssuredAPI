package com.automation.apitesting;

import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.automation.apitesting.utils.BaseClass;
import com.jayway.jsonpath.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath ;
import net.minidev.json.JSONObject;

public class ValidatePostAPIRequest extends BaseClass{

	
	@Test
	public static void CreateBooking()
	{
		JSONObject booking = new JSONObject();
		JSONObject bookingDates = new JSONObject();
		
		booking.put("firstname", "Venkat");
		booking.put("lastname", "Cherakam");
		booking.put("totalprice", 2000);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "breakfast");
		booking.put("bookingdates", bookingDates);
		
		bookingDates.put("checkin", "2025-01-30");
		bookingDates.put("checkout", "2025-01-31");
		
		Response response =
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking")
				.body(booking.toString())
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.body("booking.firstname",Matchers.equalTo("Venkat"))
				.log().all()
			.extract()
				.response();
		
		String bookingId = response.path("bookingid").toString();
		JsonPath jsonPath = response.jsonPath();
		String total_price =  jsonPath.getString("totalprice");
		String check_in_date =  jsonPath.getString("bookingdates.checkin");
		
		System.out.println("The given booking ID : " + bookingId);
		System.out.println("The Total Price for booking: " + total_price);
		System.out.println("The check in date for given booking : " + check_in_date);
		
		
			
	}
	
}
