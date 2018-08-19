package com.automation.TestNGrunners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.automation.Utility.TimeStamp;
import com.automation.Utility.Util;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberFeatureOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
retryCount = 3,
detailedReport = true,
detailedAggregatedReport = true,
overviewReport = true,
coverageReport = true,
jsonUsageReport = "target/cucumber-usage.json",
usageReport = true,
toPDF = true,
outputFolder = "target")



@CucumberOptions(plugin = { "html:target/cucumber-html-report",
"json:target/cucumber.json", 
"pretty:target/cucumber-pretty.txt",
"usage:target/cucumber-usage.json", 
"junit:target/cucumber-results.xml",
"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"},
monochrome = true,
dryRun = false,
features = "src/test/resources/features",
glue = "com.automation.stepDefinations",
tags = {"@apps"}
)
public class TestNGrunner extends AbstractTestNGCucumberTests { 


	@AfterTest
	private void test() {
		try {
			generateCustomReports();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//copyReportsFolder();
	}
	private void generateCustomReports() throws Exception {
		CucumberFeatureOverview results = new CucumberFeatureOverview();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setSourceFile("target/cucumber.json");
		results.execute(true);
		
		CucumberDetailedResults detailedResults= new CucumberDetailedResults();
		detailedResults.setOutputDirectory("target/");
		detailedResults.setOutputName("cucumber-results");
		detailedResults.setSourceFile("target/cucumber.json");
		detailedResults.setScreenShotLocation("cucumber-results/Screenshot/");
		detailedResults.execute(true);
		detailedResults.execute(false);

        }
	@Test(enabled = false)
        private void copyReportsFolder() {
            
    		String timeStampResultPath = TimeStamp.getInstance();

    		File sourceCucumber = new File(Util.getTargetPath());

    		File destCucumber = new File(timeStampResultPath);

    		try {
    			FileUtils.copyDirectory(sourceCucumber, destCucumber);
    			try {
    				FileUtils.cleanDirectory(sourceCucumber);
    			} catch (Exception e) {

    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    		TimeStamp.reportPathWithTimeStamp = null;

    	}

    	@AfterSuite
    	private void copyStoredReports() {
    		// Any customizations after execution can be added here
    	}


}
