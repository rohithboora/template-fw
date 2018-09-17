package Run;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import Utilities.AccDriver;
import cucumber.api.Scenario;


@RunWith(Suite.class)
@Suite.SuiteClasses({TestRun.class})
public class MainLaunch {

	private static AccDriver driver;
	private static Scenario scenario;
	
    @AfterClass()
    public static void tearDown() {
    	String a = scenario.getStatus();
    	System.out.print(a);
    	driver.quit();
    	  	   
    	    	if(scenario.isFailed()){
    	    		driver.methodtoscreenshotOverAll("Error Page");
    	    	}
    		 
    	    	 }
    	 }
    	
    	
    


