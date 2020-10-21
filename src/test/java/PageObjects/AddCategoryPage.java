package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCategoryPage {
	WebDriver ldriver;
	
	public AddCategoryPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(xpath="//a[@href='#']//span[contains(normalize-space(),'Catalog')]")
	WebElement catalogTab;
	
	@FindBy(xpath="//span[contains(normalize-space(),'Categories')]")
	WebElement categoriesSubTab;
	
	@FindBy(linkText="Add new")
	WebElement btnAddNew;
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnSave;
	
	@FindBy(xpath="//input[@id='Name']")
	WebElement txtName;
	
	@FindBy(xpath="//select[@id='ParentCategoryId']")
	WebElement optionParentCategory;
	
	@FindBy(xpath="//input[@id='Published']")
	WebElement chkPublished;
	
	@FindBy(xpath="//label[@for='advanced-settings-mode']")
	WebElement settingMode;
	
	
	public void clickcatalogTab() {
		catalogTab.click();
	}
	
	public void clickCategoriesSubTab() {
		categoriesSubTab.click();
	}
	
	public void clickAddNew() {
		btnAddNew.click();
	}
	
	public void clickSettingMode() {
		settingMode.click();
	}
	
	public void clickSave() {
		btnSave.click();
	}
	
	public void setName(String name) {
		txtName.sendKeys(name);
	}
	
	public void selectParentCategory(String pcategory) {
		Select select = new Select(optionParentCategory);
		select.selectByVisibleText(pcategory);
	}
	
	public void checkPublished(String pub) {
		if (pub.equals("Yes")) {
			if(!chkPublished.isSelected()) chkPublished.click();
		}
		else if (pub.equals("No")) {
			if(chkPublished.isSelected()) chkPublished.click();
		}
	}
	
	

}
