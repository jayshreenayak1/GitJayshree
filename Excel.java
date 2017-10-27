package ExcelUtility;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import FunctionLibrary.Functions;

public class Excel {
	public File f;
	public Workbook wb;
	public Sheet sheet;
	public Cell cell;
	public Row row;
	public HSSFWorkbook workbook;
	public String fpath;
	public String sheetname1;
	public String SheetName;
	public int rowno;
	public int colno;
	public String Status;
	public   WebDriver driver;
	
	
	Functions fun = new Functions(driver);
	
	//**********************************************************************
	public void setExcelPath(String fpath,String SheetName) throws Exception
	{
		
		f = new File(fpath);
		FileInputStream fi = new FileInputStream(f);
		if(fpath.endsWith(".xls"))
			wb = new HSSFWorkbook(fi);		
		else if(fpath.endsWith(".xlsx"))
			wb = new XSSFWorkbook(fi);
		
		//sheet  = wb.getSheet(SheetName);
	
		
	}
	//**************************************************************************
	public String readExcelData(String SheetName,int rowno,int colno)
	{
		return wb.getSheet(SheetName).getRow(rowno).getCell(colno).getStringCellValue();
	}
	
	//****************************************************************************
	public int roWCount(String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum()+1;
	}
	//*********************************************************************************
	public void createExcel(String sheetname1) throws Exception
	{
		workbook = new HSSFWorkbook();
		workbook.createSheet(sheetname1);


		
	}
//*************************************************************************************
	public void writeExcel(String SheetName,int rowno,int colno,String setvalue) throws Exception
	{
		//f= new File(fpath);
		FileOutputStream fo  = new FileOutputStream(f);
	
		sheet = wb.getSheet(SheetName);
		row = sheet.getRow(rowno);
		cell=row.getCell(colno);
		
		if(cell==null)
			cell = row.createCell(colno);
		
		cell.setCellValue(setvalue);
		wb.write(fo);
		
	}
	//************************************************************************************
	public void saveExcelReport() throws Exception
	{
		Date date = new Date();
		String time = date.toString().replace(":", "-").replace(".", "-");
		
		fpath = "D:\\TestFolder\\" +"TestReport - " + time + ".xls";
		FileOutputStream fo1  = new FileOutputStream(fpath);
		workbook.write(fo1);
		fo1.close();
	}
	//************************************************************************************

	public double readNumericFromExcel(String SheetName, int rowno, int colno) 
	{
			return wb.getSheet(SheetName).getRow(rowno).getCell(colno).getNumericCellValue();
	}
	//*************************************************************************************
	public void setFontStyle(int fontSize)
	{
		CellStyle style = null;
		HSSFRow row =  workbook.getSheet("1").getRow(0);
		row.setRowStyle(style);
	}

	//*******************************************************************************************

	public  void captureScreenShot() throws Exception {
		
	    File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    Date date = new Date();
	    String time = date.toString().replace(":", "-").replace(".", "-");
		
		fpath = "D:\\TestFolder\\" +"TestReport - " + time + ".xls";
	    FileUtils.copyFile(screenshotFile,new File(fpath +".png"));
	}

	public  String GetTimeStampValue()throws IOException{
	    Calendar cal = Calendar.getInstance();       
	    Date time=cal.getTime();
	    String timestamp=time.toString();
	    System.out.println(timestamp);
	    String systime=timestamp.replace(":", "-");
	    System.out.println(systime);
	    return systime;
	}
	/*
	public void takeScreenshot() throws IOException{
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	// Call method to capture screenshot
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	// Copy files to specific location here it will save all screenshot in our project home directory and
	// result.getName() will return name of test case so that screenshot name will be same
	FileUtils.copyFile(source, new File("./Screenshots/"+GetTimeStampValue()+".png"));
	}
	*/
	
}

