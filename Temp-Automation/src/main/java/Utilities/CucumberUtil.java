package Utilities;

import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
//import cucumber.api.java.Before;

public class CucumberUtil {
	AccDriver driver;
	Scenario scenario;

	public CucumberUtil() {

	}
	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
	}
	
	public void screenshot() {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

}
