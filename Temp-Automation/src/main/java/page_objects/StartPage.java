package page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StartPage {
	
	private String buttonListOnTimeOutScreen;

	//private static final WebElement AIPreplacedinjourneytimedout = null;
	@FindBy(how = How.ID, using = "CTA_continue")
	private WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='section hard--bottom']/div[1]/p[2]")
	private WebElement AIPreplacedinjourneytimedout;
	
	/*@FindBy(how = How.XPATH, using = "//div[@class='section cta-section']/button[1]")
	private WebElement AIPreplacedinjourneytimedoutbutton;
	
	@FindBy(how = How.CSS, using = "#main > div > div > div:nth-child(2) > div.section.hard--bottom > div > p:nth-child(2)")
	private WebElement AIPreplacedinjourneytimedout;*/
	
	@FindBy(how = How.CSS, using = "#sessionDialog__content > div > p")
	private WebElement 	AIPreplacedintimeout;
	
	@FindBy(how = How.CLASS_NAME, using = "full--stop")
	private WebElement 	title;
	
	@FindBy(how = How.ID, using = "lst-ib")
	private WebElement 	searchField;

	@FindBy(how = How.LINK_TEXT, using = "Accenture (@Accenture) · Twitter")
	private WebElement accentureTwiterLink;

	public WebElement StartButton() {
		return searchButton;
	}
	
	public WebElement checkAIPreplacedinjourneytimedout() {
		return AIPreplacedinjourneytimedout;
	}	
	
	public WebElement checkAIPreplacedintimeout() {
		return AIPreplacedintimeout;
		
	}
	
	public WebElement getTitle() {
		return title;
		
	}

	/*-------------------------------------------------------------------------------*/
	public StartPage enterSearchText(String text) {

		searchField.sendKeys(text);
		
		return this;
	}
	
	public StartPage clickAccentureTwiterLink() {
		
		accentureTwiterLink.click();

		return this;
	}
	
	public StartPage clickStartButton() {

		searchButton.click();

		return this;
	}
	
	public String getListOfButton() {

		buttonListOnTimeOutScreen= "//div[@id='dialog']/div[@class='dialog sessionDialog']//button";
		 
		return buttonListOnTimeOutScreen;


	}

}
