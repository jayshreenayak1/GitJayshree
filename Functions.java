package FunctionLibrary;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

import ObjectRepository.LoadObjectRepository;
import StartUp.Driver;



public class Functions  {
	
	public  static WebDriver driver;
	public boolean bFlag;
	
	public Functions(WebDriver driver)
	{
		this.driver = driver;
	}
	
	LoadObjectRepository objRead = new LoadObjectRepository();
	Driver dr = new Driver();
	
	public String reportStep()
	{
		if (bFlag == true)
		{
			 return "Passed";
		}
		else
		{
			 return "Failed";
		}

	}
	
	public boolean openBrowser(String url)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		if(driver.getTitle().contains(url))
		{
			bFlag = false;
		}
		return bFlag;
		
	}

	public void EnterText(String element, String entertext) throws Exception
	{
		driver.findElement(objRead.getLocator(Driver.para1fromcell)).clear();
		driver.findElement(objRead.getLocator(Driver.para1fromcell)).sendKeys(Driver.para2fromcell);
		
		if(driver.findElement(objRead.getLocator(Driver.para1fromcell)).getText().equalsIgnoreCase(Driver.para2fromcell)){
			bFlag = true;
		}
	}
	public void ClickElement(String element) throws Exception
	{
		try{

		Boolean exist = driver.findElement(objRead.getLocator(Driver.Parameter)).isDisplayed();
		if(exist=true){
		driver.findElement(objRead.getLocator(Driver.Parameter)).click();
		bFlag = true;
		}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element does not exist");
		};


	}
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	public boolean clickAddToOrder() throws InterruptedException
	{
		//bFlag = (Boolean) null;
		Thread.sleep(10000);
		List <WebElement> searchImages = driver.findElements(By.xpath("//*[@class='box thumbnailurl']"));

		List <WebElement> orderLink = driver.findElements(By.xpath("//*[starts-with(@id,'orderlink')]"));
		Actions action = new Actions(driver);
		for(int i = 0;i<4;i++)
		{
			WebElement element = searchImages.get(i);
			 
	        action.moveToElement(element).build().perform();

	        	orderLink.get(i).isDisplayed();
	        	Thread.sleep(2000);
	        	
	        	String title = orderLink.get(i).getAttribute("title");
	        	if(title.contains("Add to order"))
	        	{
	        		orderLink.get(i).click();	
	        	}
	        	

        	Thread.sleep(2000);	
		}
		bFlag = false;
		return bFlag;	
	}
	public void selectItemFromSearchBox()
	{
		
		List <WebElement> list = driver.findElements(By.xpath("//li[@class='ui-menu-item']"));

		for(int i=0; i<list.size();i++)
		{
			System.out.println(list.get(i));
			list.get(i).click();
			
		}
		
		//Actions action = new Actions(driver);
		//action.moveToElement(target);
		
	}
}
