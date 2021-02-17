package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import PageObjects.AddGiftCardPage;
import PageObjects.EditGiftCardPage;
import PageObjects.LoginPage;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.*;

import utilities.CommonFunctions;
import utilities.DriverInit;

public class GiftcardStepDefinition extends CommonFunctions {
	private WebDriver driver = DriverInit.getDriver();
	public LoginPage lp;
	public AddGiftCardPage agcp;
	public EditGiftCardPage egcp;
	Logger logger = Logger.getLogger(getClass().getName());

	/*
	@Before("@GiftCard")
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
	
	@After("@GiftCard")
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
	*/
	
	//Add Giftcard Step Definition

	@Given("User is on Giftcard page")
	public void user_is_on_giftcard_page() {
		lp= new LoginPage(driver);
		agcp = new AddGiftCardPage(driver);
		lp.setUsername(config.getUsername());
		lp.setPassword(config.getPassword());
		lp.clickLogin();
		logger.info("User logged in");

		agcp.clickSalesTab();
		agcp.clickGiftcardSubTab();
		logger.info("User is on Gift Card Page");

	}

	@When("User clicks on Add button and enters below giftcard details")
	public void user_clicks_on_add_button_and_enters_below_giftcard_details(DataTable dataTable) {
		List<List<String>> data = dataTable.asLists();
		agcp.clickAddNew();
		logger.info("Add New button clicked");
		agcp.selectOrdertype(data.get(1).get(0));
		agcp.setRecipientName(data.get(1).get(1));
		agcp.setSenderName(data.get(1).get(2));
		logger.info("Gift Card details entered");
		agcp.clickSave();
		logger.info("Save button clicked");

	}

	@Then("Above Giftcard should be created successfully")
	public void above_giftcard_should_be_created_successfully() {
		if (driver.getPageSource().contains("The new gift card has been added successfully.")) {
			Assert.assertTrue(true);
			logger.info("Passed: Gift card added successfully");
		} else {
			logger.info("Failed: Gift card not created");
			Assert.assertTrue(false);
		}

	}
	
	
	//Edit Giftcard Step Definition
	
	@When("User selects existing Giftcard with Recipient Name as {string} to edit")
	public void user_selects_existing_giftcard_with_recipient_name_as_to_edit(String recName) throws InterruptedException, IOException {
		egcp=new EditGiftCardPage(driver);
		Thread.sleep(2000);
		boolean recNamePresence= egcp.clickEdit(recName);		
		if(!recNamePresence) {
			//String base64AScreenshot=captureScreen(driver, scenarioName);
			logger.info("Recipient Name: " + recName +" is not present in data grid");
			Assert.assertTrue("Recipient Name: " + recName +" is not present in data grid", false);
		}
	}

	@When("updates Giftcard details as below")
	public void updates_giftcard_details_as_below(DataTable dataTable) throws InterruptedException {
		List<List<String>> data = dataTable.asLists();	
		egcp.selectOrdertype(data.get(1).get(0));
		egcp.setRecipientName(data.get(1).get(1));
		logger.info("Provided updated Giftcard details");
		egcp.clickSave();
		logger.info("Save button clicked");
		Thread.sleep(1000);
	}

	@Then("Giftcard should be updated successfully with new details")
	public void giftcard_should_be_updated_successfully_with_new_details() {
		
		if(driver.getPageSource().contains("The gift card has been updated successfully.")){
			Assert.assertTrue(true);
			logger.info("Passed: Gift card updated successfully");
		}else {
			logger.info("Failed: Gift card not updated");
			Assert.assertTrue(false);
		}

	}	

}