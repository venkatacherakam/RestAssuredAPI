package com.automation.apitesting.utils;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseClass {

	@BeforeTest()
	public void LogResponseOnFailure()
	{
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
