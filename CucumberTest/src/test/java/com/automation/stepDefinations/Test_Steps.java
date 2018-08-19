package com.automation.stepDefinations;


import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {
	static Logger log = LogManager.getLogger("Test_Steps.class");
	public static WebDriver driver;
	@Given("^user launches \"([^\"]*)\" browser with url \"([^\"]*)\"$")
	public void user_launches_browser_with_url(String browserName, String url) throws Throwable {
	    driver=WebDriverBinaries.browser(browserName);
       String ObatainedUrl=DataSourceDecider.getData(url);
        driver.get(ObatainedUrl);
		
	}
	
	@Given("^user loads the data from \"([^\"]*)\" located at \"([^\"]*)\"$")
	public void user_loads_the_data_from_located_at(String dataSource, String path) throws Throwable {
	    // data for  Excel sheet and  data base data
		// excel sheet: (first argument: Excel) and (second argument: DB)
		if(dataSource.toLowerCase().equals("excel"))
		{
			ExcelReadWrite.loadExcelFileData(path);
		}
		else if(dataSource.toLowerCase().equals("db"))
		{
			
		}
		else
		{
			throw new Exception("problem: Data source is not defined!!!!+ "//n"
					          +" solution: Use excel or db only in feature file" );
		}
		
		
	}
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver","Drivers_exe//chromedriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.store.demoqa.com");
		}

	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='account']/a")).click();
		}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password() throws Throwable {
		driver.findElement(By.id("log")).sendKeys("testuser_1"); 	 
	    driver.findElement(By.id("pwd")).sendKeys("Test@123");
	    driver.findElement(By.id("login")).click();
		}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {
		System.out.println("Login Successfully");
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
	}

	@Then("^Message displayed Logout Successfully$")
	public void message_displayed_Logout_Successfully() throws Throwable {
        System.out.println("LogOut Successfully");
	}

}