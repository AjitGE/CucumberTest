package com.automation.stepDefinations;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import cucumber.api.Scenario;

public class ExcelReadWrite {
	static Logger log =LogManager.getLogger(ExcelReadWrite.class);
	static Scenario scenario;
	static String path;
	static FileInputStream fIP;
    static XSSFWorkbook workBook;
    static XSSFSheet spreadsheet;
    static Row row;
    static Cell cell;
    static String cellData;
    static List data;
    static String sheetName ;
    static ArrayList<String> testIdNumber=new ArrayList<String>();
    final static String  testIdColumnName="TestId";
    static HashMap<String, LinkedHashMap<Integer, List<String>>> outerMap;
	static ArrayList<String> tags= new ArrayList<String>();
	static String cellValue;
	 public static void tagNames(Scenario scenario){
		 ExcelReadWrite.scenario=scenario;
		 log.info("total number of tags used in file are: "+scenario.getSourceTagNames().size());
		 tags.addAll(scenario.getSourceTagNames());
		 log.info("Tags used are: "+tags);
	}

	public static void getTestId() throws Exception {
		
		tagNames(scenario);
		boolean b=false;
		for (int i=0; i<tags.size();i++) {
			if(tags.get(i).contains("TestId")) {
				
			  testIdNumber.add(tags.get(i).substring(8));
			  b=true;
			 }
		}
		 if (!b) {
				throw new Exception("TestId is missing! Please add Test_ID tag in feature file:");
			}
		
			}
     
		

		
	
	
	public static HashMap<String, LinkedHashMap<Integer, List<String>>> loadExcelFileData(String path)
    {
        Integer values;
        // Used the LinkedHashMap and LikedList to maintain the order
        outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();

        LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
        
        // Create an ArrayList to store the data read from excel sheet.
        // List sheetData = new ArrayList();
		File fileName = new File(path);
        try
        {
        	fIP = new FileInputStream(fileName);
            // Create an excel workbook from the file system
            workBook = new XSSFWorkbook(fIP);
            // Get the first sheet on the workbook.
            for (int i = 0; i < workBook.getNumberOfSheets(); i++)
            {
                XSSFSheet sheet = workBook.getSheetAt(i);
                // XSSFSheet sheet = workBook.getSheetAt(0);
                System.out.println(sheetName = workBook.getSheetName(0));

                Iterator<Row> rows = sheet.iterator();
                while (rows.hasNext())
                {
                     row = rows.next();
                    Iterator<Cell> cells = row.iterator();

                    data = new LinkedList<String>();
                    
                    while (cells.hasNext())
                    {   
                    	
                         cell =  cells.next();
                        switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN: 
                        boolean b=cell.getBooleanCellValue(); 
                        data.add(b); 
                        break; 
                        case Cell.CELL_TYPE_NUMERIC: 
                       values =(int)cell.getNumericCellValue(); 
                       data.add(values);
                         break; 
                        case Cell.CELL_TYPE_STRING: 
                        String s=cell.getStringCellValue(); 
                        data.add(s); 
                        break; 
                        
                        }
                      
                      
                    }
                    
					hashMap.put(row.getRowNum(), data);

                    // sheetData.add(data);
                }
                outerMap.put(sheetName, hashMap);
                hashMap = new LinkedHashMap<Integer, List<String>>();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fIP != null)
            {
                try
                {
                    fIP.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println(outerMap);
        return outerMap;

    }

	public static String getdata(String url) throws Exception {
		// TODO Auto-generated method stub
		ExcelReadWrite.getTestId();
		int columnOfTestId,columnofUrl;
		
		LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
		if(outerMap.isEmpty()){
			throw new Exception("Excel file have no data");
		}
		else{
			for (int i = 0; i < workBook.getNumberOfSheets(); i++)
            {
				XSSFSheet sheet = workBook.getSheetAt(i);
			hashMap=outerMap.get(sheet.getSheetName());
						
			columnOfTestId=hashMap.get(0).indexOf(testIdColumnName);
			columnofUrl=hashMap.get(0).indexOf(url);
			
			for(int j=1; j<hashMap.keySet().size();j++)
			{
				
				if(hashMap.get(j).get(columnOfTestId) instanceof String ) {
					
					if(hashMap.get(j).get(columnOfTestId).equals(testIdNumber.get(0)))
					{
						cellValue=hashMap.get(j).get(columnofUrl);
					}
				}
				else {
					if(String.valueOf(hashMap.get(j).get(columnOfTestId)).equals(testIdNumber.get(0)))
					{
						cellValue=String.valueOf(hashMap.get(j).get(columnofUrl));
					}
				}
				
					
				
			}
		}
		
	}
		return cellValue;
	}
}
		

	

		
		
		
		
	


