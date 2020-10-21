package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import PageObjects.AddCampaignsPage;
import PageObjects.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class CampaignStepDefinitions{
	public WebDriver driver;	
	public LoginPage lp;
	public AddCampaignsPage acp;
	List<String> statusFlag = new ArrayList<>();
	
	@Before("@Campaign")
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./Drivers\\\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://admin-demo.nopcommerce.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@After("@Campaign")
	public void tearDown(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			scenario.attach("screenshot", "image/png", "./Screenshots/" + scenario.getName() + ".png");
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(FunctionLibrary.captureScreen(driver, scenario.getName()));
			scenario.log("screenshot attached");		
		}
		
		driver.quit();
	}
	
	@Given("User is on Campaign page")
	public void user_is_on_campaign_page() throws Exception {
		lp= new LoginPage(driver);
		acp= new AddCampaignsPage(driver);
		lp.setUsername("admin@yourstore.com");
		lp.setPassword("admin");
		lp.clickLogin();

		acp.clickPromotionTab();
		acp.clickCampaignsSubTab();
		Thread.sleep(1000);
	}

	@When("User clicks on Add button and enters below list of campaigns details")
	public void user_clicks_on_add_button_and_enters_below_list_of_campaigns_details(DataTable campaignTable) throws InterruptedException {

		
		for(Map<Object, Object> data: campaignTable.asMaps(String.class, String.class)) {			
		acp.clickAddNew();
		Thread.sleep(2000);
		acp.setCampaignsName(data.get("Name").toString());
		acp.setSubject(data.get("Subject").toString());
		acp.setBody(data.get("Body").toString());
		acp.clickSave();		
		Thread.sleep(2000);	
		
		if (driver.getPageSource().contains("The new campaign has been added successfully."))
			statusFlag.add("pass");
		else statusFlag.add("fail");
		
		}

	}

	@Then("Above Campaigns should be created successfully")
	public void above_campaigns_should_be_created_successfully() {
		if(statusFlag.contains("fail")) {
			System.out.println("All mentioned campaigns are not added successfully");
			Assert.assertTrue(false);
		}
		else {
			System.out.println("All mentioned campaigns are added successfully");
			Assert.assertTrue(true);
		}

	}

}
