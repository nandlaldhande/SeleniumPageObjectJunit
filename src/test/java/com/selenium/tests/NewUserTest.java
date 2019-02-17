package com.selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import io.restassured.response.Response;
import com.selenium.driverutil.BaseTemplate;
import com.selenium.driverutil.RestCalls;
import com.selenium.pageobjects.AllUsers;
import com.selenium.pageobjects.NewUser;

/**
 * Created by Nandlal Dhande on 16/02/19.
 * Following test covers the basic validations for new user creation as mention in test PDF
 * Properties - Name,Email,Password
 *The following constraints are given
 * Name must be unique and is required for user creation
 * Email must be unique and is required for user creation
 * Password is required
 * Password and password repeat must be the same
 */
public class NewUserTest extends BaseTemplate {
	private WebDriver driver;
	NewUser newUser;
	AllUsers allUsers;
	RestCalls restCalls;

	@Before
	/* Setting up the browser to all available pageObjescts*/
	public void setUp() {
		driver = getBrowser();
		newUser = new NewUser(driver);
		allUsers = new AllUsers(driver);
		restCalls = new RestCalls();

	}

	/*Following are the actual tests for new user creation. */
	
	@Test
	public void Verify_user_is_able_to_delete_all_users_using_rest_call_Test() {
        Assert.assertEquals(200,restCalls.deleteAllExistingUsers().getStatusCode() );		
        Assert.assertEquals(restCalls.getAllAvailableUser().jsonPath().getList("$").size(), 0);

	}
	
	@Test
	public void Verify_user_us_able_to_add_valid_new_user_Test() {
				newUser.goToHome();
				restCalls.deleteAllExistingUsers();		
		newUser.createNewUser(propertyReader.readProperty("userName"), propertyReader.readProperty("email"),
				propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"));
		Assert.assertEquals("All User", allUsers.verifyThePageAllUsers());
	}

	@Test
	public void verify_userName_is_required() {	
		newUser.goToHome();
		Assert.assertTrue(newUser.verifytheErrorMessage("", propertyReader.readProperty("email_1"),
				propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"),
				propertyReader.readProperty("requiredErrorMessage")));
	}

	@Test
	public void verify_userName_is_unique() {
		newUser.goToHome();
		newUser.createNewUser(propertyReader.readProperty("userName"), propertyReader.readProperty("email"),
				propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"));
		newUser.goToHome();
		Assert.assertTrue(
				newUser.verifytheErrorMessage(propertyReader.readProperty("userName"), propertyReader.readProperty("email_1"),
						propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"),
						propertyReader.readProperty("uniqueErrorMessage")));
	}

	@Test
	public void verify_email_is_required() {
		newUser.goToHome();
		Assert.assertTrue(newUser.verifytheErrorMessage(propertyReader.readProperty("userName_01"), "",
				propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"),
				propertyReader.readProperty("requiredErrorMessage")));
	}

	@Test
	public void verify_email_is_unique() {
		newUser.goToHome();
		newUser.createNewUser(propertyReader.readProperty("userName"), propertyReader.readProperty("email"),
				propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"));
		newUser.goToHome();
		Assert.assertTrue(
				
				newUser.verifytheErrorMessage(propertyReader.readProperty("userName_01"), propertyReader.readProperty("email"),
						propertyReader.readProperty("password"), propertyReader.readProperty("confirmationPassword"),
						propertyReader.readProperty("uniqueErrorMessage")));
	}

	@Test
	public void verify_password_is_Required() {
		newUser.goToHome();
		Assert.assertTrue(newUser.verifytheErrorMessage(propertyReader.readProperty("userName_01"),
				propertyReader.readProperty("email_01"), "", propertyReader.readProperty("confirmationPassword"),
				propertyReader.readProperty("requiredErrorMessage")));
	}

	@Test
	public void verify_Confirmation_password_is_required() {
		newUser.goToHome();
		Assert.assertTrue(newUser.verifytheErrorMessage(propertyReader.readProperty("userName_01"),
				propertyReader.readProperty("email_01"), propertyReader.readProperty("password"), "",
				propertyReader.readProperty("requiredErrorMessage")));
	}

	@Test
	public void verify_password_and_confirmation_password_should_match() {
		newUser.goToHome();
		Assert.assertTrue(newUser.verifytheErrorMessage(propertyReader.readProperty("userName_01"),
				propertyReader.readProperty("email_01"), propertyReader.readProperty("password"),
				propertyReader.readProperty("confirmationPasswordMismatched"),
				propertyReader.readProperty("passwordMatchErrorMessage")));
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
