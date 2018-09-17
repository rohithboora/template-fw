package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AccDriver implements WebDriver {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static final Map<String, String> temporaryValues = new HashMap<String, String>();
	private static final LinkedHashMap<String, String> temporaryValuesSavedOrder = new LinkedHashMap<String, String>();
	private static Logger logger = Logger.getLogger(AccDriver.class);
	private static BrowserMobProxy proxy = new BrowserMobProxyServer();
	private static String UserDir = System.getProperty("user.dir");
	private static Platform currentPlatform = Platform.getCurrent();
	public static final Map<String, String> SAUCELAB_CAPABILITY = new HashMap<String, String>() {

		private static final long serialVersionUID = -7772238353308319613L;
		{
			put("Windows", "Windows 10");
			put("Linux", "Linux");
			put("Mac", "OS X 10.11");
			put("internet explorer", "11");
			put("chrome", "48.0");
			put("firefox", "35.0");
			put("safari", "10.0");
			put("edge", "14.14393");
		}
	};

public AccDriver() {
		
	}
/*------------------------Initialise Driver-------------------------------*/
	public static void driverInit()
	{
		System.out.println(UserDir);
/*..........................................................................................*/		
		System.out.println(currentPlatform);
/*..........................................................................................*/		
		String firefoxFlag= ReadProperty.loadPropertyValue("SeleniumFirefox");
		String ChromeFlag= ReadProperty.loadPropertyValue("SeleniumChrome");
		String IEFlag= ReadProperty.loadPropertyValue("SeleniumIE");
		String SafariFlag= ReadProperty.loadPropertyValue("SeleniumSafari");
		String AppiumFlag= ReadProperty.loadPropertyValue("AppiumDriver");
		String SauceLabsFlag= ReadProperty.loadPropertyValue("SauceLabsFlag");
/*..........................................................................................*/
		System.out.println("firefoxFlag = [" + firefoxFlag + "]");
		System.out.println("ChromeFlag = [" + ChromeFlag + "]");
		System.out.println("IEFlag = [" + IEFlag + "]");
		System.out.println("SafariFlag = [" + SafariFlag + "]");
		System.out.println("AppiumFlag = [" + AppiumFlag + "]");
		System.out.println("SauceLabsFlag = [" + SauceLabsFlag + "]");
/*..........................................................................................*/
		String AppiumVersion = ReadProperty.loadPropertyValue("AppiumVersion");
		String OS = ReadProperty.loadPropertyValue("OS");
		String MobilePlatform = ReadProperty.loadPropertyValue("MobilePlatform");
		String PlatformVersion = ReadProperty.loadPropertyValue("PlatformVersion");
		String DeviceName = ReadProperty.loadPropertyValue("DeviceName");
		String Browser = ReadProperty.loadPropertyValue("Browser");
		String AppiumServerIP = ReadProperty.loadPropertyValue("AppiumServerIP");
		String AppiumServerPort = ReadProperty.loadPropertyValue("AppiumServerPort");
/*..........................................................................................*/
		String SauceTestName = ReadProperty.loadPropertyValue("SauceTestName");
		//String SauceVisibility = ReadProperty.loadPropertyValue("SauceVisibility");
		String SauceUSERNAME = ReadProperty.loadPropertyValue("SauceUSERNAME");
		String SauceACCESSKEY = ReadProperty.loadPropertyValue("SauceACCESSKEY");
		String SaucePort = ReadProperty.loadPropertyValue("SaucePort");
		String SauceBrowser = ReadProperty.loadPropertyValue("SauceBrowser");
		String SaucePlatform = ReadProperty.loadPropertyValue("SaucePlatform");
		String SauceBrowserVersion = ReadProperty.loadPropertyValue("SauceBrowserVersion");
		String SauceTunnelIdentifier = ReadProperty.loadPropertyValue("SauceTunnelIdentifier");
		System.setProperty("webdriver.gecko.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"geckodriver.exe");
/*..........................................................................................*/
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
/*....................Select Log4j depending on Platform........................................................*/
		
		PropertyConfigurator.configure(UserDir + File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"log4j.properties");
		
		logger.info("Test execution log");
		
/*.....................Select the Browser depending on Platform......................................................*/	
		
		if(firefoxFlag.equals("Yes"))
		{
			if(currentPlatform.VISTA != null || currentPlatform.toString().equals("MAC") || currentPlatform.toString().equals("LINUX"))
			{
				DesiredCapabilities firefoxcapabilities = DesiredCapabilities.firefox();
				firefoxcapabilities.setCapability("marionette", true);
				driver = new FirefoxDriver(firefoxcapabilities);
				driver.manage().window().maximize();		
			} else 
				if (currentPlatform.XP != null) {
				try {
					System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
				} catch (Exception e) 
				{
					System.out.println("Firefox path not found: C:\\Program Files\\Mozilla Firefox\\firefox.exe; Trying another path");
					e.printStackTrace();
					try {
						System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\Mozilla\\firefox.exe");
						driver = new FirefoxDriver();
						driver.manage().window().maximize();
					} catch (Exception e1) {
						System.out.println("Firefox path not found: C:\\Program Files\\Mozilla Firefox\\Mozilla\\firefox.exe; catch throw");
						e1.printStackTrace();
					}
				}
			}
		}
		else if(ChromeFlag.equals("Yes"))
		{
			capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
			System.setProperty("webdriver.chrome.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"chromedriver.exe");
		    driver = new ChromeDriver(capabilities);
		    driver.manage().window().maximize();
		    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		    proxy.newHar(driver.getTitle()+"Null");
		    
		}
		else if(IEFlag.equals("Yes"))
		{
			System.setProperty("webdriver.ie.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"IEDriverServer.exe");
			DesiredCapabilities IEcapabilities = DesiredCapabilities.internetExplorer();
	        //IEcapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
	        //IEcapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
	        //IEcapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
	        IEcapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
	        IEcapabilities.setCapability("ignoreProtectedModeSettings", true);
	        //IEcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        IEcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
	        IEcapabilities.setJavascriptEnabled(true);
	        //IEcapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	        IEcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
	        IEcapabilities.setCapability("nativeEvents", false);    
	        //IEcapabilities.setCapability("unexpectedAlertBehaviour", "accept");
	        IEcapabilities.setCapability("disable-popup-blocking", true);
	        IEcapabilities.setCapability("enablePersistentHover", true);
	        IEcapabilities.setCapability("requireWindowFocus", true);
	        //IEcapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);
	        IEcapabilities.setJavascriptEnabled(true);
	        //IEcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        //IEcapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");

			driver = new InternetExplorerDriver(IEcapabilities);
			driver.manage().window().maximize();
			
		}
		else if(SafariFlag.equals("Yes"))
		{
			driver = new SafariDriver();
		}
/*.....................Appium Settings for the browser based Tests.......................................*/		
		
		else if(AppiumFlag.equals("Yes")){
			logger.info("Test execution log");
			System.out.println("Appium Veriosn ="+ AppiumVersion);
			System.out.println("Mobile Platform ="+ MobilePlatform); 
		    System.out.println("Platform Version ="+ PlatformVersion);
			System.out.println("Device Name =" + DeviceName);
			System.out.println("Browser Name ="+ Browser);
			System.out.println("Appium Server IP ="+ AppiumServerIP);
			System.out.println("Appium Server Port ="+ AppiumServerPort);
/*..........................................................................................*/			
			capabilities.setCapability("appiumVersion", AppiumVersion);
			capabilities.setCapability("OS", OS);
			capabilities.setCapability("platformName",MobilePlatform);
			capabilities.setCapability("PlatformVersion", PlatformVersion);
			capabilities.setCapability("deviceName", DeviceName);
			capabilities.setCapability("browserName", Browser);
			
			try {
				driver = new DriverAppium(new URL("http://"+AppiumServerIP+":"+AppiumServerPort+"/wd/hub"),capabilities);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(SauceLabsFlag.trim().equals("Yes")){
			
			logger.info("Test execution log SauceLabs");
			System.out.println("OS Platform ="+ SaucePlatform);
			System.out.println("Browser Name ="+ SauceBrowser);
			System.out.println("Browser Version ="+ SauceBrowserVersion);
/*..........................................................................................*/
			capabilities.setCapability("name", SauceTestName);
			//capabilities.setCapability("private", SauceVisibility);
			capabilities.setCapability("platform", SAUCELAB_CAPABILITY.get(SaucePlatform));
			capabilities.setCapability("browserName", SauceBrowser);
			capabilities.setCapability("version", SAUCELAB_CAPABILITY.get(SauceBrowser));
			capabilities.setCapability("tunnel-identifier", SauceTunnelIdentifier);
			String SauceURl = "http://" + SauceUSERNAME + ":" + SauceACCESSKEY + "@localhost:"+SaucePort+"/wd/hub";
/*..........................................................................................*/
			try {
				driver = new RemoteWebDriver(new URL(SauceURl), capabilities);
				System.out.println("Running tests on SaucePort ="+ SaucePort);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Please Set the Right Properties in Config.properties file");
		}
		
	}
/*....................................................................................................*/
	
	public static void startBrowserMobProxy()
	{
		String ChromeFlag= ReadProperty.loadPropertyValue("SeleniumChrome");
		
		if(ChromeFlag.equals("Yes"))
		{
		 proxy.start(0);
		 int port = proxy.getPort();
		 System.out.println(port+ "= Port Started");
		}
		else {
			System.out.println("BMP port terminated: No Network Logs are available at the moment as the tests are not getting run on chrome");
		}
	}
	

	public static void captureRequestResponselogs(String ScenarioTitle)
	{
		String JsonFilePath = UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator +ScenarioTitle+".json";
		Har har = proxy.getHar();
		
		File f = new File(JsonFilePath);
		try {
			har.writeTo(f);
		 if (f.exists()){
	            InputStream inputstream = new FileInputStream(JsonFilePath);
	            String jsonTxt = IOUtils.toString(inputstream);  
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    		JsonParser jp = new JsonParser();
	    		JsonElement je = jp.parse(jsonTxt);
	    		String prettyJson = gson.toJson(je);
	            
	    		FileWriter file = new FileWriter(JsonFilePath);
	    		try{
	    			file.write(prettyJson);
	    			System.out.println("Successfully copied JSON Object to file");
	    			//System.out.println("\nJSON Object:" + prettyJson);
	    		} finally {
	    			file.flush();
	    			file.close();
	    		}   
		 }
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("shutting down port");
	    proxy.stop();
	}
	
	/**
	 * 
	 * Driver instance on wait for Elements - to make the element load fully on page load
	 * 
	 */
	public void waitForElementPresent(By by) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForElementPresent(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForTextPresentInElement(WebElement element, String text) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void waitForInvisibilityOfElement(By by) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	/**
	 * 
	 * Is element Preset (True or False) Validation
	 * 
	 */
	public boolean isElementPresent(By by) {
		if(driver.findElements(by).size()>0){
			driver.findElement(by);
			if(driver.findElement(by).isDisplayed()){
				if(driver.findElement(by).isEnabled()){
					return true;
				}
			}
		}
		return false;	
	}
	
	public boolean isElementPresent(WebElement element) {
		try {
			element.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	/**
	 * 
	 * Temporary properties will allow data to be passed between steps or even scenarios
	 * 
	 * @param key - Identifier for the value being added
	 * @param value - value you wish to store
	 * 
	 * 
	 */
	public void addTemporaryProperty(String key, String value) {
		temporaryValues.put(key, value);
	}
	
	public void addTemporaryPropertySavingOrder(String key, String value) {
		temporaryValuesSavedOrder.put(key, value);
	}

	/**
	 * 
	 * Temporary properties will allow data to be passed between steps or even scenarios
	 * 
	 * @param key - Identifier for the value you wish to be returned
	 * @return String holding the data matching the key
	 * 
	 * 
	 */
	public String getTemporaryProperty(String key) {
		return temporaryValues.get(key);
	}
	
	public String getTemporaryPropertySavedOrder(String key) {
		return temporaryValuesSavedOrder.get(key);
	}
	
	public Set<String> getTemporaryPropertyKey() {
		return temporaryValues.keySet();
	}
	
	public int getTemporaryPropertySize() {
		return temporaryValues.size();
	}
	
	public Collection<String> getTemporaryPropertyValues() {
		return temporaryValues.values();
	}
	
	public int compareTemporaryPropertyItemsWithText(String module) {
		int count = 0;
		for(String value : temporaryValues.values()) {
		    if(driver.findElement(By.cssSelector(module)).getText().contains(value)) {
		        count++;
		    }
		}
		return count;
	}
	
	public void presenceOfExpectedAmountOfTemporaryPropertyItemsInText(String module, int expectedAmount) {
		if(temporaryValuesSavedOrder.size()<expectedAmount) {
			Assert.fail("Wrong definition. Expected amount is " + expectedAmount + ", but amount of items added previously is " + temporaryValuesSavedOrder.size());	
		}
		int count = 1;
		for(String value : temporaryValuesSavedOrder.values()) {
			if(count<=expectedAmount) {
				Assert.assertTrue(driver.findElement(By.cssSelector(module)).getText().contains(value));
				count++;
			} else if(count>expectedAmount) {
				Assert.assertFalse(driver.findElement(By.cssSelector(module)).getText().contains(value));	
			} 			
		}
	}
	public void clearTemporaryProperty() {
		temporaryValues.clear();
	}

	public void clearTemporaryPropertySavedOrder() {
		temporaryValuesSavedOrder.clear();
	}
	/**
	 * 
	 * Driver Instances from Selenium driver.(....)
	 * 
	 */
	public void close() {
		driver.close();
	}

	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	public void get(String arg0) {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.get(arg0);
	}

	public String getCurrentUrl() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		return driver.getPageSource();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}
	
	public Options manage() {
		return driver.manage();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}
	
	public void clickElement(String Selector ,String Locator){
		
		if(Locator.equals("id"))
		{
			driver.findElement(By.id(Selector)).click();
		}
		if(Locator.equals("xpath"))
		{
			driver.findElement(By.xpath(Selector)).click();
			
		}
		if(Locator.equals("class"))
		{
			driver.findElement(By.className(Selector)).click();
		}
		if(Locator.equals("linktext"))
		{
			driver.findElement(By.linkText(Selector)).click();
		}
		if(Locator.equals("css"))
		{
			driver.findElement(By.cssSelector(Selector)).click();
		}
	}
	
	public void enterText(String WebElement ,String Selector , String InputValue)
	{
		if(Selector.equals("id"))
		{
			driver.findElement(By.id(WebElement)).sendKeys(InputValue);
		}
		if(Selector.equals("xpath"))
		{
			driver.findElement(By.xpath(WebElement)).sendKeys(InputValue);
		}
		if(Selector.equals("class"))
		{
			driver.findElement(By.className(WebElement)).sendKeys(InputValue);
		}
		if(Selector.equals("linktext"))
		{
			driver.findElement(By.linkText(WebElement)).sendKeys(InputValue);
		}
		if(Selector.equals("css"))
		{
			driver.findElement(By.cssSelector(WebElement)).sendKeys(InputValue);
		}
	}
	/**
	 * 
	 * Verify initial state of the Checkbox
	 * 
	 */
	public void checkboxAction(WebElement checkbox, String action) {
		if (action.equalsIgnoreCase("check") || action.equalsIgnoreCase("enable")) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is checked");
			} else {
				Assert.fail("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is already checked");
			}
		} else if(action.equalsIgnoreCase("uncheck") || action.equalsIgnoreCase("disable")) {
			if(checkbox.isSelected()) {
				checkbox.click();
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is unchecked");
			} else {
				Assert.fail("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is already unchecked");
			}
		} else {
			Assert.fail("No action with checkbox " + "\"" + checkbox.getAttribute("id") + "\". Following action is wrongly defined: "  + "\"" + action + "\"");
		}
	}
	
	public void checkboxActionIgnoreInitialState(WebElement checkbox, String action) {
		if (action.equalsIgnoreCase("check") || action.equalsIgnoreCase("enable")) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is checked");
			} else {
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is already checked");
			}
		} else if(action.equalsIgnoreCase("uncheck") || action.equalsIgnoreCase("disable")) {
			if(checkbox.isSelected()) {
				checkbox.click();
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is unchecked");
			} else {
				logger.info("Checkbox " + "\"" + checkbox.getAttribute("id") + "\"" + " is already unchecked");
			}
		} else {
			Assert.fail("No action with checkbox " + "\"" + checkbox.getAttribute("id") + "\". Following action is wrongly defined: "  + "\"" + action + "\"");
		}
	}
	
	//Enable Checkbox
	public static void enableCheckbox(WebElement checkbox) {		
		if(!checkbox.isSelected()) {
			checkbox.click();
			logger.info("Action performed: " + checkbox.getAttribute("id") + " checkbox is checked");
		} else {
			logger.info("Action is not performed: " + checkbox.getAttribute("id") + " checkbox is already checked");
		}
	}
	
	//Disable Checkbox
	public static void disableCheckbox(WebElement checkbox) {
		if(checkbox.isSelected()) {
			checkbox.click();
			logger.info("Action performed: " + checkbox.getAttribute("id") + " checkbox is unchecked");
		} else {
			logger.info("Action is not performed: " + checkbox.getAttribute("id") + " checkbox is already unchecked");
		}
	}

	//TODO: Write method that will highlight the element passed in yellow - good for debugging
			
	//TODO: bring across tearDown and SetUp methods from old framework

	/**
	 * 
	 * Validate content from an element 
	 * 
	 */
	public void validateContent(WebElement Selector, String ExpectedValue) throws IOException  {
		
		if(Selector.getText().contains(ExpectedValue)){
			System.out.println("Content Matched = " +ExpectedValue+" = "+ Selector);
		}else {
			System.out.println("Content Doesn't Match, it should be = "+ExpectedValue+" = "+ Selector);
		}	
	}
	/**
	 * 
	 * Action Commands to Hover over elements
	 * 
	 */
	public void hoverOverElement(WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.build().perform();	
	}
	
	public void slideElement(WebElement element,int x, int y){
		Actions action = new Actions(driver);
		action.dragAndDropBy(element, x, y);
		action.build().perform();	
	}
	
	/**
	 * 
	 * Select values from a Dropdown
	 * 
	 */
	public boolean selectFirstOption(WebElement Element, String stringvalue)
	{
		Select dropbox = new Select(Element);
		return dropbox.getFirstSelectedOption().getText().contains(stringvalue);
	}
	
	public void SelectValue(String value,WebElement Element)
	{
		Select select = new Select(Element);
		select.selectByValue(value);
	}
	/**
	 * 
	 * Load URL/Data from a property file
	 * 
	 */
	public void loadURL(String Url)
	{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.get(ReadProperty.loadPropertyValue(Url));
	}
	
	/**
	 * 
	 * Load Data from a xls file
	 * 
	 */
	public String getCsvValue(String filename, int worksheetnumber, int rowvalue, int columnvalue) throws Throwable, IOException
	{
		String Path = UserDir+ File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"TestData"+ File.separator +filename+".xls";
		File inputWorkbook = new File(Path);
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sh= w.getSheet(worksheetnumber);
		return sh.getCell(rowvalue,columnvalue).getContents();
	}
	/**
	 * 
	 * Method to take screenshots 
	 * 
	 */
	
	//Method to take screenshot in different folders with different page names
	public void methodtoscreenshotforUserStorie(String page, String Folder) {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(UserDir+ File.separator +"Reports"+ File.separator +"Wave2Screenshots"+ File.separator + Folder + File.separator + page + " screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			if(currentPlatform.toString().equals("VISTA") || currentPlatform.toString().equals("XP"))
			{
				FileUtils.copyFile(scrFile, new File(UserDir+ "\\Reports\\Wave2Screenshots\\"+ Folder +"\\"+ page + " screenshot.png"));
			}
			else if(currentPlatform.toString().equals("MAC") || currentPlatform.toString().equals("LINUX") )
			{
				FileUtils.copyFile(scrFile, new File(UserDir+ "/Reports/Wave2Screenshots/"+ Folder +"/"+ page + " screenshot.png"));
			}else
			{
				System.out.println("Error in OS");
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	//Method to take screenshot and record time in one folder with different page names	
	public void methodtoscreenshotOverAll(String pagename) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
		Calendar cal = Calendar.getInstance();
		String Time = dateFormat.format(cal.getTime());
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(UserDir+ File.separator +"Test Evidence"+ File.separator +"Regular Screenshots"+ File.separator +"AllPages"+ File.separator + pagename +"--"+ Time + "--screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to take screenshot and record time in one folder with different page names	
		public void methodtoFailedScreenshot(String ScenarioTitle) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
			Calendar cal = Calendar.getInstance();
			String Time = dateFormat.format(cal.getTime());
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator + ScenarioTitle +"--"+ Time + "--screenshot.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/**
	 * 
	 * Method to execute JavaScript 
	 * 
	 */
	public void javascriptaction(String arg, WebElement Element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(arg , Element);
	}
	
	public String javascriptaction(String arg)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String output = (String) js.executeScript(arg);
		return output;
	}
	
	public void javascriptExecutor(String arg)
    {
           JavascriptExecutor js = (JavascriptExecutor)driver;
           js.executeScript(arg);
    }
	
	public void scrollUp() {
		((JavascriptExecutor) driver).executeScript("scroll(250,0);");
	}
	
	public void scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * 
	 * Method to write into a file
	 * 
	 */
	
	//write into Json file
	public void writeJsonToFile(String browserLog, String Pagename , String AppFolder, String foldername) throws IOException
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(browserLog);
		String prettyJson = gson.toJson(je);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		new File(UserDir+ File.separator +"Reports"+ File.separator +"Wave2Screenshots"+ File.separator + foldername + File.separator + AppFolder).mkdir();
		FileUtils.copyFile(scrFile, new File(UserDir+ File.separator + "Reports"+ File.separator +"Wave2Screenshots"+ File.separator + foldername + File.separator + AppFolder + File.separator + Pagename + ".png"));
		FileWriter file = new FileWriter(UserDir+ File.separator +"Reports"+ File.separator +"Wave2Screenshots"+ File.separator + foldername + File.separator + AppFolder + File.separator + Pagename +".json");
		
		try{
			file.write(prettyJson);
			System.out.println("Successfully copied JSON Object to file");
			System.out.println("\nJSON Object:" + prettyJson);
		} finally {
			file.flush();
			file.close();
		}
		
		/*if(currentPlatform.toString().equals("VISTA") || currentPlatform.toString().equals("XP"))
		{
		new File(UserDir+ "\\Reports\\Wave2Screenshots\\"+ foldername +"\\"+AppFolder).mkdir();
		FileUtils.copyFile(scrFile, new File(UserDir+ "\\Reports\\Wave2Screenshots\\"+ foldername +"\\"+ AppFolder +"\\"+ Pagename + ".png"));
		FileWriter file = new FileWriter(UserDir+ "\\Reports\\Wave2Screenshots\\"+ foldername +"\\"+ AppFolder +"\\"+ Pagename +".json");
		
		try{
			file.write(prettyJson);
			System.out.println("Successfully copied JSON Object to file");
			System.out.println("\nJSON Object:" + prettyJson);
		} finally {
			file.flush();
			file.close();
		}
		}else 
			if(currentPlatform.toString().equals("MAC") || currentPlatform.toString().equals("LINUX") )
		{
			new File(UserDir+ "/Reports/Wave2Screenshots/"+ foldername +"/"+AppFolder).mkdir();
			FileUtils.copyFile(scrFile, new File(UserDir+ "/Reports/Wave2Screenshots/"+ foldername +"/"+ AppFolder +"/"+ Pagename + ".png"));
			FileWriter file = new FileWriter(UserDir+ "/Reports/Wave2Screenshots/"+ foldername +"/"+ AppFolder +"/"+ Pagename +".json");
			
			try{
				file.write(prettyJson);
				System.out.println("Successfully copied JSON Object to file");
				System.out.println("\nJSON Object:" + prettyJson);
			} finally {
				file.flush();
				file.close();
			}
		}*/
		
		
	}
	
	//write into text file
	public void writeToTextFile(String FolderName, WebElement Selector, String TextfileName ) throws IOException
	{
		
		FileWriter file = new FileWriter(UserDir+ File.separator + "Reports"+ File.separator + TextfileName +".txt", true);
		String Write = FolderName + "-" + Selector.getText();
		String lineSeparator = System.getProperty("line.separator");
		String[] output = Write.split("\n");
		try{
			for (int i = 0; i <= output.length-1; i++) {
				file.write("\r\n");
				file.write(output[i]);
				file.write(lineSeparator);
			  }
			System.out.println("Successfully copied");
			System.out.println(Selector.getText());
		} finally {
			file.flush();
			file.close();
		}
		
		/*if(currentPlatform.toString().equals("VISTA") || currentPlatform.toString().equals("XP"))
		{
		FileWriter file = new FileWriter(UserDir+ "\\Reports\\"+ TextfileName +".txt", true);
		String Write = FolderName + "-" + Selector.getText();
		String lineSeparator = System.getProperty("line.separator");
		String[] output = Write.split("\n");
		try{
			for (int i = 0; i <= output.length-1; i++) {
				file.write("\r\n");
				file.write(output[i]);
				file.write(lineSeparator);
			  }
			System.out.println("Successfully copied");
			System.out.println(Selector.getText());
		} finally {
			file.flush();
			file.close();
		}
		}else 
			if(currentPlatform.toString().equals("MAC") || currentPlatform.toString().equals("LINUX") )
		{
			FileWriter file = new FileWriter(UserDir+ "/Reports/"+ TextfileName +".txt");
			String Write = FolderName + "-" + Selector.getText();
			String lineSeparator = System.getProperty("line.separator");
			String[] output = Write.split("\n");
			try{
				for (int i = 0; i <= output.length-1; i++) {
					file.write("\r\n");
					file.write(output[i]);
					file.write(lineSeparator);
				  }
				System.out.println("Successfully copied");
				System.out.println(Selector.getText());
			} finally {
				file.flush();
				file.close();
			}
		}*/
	}
	public void validateElementsList(String xpath_locator, String attribute, String value1, String value2)
    {
           ArrayList<WebElement> dialogElem = new ArrayList<WebElement>(driver.findElements(By.xpath(xpath_locator)));
           int a=dialogElem.size();
           System.out.println("No of elements found in Arraylist is "+a);
           Iterator<WebElement> iter = dialogElem.iterator();
           
           while(iter.hasNext())
           {
                  WebElement elem= iter.next();
                  if(((elem.getAttribute(attribute)).equalsIgnoreCase(value1))||((elem.getAttribute(attribute)).equalsIgnoreCase(value2)))
                  {
                        System.out.println("Expected buttons are available");
                  }
                  else 
                        {
                               System.out.println(elem.getAttribute(attribute));
                               System.out.println("Unexpected buttons are available");
                               driver.quit();
                        }
           }
    }

	public void writeToTextFile1(List<String> content, String TextfileName ) throws IOException
	{
		PrintWriter pw = null;
	    //String outputFolder = ".";
	    File output = null;
	    try {
	        File dir = new File(UserDir + File.separator +"Reports" + File.separator + "HTML_Sources");
	        if (!dir.exists()) {
	            boolean success = dir.mkdirs();
	            if (success == false) {
	                try {
	                    throw new Exception(dir + " could not be created");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        output = new File(UserDir + File.separator +"Reports" + File.separator + "HTML_Sources"+ File.separator +TextfileName + ".html");
	        if (!output.exists()) {
	            try {
	                output.createNewFile();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
	        pw = new PrintWriter(new FileWriter(output, true));
	        for (String line : content) {
	            pw.print(line);
	            pw.print("\n");
	        }
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } finally {
	        pw.close();
	    }
	}

	public void setTimeout(Integer t)
    {
		driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(t, TimeUnit.SECONDS);
    }
}

