package Run;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		tags = {"@TC-HUB-CMS, @TC-AIP-CMS, @TC-AppForm-CMS, @TC-PS-KFI-CMS, @TC-DocUpload-CMS"},
		features = { "features", "BR_Features" },
		format = {"pretty", "html:Reports/html", "junit:Reports/junit/junit-report.xml", "json:Reports/json/json-report.json"}, 
		glue = {"browser_Start_Up","aip_React_Page_Steps","app_Data_Form_Page_Steps","hub_Page_Steps","customize_Your_Mortgage_Page_Steps","kfi_Page_Steps","docUpload_Page_Steps"})

public class CMSTestRun {

}