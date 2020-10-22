package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		FileInputStream is;
		try {
			is = new FileInputStream("./src/test/resources/config.properties");
			pro= new Properties();
			pro.load(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
				
	}
	
	public String getbaseURL() {
		return pro.getProperty("baseURL");
	}
	
	public String getUsername() {
		return pro.getProperty("username");
	}
	
	public String getPassword() {
		return pro.getProperty("password");
	}
	
	public String getChromePath() {
		return pro.getProperty("chromePath");
	}
	
	public String getEdgePath() {
		return pro.getProperty("edgePath");
	}
}
