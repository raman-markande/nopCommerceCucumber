package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddGiftCardPage {
	
	WebDriver ldriver;
	
	public AddGiftCardPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver,  this);
	}
	
	
	@FindBy(xpath="//span[contains(text(),'Sales')]")
	WebElement salesTab;
	
	@FindBy(xpath="//span[contains(text(),'Gift cards')]")
	WebElement giftcardSubTab;
	
	@FindBy(linkText="Add new")
	WebElement btnAddNew;
	
	@FindBy(how= How.XPATH, using="//select[@id='GiftCardTypeId']")
	WebElement optionOrdertype;
	
	@FindBy(xpath="//input[@id='Amount']")
	WebElement textAmount;
	
	@FindBy(xpath="//input[@id='RecipientName']")
	WebElement textRecipientName;
	
	@FindBy(xpath="//input[@id='SenderName']")
	WebElement textSenderName;
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnSave;
	
	
	public void clickSalesTab() {
		salesTab.click();
	}
	
	public void clickGiftcardSubTab() {
		giftcardSubTab.click();
	}
	
	public void clickAddNew() {
		btnAddNew.click();
	}
	
	public void selectOrdertype(String order_type) {
		Select select = new Select(optionOrdertype);
		select.selectByVisibleText(order_type);
	}
	
	public void setAmount(String amount) {
		textAmount.sendKeys(amount);;
	}
	
	public void setRecipientName(String rname) {
		textRecipientName.sendKeys(rname);
	}
	
	public void setSenderName(String sname) {
		textSenderName.sendKeys(sname);
	}
	
	public void clickSave() {
		btnSave.click();
	}
	

}
