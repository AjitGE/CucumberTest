package com.automation.stepDefinations;

import org.apache.commons.validator.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSourceDecider {

	static Logger log;
	static String obtainedUrl;

	static {
		log = LogManager.getLogger(DataSourceDecider.class);
	}

	public static String dataFinder(String url) throws Exception {
		
		
		if(url==null)
		{
			return null;
			}
		String s=url.substring(1);
		if(!(url.startsWith("#") || url.startsWith("$")) )
		{
		return urlChecker(url); //check url is valid else throws an exception
		}
		else if(url.startsWith("#")){
			
			obtainedUrl= ExcelReadWrite.getdata(s);
			return urlChecker(obtainedUrl);
			} 
		else if(url.startsWith("$")){
			obtainedUrl=DbReadWrite.getdata(s);
			return urlChecker(obtainedUrl);
		}
		throw new UnsupportedOperationException("This url: "+"\""+obtainedUrl+"\""+ " is in invalid format");
	}

	
	//check url is valid else throws an exception
	private static String urlChecker(String url) {
		UrlValidator urlValidator = new UrlValidator();
	    if (urlValidator.isValid(url)) 
	    {
	    	log.debug("Valid url....");
	    	return url;
	    } 
	    else {
	       throw new UnsupportedOperationException("This url: "+"\""+url+"\""+ " is in invalid format");
	    }
		
	}

}
