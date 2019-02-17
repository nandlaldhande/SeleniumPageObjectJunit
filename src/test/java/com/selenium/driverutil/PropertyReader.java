package com.selenium.driverutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nandlal Dhande on 17/02/19.
 * Utility to provide the value from property file for given key
 */
public class PropertyReader {

	Properties properties;

	InputStream inputStream = null;

	public PropertyReader() {	
		properties = new Properties();
		loadProperties();
	}

	private void loadProperties() {
		try {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readProperty(String key) {
		return properties.getProperty(key);
	}
}
