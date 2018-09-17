package Run;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		tags = {"@TC01"},
		features = { "features"},
		format = {"pretty", "html:Reports/html", "junit:Reports/junit/junit-report.xml", "json:Reports/json/json-report.json", "json:target/site/cucumber.json", "html:target/site/cucumber-pretty"}, 
		glue = {"browser_Start_Up","page_steps"})

public class TestRun {

}