package com.automation.stepDefinations;

import org.apache.commons.validator.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LocateStratergy {
	static Logger log;

	static {
		log = LogManager.getLogger(LocateStratergy.class);
	}

	public static String UrlFinder(String url) throws Exception {
		if(url==null)
		{
			return null;
			}
		if(!(url.startsWith("#") && url.startsWith("$")) )
		{
		return urlChecker(url); //check url is valid else throws an exception
		}
		else if(url.startsWith("#")){
			String obtainedUrl= ExcelReadWrite.getColumnData(url);
			return urlChecker(obtainedUrl);
			} 
		else if(url.startsWith("$")){
			String obtainedUrl=DbReadWrite.getdata(url);
			return urlChecker(obtainedUrl);
		}
		throw new UnsupportedOperationException("This url: "+"\""+url+"\""+ " is in invalid format");
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
