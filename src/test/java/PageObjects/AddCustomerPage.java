package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	//@FindBy(xpath="(//span[contains(text(),'Customers')])[1]")
	@FindBy(xpath="//a[@href='#']//span[contains(normalize-space(),'Customers')]")
	@CacheLookup
	WebElement customerTab;
	
	@FindBy(xpath="(//span[contains(text(),'Customers')])[2]")
	@CacheLookup
	WebElement customerSubTab;
	
	@FindBy(linkText="Add new")
	@CacheLookup
	WebElement btnAddNew;
	
	@FindBy(how= How.XPATH, using="//input[@id='Email']")
	@CacheLookup
	WebElement textEmail;
	
	@FindBy(xpath="//input[@id='Password']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='FirstName']")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='LastName']")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='Gender_Male']")
	@CacheLookup
	WebElement radioMaleGender;
	
	@FindBy(xpath="//input[@id='DateOfBirth']")
	@CacheLookup
	WebElement txtDateOfBirth;
	
	@FindBy(xpath="//input[@id='Company']")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(xpath="//input[@id='IsTaxExempt']")
	@CacheLookup
	WebElement checkBoxIsTaxExempt;
	
	@FindBy(xpath="(//div[@class='k-multiselect-wrap k-floatwrap'])[1]")
	@CacheLookup
	WebElement divNewsletter;
	@FindBy(xpath="//ul[@id='SelectedNewsletterSubscriptionStoreIds_taglist']/following-sibling::input")
	@CacheLookup
	WebElement txtNewsletter;
	
	@FindBy(xpath="//select[@id='VendorId']")
	@CacheLookup
	WebElement selectMOV;
	
	@FindBy(xpath="//textarea[@id='AdminComment']")
	@CacheLookup
	WebElement txtAreaAdminComment;
	
	@FindBy(xpath="//button[@name='save']")
	@CacheLookup
	WebElement btnSave;
	
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
	
	public String getEmailFromGrid() {
		return ldriver.findElement(By.xpath("//table[@id='customers-grid']//tr[1]/td[2]")).getText().trim();
	}
	
	public void clickCustomerTab() {
		customerTab.click();
	}
	
	public void clickCustomerSubTab() {
		customerSubTab.click();
	}
	
	public void clickAddNew() {
		btnAddNew.click();
	}
	
	public void setEmail(String email) {
		textEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setFirstName(String firstname) {
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		txtLastName.sendKeys(lastname);
	}
	
	public void selectMale() {
		radioMaleGender.click();
	}
	
	public void setDateOfBirth(String dob) {
		txtDateOfBirth.sendKeys(dob);
	}
	
	public void setCompany(String company) {
		txtCompany.sendKeys(company);
	}
	
	public void setNewsletter(String newsletter) throws Throwable {
		divNewsletter.click();
		Thread.sleep(1000);
		txtNewsletter.sendKeys(newsletter);
		Thread.sleep(1000);
		txtNewsletter.sendKeys(Keys.ENTER);		
	}
	
	public void selectManagerOfVendor(String mov) {
		Select select = new Select(selectMOV);
		select.selectByVisibleText(mov);
	}
	
	public void setAreaAdminComment(String comment) {
		txtAreaAdminComment.sendKeys(comment);
	}
	
	public void clickSave() {
		btnSave.click();
	}

}
