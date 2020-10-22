package stepDefinitions;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import PageObjects.AddCategoryPage;
import PageObjects.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class CategoryStepDefinition extends FunctionLibrary{
	public WebDriver driver;
	public LoginPage lp;
	public AddCategoryPage acp;

	@Before("@Category")
	public void setup() {
		if(System.getProperty("browserName").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();
		} else if (System.getProperty("browserName").equals("edge")) {
			System.setProperty("webdriver.edge.driver", config.getEdgePath());
			driver = new EdgeDriver();
		} else
		{
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();			
		}

		driver.get(config.getbaseURL());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After("@Category")
	public void tearDown(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			scenario.attach(FunctionLibrary.captureScreenForMvnCucumberReporting(driver, scenario.getName()),"image/png", "./Screenshots/" + scenario.getName() + ".png");
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(FunctionLibrary.captureScreenForExtentReporting(driver, scenario.getName()));
			scenario.log("screenshot attached");
			driver.quit();
			Assert.assertTrue(false);
		}

		driver.quit();
	}

	@Given("User is on Category page")
	public void user_is_on_category_page() {
		lp = new LoginPage(driver);
		acp = new AddCategoryPage(driver);
		lp.setUsername(config.getUsername());
		lp.setPassword(config.getPassword());
		lp.clickLogin();

		acp.clickcatalogTab();
		acp.clickCategoriesSubTab();

	}

	@When("User provide below list of {string} , {string} as input data")
	public void user_provide_below_list_of_and_as_input_data(String cname, String pcategory) {
		acp.clickAddNew();
		//acp.clickSettingMode();
		acp.setName(cname);
		acp.selectParentCategory(pcategory);
		acp.clickSave();

	}

	@Then("below list of Categories should be created")
	public void below_list_of_categories_should_be_created() {
		if (driver.getPageSource().contains("The new category has been added successfully")) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);

	}

}
