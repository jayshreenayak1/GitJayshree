package ObjectRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;

import StartUp.Driver;

public class LoadObjectRepository {
	
	public static String filePath = "C:\\Users\\jayshree.nayak\\workspace\\KeywordDriven\\src\\ObjectRepository\\Objects.properties";
	//public static String filePath = "Objects.properties";
	public Properties prop;
	public String propfromOR;
	public String locatorType;
	public String locatorValue;
	public File f;
		

	
	public Properties loadObjects(String filePath) throws IOException
	{
		 f = new File(filePath);
		prop = new Properties();
		InputStream fs = new FileInputStream(f);
		prop.load(fs);
		//System.out.println(prop.getProperty("txtSearchBox"));
		return prop;
		
	}
	
	public By getLocator(String ElementName) throws IOException
	{

		if(Driver.Parameter.contains(">"))
		{
			propfromOR = loadObjects(filePath).getProperty(Driver.para1fromcell);
					
		}
		else 
		{

			propfromOR = loadObjects(filePath).getProperty(Driver.Parameter);
		}
		
		locatorType = propfromOR.split("#")[0];
		locatorValue = propfromOR.split("#")[1];
		

			if(locatorType.equals("id"))
			{
				return By.id(locatorValue);
			}

			if(locatorType.equals("name"))
			{
				return By.name(locatorValue);
			}
			if(locatorType.equals("xpath"))
			{
				return By.xpath(locatorValue);
			}


		return null;
	}


}
