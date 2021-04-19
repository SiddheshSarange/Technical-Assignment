package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/features" }, glue = {
		"stepDefinition" }, plugin = "json:target/jsonReports/cucumber-reports.json")
public class TestRunner {

}
