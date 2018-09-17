package Utilities;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

@SuppressWarnings("rawtypes")
public class DriverAppium extends AppiumDriver {

	public DriverAppium(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
		// TODO Auto-generated constructor stub
	}

	public void rotate(DeviceRotation rotation) {
		// TODO Auto-generated method stub
		
	}

	public DeviceRotation rotation() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement scrollTo(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement scrollToExact(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
