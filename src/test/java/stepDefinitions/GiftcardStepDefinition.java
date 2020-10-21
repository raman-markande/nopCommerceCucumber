package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import PageObjects.AddGiftCardPage;
import PageObjects.EditGiftCardPage;
import PageObjects.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class GiftcardStepDefinition{
	public WebDriver driver;	
	public LoginPage lp;
	public AddGiftCardPage agcp;
	public EditGiftCardPage egcp;
	

	@Before("@GiftCard")
	public void setup(Scenario scenario) {
		System.setProperty("webdriver.chrome.driver", "./Drivers\\\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://admin-demo.nopcommerce.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After("@GiftCard")
	public void tearDown(Scenario scenario) throws IOException {
		
		if(scenario.isFailed()) {
			scenario.attach(FunctionLibrary.captureScreenForMvnCucumberReporting(driver, scenario.getName()), "image/png", "./Screenshots/" + scenario.getName() + ".png");
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(FunctionLibrary.captureScreenForExtentReporting(driver, scenario.getName()));
			scenario.log("screenshot attached");
			driver.quit();
			Assert.assertTrue(false);
		}
		
		driver.quit();
	}
	
	//Add Giftcard Step Definition

	@Given("User is on Giftcard page")
	public void user_is_on_giftcard_page() {
		lp= new LoginPage(driver);
		agcp = new AddGiftCardPage(driver);
		lp.setUsername("admin@yourstore.com");
		lp.setPassword("admin");
		lp.clickLogin();

		agcp.clickSalesTab();
		agcp.clickGiftcardSubTab();

	}

	@When("User clicks on Add button and enters below giftcard details")
	public void user_clicks_on_add_button_and_enters_below_giftcard_details(DataTable dataTable) {
		List<List<String>> data = dataTable.asLists();
		agcp.clickAddNew();
		agcp.selectOrdertype(data.get(1).get(0));
		agcp.setRecipientName(data.get(1).get(1));
		agcp.setSenderName(data.get(1).get(2));
		agcp.clickSave();

	}

	@Then("Above Giftcard should be created successfully")
	public void above_giftcard_should_be_created_successfully() {
		if (driver.getPageSource().contains("The new gift card has been added successfully.")) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);

	}
	
	
	//Edit Giftcard Step Definition
	
	@When("User selects existing Giftcard with Recipient Name as {string} to edit")
	public void user_selects_existing_giftcard_with_recipient_name_as_to_edit(String recName) throws InterruptedException, IOException {
		egcp=new EditGiftCardPage(driver);
		Thread.sleep(2000);
		boolean recNamePresence= egcp.clickEdit(recName);		
		if(!recNamePresence) {
			//String base64AScreenshot=captureScreen(driver, scenarioName);
			Assert.assertTrue("Recipient Name: " + recName +" is not present in data grid", false);
		}
	}

	@When("updates Giftcard details as below")
	public void updates_giftcard_details_as_below(DataTable dataTable) throws InterruptedException {
		List<List<String>> data = dataTable.asLists();		
		egcp.selectOrdertype(data.get(1).get(0));
		egcp.setRecipientName(data.get(1).get(1));
		egcp.clickSave();
		Thread.sleep(1000);
	}

	@Then("Giftcard should be updated successfully with new details")
	public void giftcard_should_be_updated_successfully_with_new_details() {
		
		if(driver.getPageSource().contains("The gift card has been updated successfully.")){
			Assert.assertTrue(true);
		}else Assert.assertTrue(false);

	}	

}
