package com.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.driverutil.BaseTemplate;

/**
 * Created by Nandlal Dhande on 16/02/19.
 * Page Objects for page New user
 */
public class NewUser extends BaseTemplate {

	private WebDriver _driver = null;

	@FindBy(id = "name")
	private WebElement user_name_textbox;

	@FindBy(id = "user.name.error")
	private WebElement user_name_error;

	@FindBy(id = "email")
	private WebElement email_textbox;	

	@FindBy(id = "password")
	private WebElement password_textbox;

	@FindBy(id = "confirmationPassword")
	private WebElement confirmation_password_textbox;

	@FindBy(xpath = "//button[1]")
	private WebElement submit_buttont;

	public NewUser(WebDriver driver) {
		this._driver = driver;
		PageFactory.initElements(_driver, this);
	}

	public void goToHome() {
		_driver.get(propertyReader.readProperty("url"));
	}

	public void createNewUser(String name, String email, String password, String confirmationPassword) {

		user_name_textbox.sendKeys(name);
		email_textbox.sendKeys(email);
		password_textbox.sendKeys(password);
		confirmation_password_textbox.sendKeys(confirmationPassword);
		submit_buttont.click();
	}

/* return the whether given error message is getting displayed on page or not */
	public boolean verifytheErrorMessage(String name, String email, String password, String confirmationPassword,
			String errorMessage) {
		createNewUser(name, email, password, confirmationPassword);
		return _driver.getPageSource().contains(errorMessage);

	}
}
