package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	public ReadConfig() {
		FileInputStream is;
		try {
			is = new FileInputStream("./src/test/resources/config.properties");
			prop= new Properties();
			prop.load(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public String getbaseURL() {
		return prop.getProperty("baseURL");
	}
	
	public String getUsername() {
		return prop.getProperty("username");
	}
	
	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public String getChromePath() {
		return prop.getProperty("chromePath");
	}
	
	public String getEdgePath() {
		return prop.getProperty("edgePath");
	}
}
