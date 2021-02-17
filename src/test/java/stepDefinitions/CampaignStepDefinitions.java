package stepDefinitions;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import PageObjects.AddCampaignsPage;
import PageObjects.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.CommonFunctions;
import utilities.DriverInit;

public class CampaignStepDefinitions extends CommonFunctions{
	private WebDriver driver = DriverInit.getDriver();
	public LoginPage lp;
	public AddCampaignsPage acp;
	List<String> statusFlag = new ArrayList<>();
	Logger logger = Logger.getLogger(getClass().getName());
	//PropertyConfigurator.configure("Log4j.properties");
	
	
	
	/*@Before("@Campaign")
	public void setup(Scenario scenario) {
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
		
		logger= Logger.getLogger(scenario.getName());
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	@After("@Campaign")
	public void tearDown(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			scenario.attach(FunctionLibrary.captureScreenForMvnCucumberReporting(driver, scenario.getName()),"image/png", "./Screenshots/" + scenario.getName() + ".png");
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(FunctionLibrary.captureScreenForExtentReporting(driver, scenario.getName()));
			scenario.log("screenshot attached");
			driver.quit();
			Assert.assertTrue(false);
		}

		driver.quit();
	}*/
	
	@Given("User is on Campaign page")
	public void user_is_on_campaign_page() throws Exception {
		lp= new LoginPage(driver);
		acp= new AddCampaignsPage(driver);
		lp.setUsername(config.getUsername());
		lp.setPassword(config.getPassword());
		lp.clickLogin();
		logger.info("User logged in");
		Thread.sleep(2000);

		acp.clickPromotionTab();
		acp.clickCampaignsSubTab();
		Thread.sleep(1000);
		logger.info("User is on Campaigns Page");
	}

	@When("User clicks on Add button and enters below list of campaigns details")
	public void user_clicks_on_add_button_and_enters_below_list_of_campaigns_details(DataTable campaignTable) throws InterruptedException {

		
		for(Map<Object, Object> data: campaignTable.asMaps(String.class, String.class)) {			
		acp.clickAddNew();
		logger.info("Add New button clicked");
		Thread.sleep(2000);
		acp.setCampaignsName(data.get("Name").toString());
		acp.setSubject(data.get("Subject").toString());
		acp.setBody(data.get("Body").toString());
		logger.info("Gift Card details entered");
		acp.clickSave();
		logger.info("Save button clicked");
		Thread.sleep(2000);	
		
		if (driver.getPageSource().contains("The new campaign has been added successfully."))
			statusFlag.add("pass");
		else statusFlag.add("fail");
		
		}

	}

	@Then("Above Campaigns should be created successfully")
	public void above_campaigns_should_be_created_successfully() {
		if(statusFlag.contains("fail")) {
			logger.info("Failed: All mentioned campaigns are not added successfully");
			System.out.println("All mentioned campaigns are not added successfully");
			Assert.assertTrue(false);
		}
		else {
			System.out.println("Passed: All mentioned campaigns are added successfully");
			logger.info("Passed: All mentioned campaigns are added successfully");
			Assert.assertTrue(true);
		}

	}

}
