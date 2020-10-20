package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);		
	}

	@FindBy(name="Email")
	WebElement text_username;
	
	@FindBy(id="Password")
	WebElement text_password;
	
	@FindBy(xpath="//input[@class='button-1 login-button']")
	WebElement button_login;
	
	@FindBy(xpath="//a[contains(normalize-space(),'Logout')]")
	WebElement linkLogout;  
	
	public void setUsername(String username) {
		text_username.clear();
		text_username.sendKeys(username);
	}
	
	public void setPassword(String password) {
		text_password.clear();
		text_password.sendKeys(password);
	}
	
	public void clickLogin() {
		button_login.click();
	}
	
	public void clickLogout() {
		linkLogout.click();
	}
	
}
