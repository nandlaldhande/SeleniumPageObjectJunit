package com.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nandlal Dhande on 16/02/19.
 * Page Objects for page All users
 */
public class AllUsers {

	private WebDriver _driver = null;

	@FindBy(xpath = "//h1[1]")
	private WebElement allUserPageHeading;

	public AllUsers(WebDriver driver) {
		this._driver = driver;
		PageFactory.initElements(_driver, this);
	}

	public String verifyThePageAllUsers() {
		return allUserPageHeading.getText();
	}
}
