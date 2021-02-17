package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="./src\\test\\java\\Features",
				 glue={"stepDefinitions", "appHooks"},
				 monochrome=true,
				 dryRun=false,
				 publish=true,
				 plugin= {"json:target/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
				)
public class TestRun{

}
