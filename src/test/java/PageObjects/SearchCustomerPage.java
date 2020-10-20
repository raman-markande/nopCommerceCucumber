package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {	
	
	WebDriver ldriver;
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);		
	}
	
	@FindBy(xpath="//a[@href='#']//span[contains(normalize-space(),'Customers')]")
	@CacheLookup
	WebElement customerTab;
	
	@FindBy(xpath="(//span[contains(text(),'Customers')])[2]")
	@CacheLookup
	WebElement customerSubTab;
	
	@FindBy(xpath="//input[@id='SearchEmail']")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='SearchFirstName']")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='SearchLastName']")
	WebElement txtLastName;
	
	@FindBy(xpath="//button[@id='search-customers']")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(xpath="table[@id='customers-grid']")
	@CacheLookup
	WebElement customerTable;
	
	@FindBy(xpath="//table[@id='customers-grid']//tr")
	@CacheLookup
	List<WebElement> customerTableRows;
	
	@FindBy(xpath="//table[@id='customers-grid']//tr/td")
	@CacheLookup
	List<WebElement> customerTableColumns;
	
	
	public int getRowNum() {
		return customerTableRows.size();
	}
	
	public int getColNum() {
		return customerTableColumns.size();
	}
	
	public void clickCustomerTab() {
		customerTab.click();
	}
	
	public void clickCustomerSubTab() {
		customerSubTab.click();
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void clickSearch() {
		btnSearch.click();
	}
	
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag=false;
		
		for(int i=1;i<getRowNum(); i++) {
			String actual_email= ldriver.findElement(By.xpath("//table[@id='customers-grid']//tr["+ i + "]/td[2]")).getText().trim();
			if(actual_email.equals(email)) flag=true;
		}
		
		return flag;		
	}
	
	public boolean searchCustomerByName(String name) {
		boolean flag=false;
		
		for(int i=1;i<getRowNum(); i++) {
			String actual_name= ldriver.findElement(By.xpath("//table[@id='customers-grid']//tr["+ i + "]/td[3]")).getText().trim();
			if(actual_name.equals(name)) flag=true;
		}
		
		return flag;		
	}

}
