package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCampaignsPage {
	
	WebDriver ldriver;
	
	public AddCampaignsPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(xpath="//span[contains(text(),'Promotions')]")
	WebElement promotionTab;
	
	@FindBy(xpath="//span[contains(text(),'Campaigns')]")
	WebElement campaignsSubTab;
	
	@FindBy(linkText="Add new")
	WebElement btnAddNew;
	
	@FindBy(xpath="//input[@id='Name']")
	WebElement campaignsName;
	
	@FindBy(xpath="//input[@id='Subject']")
	WebElement textSubject;
	
	@FindBy(xpath="//textarea[@id='Body']")
	WebElement textBody;
	
	@FindBy(xpath="//select[@id='StoreId']")
	WebElement optionStore;
	
	@FindBy(xpath="//select[@id='CustomerRoleId']")
	WebElement optionCustomerRole;
	
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnSave;
	
	
	public void clickPromotionTab() {
		promotionTab.click();
	}
	
	public void clickCampaignsSubTab() throws InterruptedException {
		Thread.sleep(2000);
		campaignsSubTab.click();
	}
	
	public void clickAddNew() {
		btnAddNew.click();
	}
	
	public void setCampaignsName(String name) {
		campaignsName.sendKeys(name);
	}
	
	public void setSubject(String sub) {
		textSubject.sendKeys(sub);
	}
	
	public void setBody(String body) {
		textBody.sendKeys(body);
	}
	
	public void selectStore(String store) {
		Select select = new Select(optionStore);
		select.selectByVisibleText(store);
	}
	
	public void selectCustomerRole(String role) {
		Select select = new Select(optionCustomerRole);
		select.selectByVisibleText(role);
	}	
	
	public void clickSave() {
		btnSave.click();
	}
	
	

}
