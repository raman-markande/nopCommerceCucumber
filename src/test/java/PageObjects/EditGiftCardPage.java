package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditGiftCardPage {
	
WebDriver ldriver;
	
	public EditGiftCardPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver,  this);
	}
	
	
	@FindBy(xpath="//table[@id='giftcards-grid']//tr")
	List<WebElement> giftcardTableRows;
	
	@FindBy(xpath="//table[@id='giftcards-grid']//tr/td")
	List<WebElement> giftcardTableCols;

	
	@FindBy(how= How.XPATH, using="//select[@id='GiftCardTypeId']")
	WebElement optionOrdertype;
	
	
	@FindBy(xpath="//input[@id='RecipientName']")
	WebElement textRecipientName;
	
	@FindBy(xpath="//input[@id='SenderName']")
	WebElement textSenderName;
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnSave;
	
	
	public int getRowNum() {
		return giftcardTableRows.size();
	}
	
	public int getColNum() {
		return giftcardTableCols.size();
	}
	
	
	public boolean clickEdit(String recptName) throws InterruptedException {
		boolean flag=false;
		for(int i=1;i<getRowNum(); i++) {			
			String gridRecName= ldriver.findElement(By.xpath("//table[@id='giftcards-grid']//tr["+ i + "]/td[4]")).getText().trim();
			if(gridRecName.equals(recptName)) {
				ldriver.findElement(By.xpath("//table[@id='giftcards-grid']//tr["+ i + "]/td[7]/a")).click();
				flag=true;
			}
		}
		return flag;
		
	}
	
	public void selectOrdertype(String order_type) {
		Select select = new Select(optionOrdertype);
		select.selectByVisibleText(order_type);
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
