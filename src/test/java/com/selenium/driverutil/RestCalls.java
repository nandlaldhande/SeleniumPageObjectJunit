package com.selenium.driverutil;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import io.restassured.response.Response;

public class RestCalls extends BaseTemplate {

	
	/*Rest call to delete all available users from DB */
	public Response deleteAllExistingUsers() {			
		return delete(propertyReader.readProperty("baseURLRest")+"/user/all");		
	}
	
	/*Rest call to get all available users from DB */
	public Response getAllAvailableUser() {
		return get(propertyReader.readProperty("baseURLRest")+"/user/all/json");
		}
}
	