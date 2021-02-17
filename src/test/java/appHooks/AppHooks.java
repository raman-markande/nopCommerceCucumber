package appHooks;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverInit;

public class AppHooks {
	
	private WebDriver driver;
	private DriverInit di;
	
	@Before()
	public void setup(Scenario scenario) {
		di= new DriverInit();
		driver = di.init_driver("chrome"); //(System.getProperty("browserName"));
		
	}
	
	
	@After(order=0)
	public void quiteDriver() throws IOException {
		driver.quit();
	}
	
	
	@After(order=1)
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {
			
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", scenario.getName());
			
			//scenario.attach(FunctionLibrary.captureScreenForMvnCucumberReporting(driver, scenario.getName()),"image/png", "./Screenshots/" + scenario.getName() + ".png");
			//ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(FunctionLibrary.captureScreenForExtentReporting(driver, scenario.getName()));
			scenario.log("screenshot attached");
			//driver.quit();
			//Assert.assertTrue(false);
		}
	}
	
}
