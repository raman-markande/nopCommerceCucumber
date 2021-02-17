package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class CommonFunctions {
	public ReadConfig config= new ReadConfig();
	

	public static String captureScreenForExtentReporting(WebDriver driver, String sname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + sname + ".png");
		FileUtils.copyFile(source, target);
		
		byte[] fileContent= FileUtils.readFileToByteArray(source);
		String Base64StringofScreenshot= "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		
		System.out.println("Screenshot taken");
		return Base64StringofScreenshot;
	}
	
	public static byte[] captureScreenForMvnCucumberReporting(WebDriver driver, String sname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + sname + ".png");
		FileUtils.copyFile(source, target);
		
		byte[] fileContent= FileUtils.readFileToByteArray(source);
		
		System.out.println("Screenshot taken");
		return fileContent;
	}
	
	public static String randomAlphabeticString(int c) {
		return RandomStringUtils.randomAlphabetic(c);
	}

}
