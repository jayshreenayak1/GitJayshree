package StartUp;

import java.awt.Color;
import java.lang.reflect.Method;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.WebDriver;

import ExcelUtility.Excel;
import FunctionLibrary.Functions;



public class Driver {

	public static String filepath = "C:\\Users\\jayshree.nayak\\workspace\\KeywordDriven\\src\\toolbox.xls";
	public static String sheetname = "Front end";
	public static WebDriver driver;
	public static String para1fromcell;
	public static String para2fromcell;
	public static String path1 = "D:\\TestFolder\\Test.xls";
	public static double TCNo;
	public static String TCName;
	public static String Keyword;
	public static String Parameter;
	public static String Results;
	public static String sheetname1="Test";
	public static  HSSFSheet spreadsheet;
	public static HSSFRow row;
	public static String reportstatus;
	public static boolean bFlag;
	public static String fail;
	


	public static void main(String[] args) throws Exception {
		
		Excel obj = new Excel();
		obj.setExcelPath(filepath, sheetname);


		Functions objfunction = new Functions( driver);
		
		Method meth[]  = objfunction.getClass().getDeclaredMethods();

		obj.createExcel(sheetname1);
		spreadsheet = obj.workbook.getSheet(sheetname1);

		spreadsheet.createRow(0).createCell(0).setCellValue("Test Case no");
		spreadsheet.getRow(0).createCell(1).setCellValue("Test Case name");
		spreadsheet.getRow(0).createCell(2).setCellValue("Keywords");
		spreadsheet.getRow(0).createCell(3).setCellValue("Parameters");
		spreadsheet.getRow(0).createCell(4).setCellValue("Result");
		//for(int col_No = 0; col_No <5; col_No++)
	//	{
		     //CellStyle style=null;
		     // Creating a font
		        HSSFFont font= obj.workbook.createFont();
		        font.setFontHeightInPoints((short)10);
		        font.setFontName("Arial");
		        font.setColor(IndexedColors.WHITE.getIndex());
		        font.setBold(true);
		        font.setItalic(false);
		

			/*
			 style.setBorderBottom(BorderStyle.MEDIUM);
			 
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			
			*/
	//	}

		int noOfRows = obj.roWCount(sheetname);
		for(int i =1;i< noOfRows; i++)
		{
			//get data from test case sheet

			TCNo = obj.readNumericFromExcel(sheetname, i, 0);
			TCName = obj.readExcelData(sheetname, i, 1);
			Keyword = obj.readExcelData(sheetname, i, 2);
			Parameter = obj.readExcelData(sheetname, i, 3);
			// write data from test case to report
			row = spreadsheet.createRow(i);
			if(TCNo!=0.0)
			{
				row.createCell(0).setCellValue(TCNo);
			}

			
			row.createCell(1).setCellValue(TCName);
			row.createCell(2).setCellValue(Keyword);
			row.createCell(3).setCellValue(Parameter);
			row.createCell(4).setCellValue(Results);
			

			

			
			

			for(int j=0; j<=meth.length-1; j++)
			{
				String currentmethod = meth[j].getName();
				
				String MethodIncell = obj.readExcelData(sheetname, i, 2);
				System.out.println(MethodIncell);
				
				if (MethodIncell.equalsIgnoreCase(currentmethod))
				{

					 if (Parameter.equals(""))
					 {
						 meth[j].invoke(objfunction); 
					 }
					 else if(Parameter.contains(">"))
					 {
							para1fromcell = Parameter.split(">")[0];
							para2fromcell = Parameter.split(">")[1];
						 meth[j].invoke(objfunction,para1fromcell,para2fromcell); 

					 }
					 else
					 {
						 meth[j].invoke(objfunction,Parameter); 

					 }
					
						reportstatus = objfunction.reportStep();
						row.createCell(4).setCellValue(reportstatus); 
						/*
						if(reportstatus == "Failed")
						{
						obj.captureScreenShot();
						}
						*/
						//break;
					 
				}
				if(i==noOfRows-1)
				{
					
					/*CellStyle style=obj.workbook.createCellStyle();
					style.setBorderBottom(BorderStyle.MEDIUM);
					style.setBorderTop(BorderStyle.MEDIUM);
					style.setBorderLeft(BorderStyle.MEDIUM);
					style.setBorderRight(BorderStyle.MEDIUM);
				obj.saveExcelReport();
				*/
				}
			}


}
		}
		

	}


