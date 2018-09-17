package browser_Start_Up;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import Utilities.AccDriver;
import Utilities.ReadProperty;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class InitialSetUp extends AccDriver {

	private static AccDriver driver;	
	
	public InitialSetUp(AccDriver driver) {
		InitialSetUp.driver = driver;
		driver = PageFactory.initElements(driver, AccDriver.class);
	}
	
@SuppressWarnings("static-access")
@Before
	public static void StartBrowser()
	{
		driver.startBrowserMobProxy();
		driver.driverInit();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	}
	
@After
	public static void StopBrowser(Scenario scenario)
	{
		String ScenarioTitle = scenario.getName().toString().trim();
		String ChromeFlag= ReadProperty.loadPropertyValue("SeleniumChrome");
		
		if (scenario.getStatus().equalsIgnoreCase("failed")) 
		{
			driver.methodtoFailedScreenshot(ScenarioTitle);
			if(ChromeFlag.equals("Yes"))
			{
			AccDriver.captureRequestResponselogs(ScenarioTitle);
			}else
			{
				System.out.println("Scenario Failed - Choose Chrome Browser to generate Network Logs");
			}
		}
		driver.quit();
	}


}


