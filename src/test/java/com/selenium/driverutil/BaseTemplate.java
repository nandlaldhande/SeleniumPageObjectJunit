package com.selenium.driverutil;

import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseTemplate {

	public static final PropertyReader propertyReader = new PropertyReader();

	/* Initiate HtmlUnit Driver */
	public static WebDriver getBrowser() {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		return new HtmlUnitDriver();
	}
	
	

}
