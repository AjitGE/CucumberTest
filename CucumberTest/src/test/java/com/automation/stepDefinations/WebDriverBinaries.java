package com.automation.stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.EDGE;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;
import static io.github.bonigarcia.wdm.DriverManagerType.OPERA;
import static io.github.bonigarcia.wdm.DriverManagerType.PHANTOMJS;
public class WebDriverBinaries {
	public static void main(String[] args) {
		 WebDriverBinaries.browser("edge");
	}
	static Logger log = LogManager.getLogger(WebDriverBinaries.class);
	public static WebDriver browser(String browserName){
		
		WebDriver driver = null;
		try{
		switch(browserName.toLowerCase()){
		case "chrome" :{
			WebDriverManager.chromedriver().targetPath("Drivers_exe/chromedriver").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(CHROME).setup();
			return new ChromeDriver();
		}
		case "edge" :{
			WebDriverManager.edgedriver().targetPath("Drivers_exe/edgedriver").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(EDGE).setup();
			 return new EdgeDriver();
			
		}
		case "ie" :{
			WebDriverManager.iedriver().targetPath("Drivers_exe/iedriver").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(IEXPLORER).setup();
			return new  InternetExplorerDriver();
		}
		case "Opera" :{
			WebDriverManager.operadriver().targetPath("Drivers_exe/operadriver").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(OPERA).setup();
			return new OperaDriver();
		}
		case "safari" :{
			return new SafariDriver();
		}
		case "firefox" :{
			WebDriverManager.firefoxdriver().targetPath("Drivers_exe/firefoxdriver").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(FIREFOX).setup();
			return new FirefoxDriver();		}
		
		case "phantomjs" :{
			WebDriverManager.phantomjs().targetPath("Drivers_exe/phantomjs").avoidOutputTree().forceCache().setup();
			WebDriverManager.getInstance(PHANTOMJS).setup();
		    return new PhantomJSDriver();
		}
		default: {
			throw new Exception(String.format("Unhandled browser! browser name: '%s' need correction",browserName));
		} 
		}
		}
		catch (Exception ex) {
		log.error(ex.getMessage());
		ex.printStackTrace();
	}

	return driver;
		}
		
}

