package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class testOnly {
	public String scenarioName;
	
	  @Before("@Testonly") public void setup(Scenario scenario) {
	  scenarioName=scenario.getName(); }
	 
	
	
	@Given("scenario name")
	public void scenario_name() {

	}

	@Then("print scenario name")
	public void print_scenario_name() {
		System.out.println("Name of current executing scenario is " + scenarioName);

	}

}
