package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverInit {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tl = new ThreadLocal<>();
	public ReadConfig config;
	

	public WebDriver init_driver(String browser) {	
		config= new ReadConfig();
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			tl.set(new ChromeDriver());
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", config.getEdgePath());
			tl.set(new EdgeDriver());
		} else
		{
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			tl.set(new ChromeDriver());			
		}

		getDriver().get(config.getbaseURL());
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tl.get();
	}

}
