package page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AllLinks {

	@FindBy(how = How.LINK_TEXT, using = "government scheme to help buy a home")
	private WebElement govschemebuyhome;
	
	@FindBy(how = How.LINK_TEXT, using = "repayment mortgage")
	private WebElement repaymentmortgage;
	
	@FindBy(how = How.ID, using = "nextStep-BookAppointment")
	private WebElement bookappointmentbutton;
	
	@FindBy(how = How.LINK_TEXT, using = "book an appointment")
	private WebElement bookappointmentlink;
	
	@FindBy(how = How.ID, using = "nextStep-LearnMore")
	private WebElement learnmorehomebuying;
	
	@FindBy(how = How.ID, using = "helpUs_helpButton")
	private WebElement shareyourfeedback;
	
	@FindBy(how = How.LINK_TEXT, using = "completing our mortgage calculator")
	private WebElement mortgagecalculator;
	
	@FindBy(how = How.LINK_TEXT, using = "switch to a new deal here")
	private WebElement switchtonewdeal;
	
	@FindBy(how = How.LINK_TEXT, using = "apply to borrow more here")
	private WebElement applyborrowmore;
	
	@FindBy(how = How.LINK_TEXT, using = "www.lloydsbankinggroup.com")
	private WebElement lloydsbanking;
	
	@FindBy(how = How.LINK_TEXT, using = "http://www.lloydsbank.com/privacy.asp")
	private WebElement lloydspiracy;
	
	@FindBy(how = How.LINK_TEXT, using = "apply to borrow more here")
	private WebElement halifaxpiracy;
	
	@FindBy(how = How.LINK_TEXT, using = "Money Advice Service")
	private WebElement moneyadviceservice;
	
	@FindBy(how = How.LINK_TEXT, using = "Money saving expert")
	private WebElement moneysavingexpert;
	
	@FindBy(how = How.LINK_TEXT, using = "apply to borrow more here")
	private WebElement returnbutton;
		
	public AllLinks clickGovSchemeBuyHouseLink()
	{
		govschemebuyhome.click();
		return this;
	}
	
	public AllLinks clickRepaymentMortgageLink()
	{
		repaymentmortgage.click();
		return this;
	}
	
	public AllLinks clicBookAppointmentButton()
	{
		bookappointmentbutton.click();
		return this;
	}
	
	public AllLinks clickLearnMoreHomeBuyingLink()
	{
		learnmorehomebuying.click();
		return this;
	}
	
	public AllLinks clickShareFeedbackLink()
	{
		shareyourfeedback.click();
		return this;
	}
	
	public AllLinks clickBookAppointmentLink()
	{
		bookappointmentlink.click();
		return this;
	}
	
	public AllLinks clickMortgageCalculatorLink()
	{
		mortgagecalculator.click();
		return this;
	}
	
	public AllLinks clickSwitchToNewDealLink()
	{
		switchtonewdeal.click();
		return this;
	}
	
	public AllLinks clickApplyBorrowMoreLink()
	{
		applyborrowmore.click();
		return this;
	}
	
	public AllLinks clickLloydsBankingGroupLink()
	{
		lloydsbanking.click();
		return this;
	}
	
	public AllLinks clickBrandPiracyLink(String arg1)
	{

		if(arg1.equals("Lloyds"))
		{
			lloydspiracy.click();
		}else 
			if(arg1.equals("Halifax"))
			{
				halifaxpiracy.click();
			}
		return this;
	}
	
	public AllLinks clickMoneyAdviceServiceLink()
	{
		moneyadviceservice.click();
		return this;
	}
}
