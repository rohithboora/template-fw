package page_steps;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import page_objects.AllLinks;
import page_objects.StartPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import Utilities.AccDriver;
import Utilities.ReadProperty;

public class StartPageSteps {

	private AccDriver driver;
	private StartPage startpage;
	private AllLinks allLinks;

	String AppiumFlag = ReadProperty.loadPropertyValue("AppiumDriver");
	String HtmlSourceFlag = ReadProperty.loadPropertyValue("HtmlSourceFlag");

	public StartPageSteps(AccDriver driver) {
		this.driver = driver;
		startpage = PageFactory.initElements(driver, StartPage.class);
		allLinks = PageFactory.initElements(driver,
				AllLinks.class);
	}

	@Given("^I am on the Agreement Page$")
	public void i_am_on_the_Agreement_Page() throws Throwable {
		driver.loadURL("BaseURL");
		Thread.sleep(5000);
		if (AppiumFlag.equals("Yes")) {
			driver.findElement(By.id("ignore_this_warning")).click();
			Thread.sleep(5000);
		}
	}
	
	@Given("^I want to open a Url$")
	public void i_want_to_open_a_Url() throws Throwable {
	    
		driver.loadURL("BaseUrl");
	}
	
	@When("^I search for text as \"([^\"]*)\"$")
	public void i_search_for_text_as(String text) throws Throwable {
	    
		startpage.enterSearchText(text);
	}
	
	@When("^I click on the Accenture twiter account$")
	public void i_click_on_the_Accenture_twiter_account() throws Throwable {
	    
		startpage.clickAccentureTwiterLink();
	}

	@Given("^I click the search button$")
	public void i_click_the_search_button() throws Throwable {
	    
		startpage.clickStartButton();
	}

	@Then("^I am navigated to the Before Getting Started Page$")
	public void i_am_navigated_to_the_Before_Getting_Started_Page()
			throws Throwable {
		
		driver.setTimeout(120);
	}

	@Given("^I capture the scenario \"(.*?)\" Screenshot for the Page \"(.*?)\"$")
	public void i_capture_the_scenario_Screenshot_for_the_Page(String arg1, String arg2) throws Throwable {
	    
		driver.methodtoscreenshotforUserStorie(arg2, arg1);
		System.out.println(arg2 + "Completed navigating to the next page");
	}
	
	@Given("^I click Book an Appointment Link and \"(.*?)\" Screenshot for the link \"(.*?)\"$")
	public void i_click_Book_an_Appointment_Link_and_Screenshot_for_the_link(String arg1, String arg2) throws Throwable {
	    
		String ParentTab = driver.getWindowHandle();

		System.out.println("Current handle is: "+ParentTab);
		   
		Thread.sleep(5000);
		allLinks.clickBookAppointmentLink();
		ArrayList<String> ChildTab = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(10000);
		
		ChildTab.remove(ParentTab);
		
		driver.switchTo().window(ChildTab.get(0));
		
		driver.methodtoscreenshotforUserStorie(arg2, arg1);
		   
		driver.close();
		   
		driver.switchTo().window(ParentTab);
	}
	
	@Then("^I capture the \"(.*?)\" CMS keys in app \"(.*?)\"$")
	public void i_capture_the_CMS_keys_in_app(String PageName, String AppName) throws Throwable {
		
		String browserLog = driver.javascriptaction("window.baukeys();var cms = document.querySelectorAll('cms');var pairs = {};for(var i=0; i<cms.length;i++) {var key = cms[i].className;var content = cms[i].innerHTML;pairs[key] = content;}return JSON.stringify(pairs);");
		System.out.println(browserLog);
		driver.writeJsonToFile(browserLog, PageName, AppName , "CMSFolder");
	    
	}

	@When("^I navigate to the \"(.*?)\" settings URL in a new Tab and navigate back to the previous page$")
	public void i_navigate_to_the_settings_URL_in_a_new_Tab_and_navigate_back_to_the_previous_page(String arg1) throws Throwable {    
		   String ParentTab = driver.getWindowHandle();
		   String ChildTab = driver.getWindowHandle();
		   System.out.println("Current handle is: "+ParentTab);
		   Thread.sleep(3000);
		   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		   driver.loadURL("NegativeProductSelector");
		   driver.switchTo().window(ChildTab);
		   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.SHIFT,Keys.TAB);
		   driver.switchTo().window(ParentTab);
	}
	
	@And("^I wait for 15 min to get Timeout screen$")
	public void i_wait_for_15_min_to_get_Timeout_screen() throws Throwable {
		driver.javascriptExecutor("__MTP__.setSessionPopupTimers({popup: 0})");
	}
	
    @And("^I see no close button on Timeout screen$")
	public void i_see_no_close_button_on_Timeout_screen() throws Throwable {
    	
    	driver.validateElementsList(startpage.getListOfButton(), "id", "txt0", "txt1");
    }
    
    
    @Then("^I see title band as long as title \"(.*?)\" on \"(.*?)\"$")
	public void i_see_title_band_as_long_as_title(String expectedTitle, String page) throws Throwable {
    	
    	String actualTitle= startpage.getTitle().getText().replaceAll("\n", " "); 
		Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle));   
    }
 /* Method to Implement the html dump load for a feature file 
  * this is used to test accessibility using Pally*/
    
    @Given("^I take html dump for the page load \"(.*?)\"$")
    public void i_take_html_dump_for_the_page_load(String arg1) throws Throwable {
        
    	if(HtmlSourceFlag.equals("Yes"))
    	{
    		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    		List<String> pageSource = new ArrayList<String>(Arrays.asList(driver.getPageSource().split("\n")));
    		driver.writeToTextFile1(pageSource, arg1);
    	}
       
    }
}
