package com.automation.stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class CucumberHooks {
	static Logger log;

	static {
		log =LogManager.getLogger(CucumberHooks.class);
	}

	@Before
	public void setUp(Scenario scenario) {
		ExcelReadWrite.tagNames(scenario);
		try {
			 
			
				 
			 }
		catch(Exception e){
			e.getMessage();
		}
            
}
	}
