package GoogleSearch;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import excel.utility.Xls_Reader;

import org.testng.annotations.BeforeClass;

import Utility.Utilities;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 
public class newDataDriven {
	
	 public static WebDriver driver = new InternetExplorerDriver();
	 WebDriverWait wait = new WebDriverWait(driver, 120);	 
	 WebElement element;
	

 @BeforeClass
     public void openBrowser() throws InterruptedException{
	 //String service = "C:\\.selenium\\iedriver\\IEDriverServer.exe";
	 //System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
	 System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
	 //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
     //System.setProperty("webdriver.chrome.driver", "/Users/Example/Driver/chromedriver");
	 driver.manage().window().maximize();	 
     driver.manage().deleteAllCookies();
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
         
         
         //driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
         driver.get("http://google.com"); 
     
       //Store the current window handle
         driver.getWindowHandle();
         //Perform the click operation that opens new window
         Thread.sleep(7000);
         //Switch to new window opened
         for(String winHandle : driver.getWindowHandles()){
        	 try {
             driver.switchTo().window(winHandle);
             System.out.println("Parent Window Title is " + driver.getTitle()); 
        	 }catch (Exception e){
        		 System.out.println("Unable to switch Child Window/Popup");
    			 driver.quit();
            	throw(e); 
        	 }
         }
 
 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
 Utilities.capturescreenshot(driver, "ededed");   
 	  driver.findElement(By.id("//q")).clear();
	  driver.findElement(By.id("//q")).click()
      driver.findElement(By.id("//q")).sendKeys("search something");      
      try{
  	  System.out.println("Assertion **************");
  	  element = driver.findElement(By.text("google"));
    }catch (Exception e){
  	  System.out.println("Login  HATAAAAAA **************");
  	  Utilities.capturescreenshot(driver, "_SearchFail_");
  	  throw(e);
    } 
         
 } 

 @Test(priority=1)
public void searchPage() throws InterruptedException 
     {
	Xls_Reader reader = new Xls_Reader("C:\\eclipse-workspace\\testData\\TestData.xlsx");

	int rowCount = reader.getRowCount("Data");
	
	
	for(int rowNum = 2; rowNum<=rowCount; rowNum++) {
			
	System.out.println("Case"+rowNum+"START"); 
	    String name = reader.getCellData("Data", "username", rowNum);
	    System.out.println(name);
	    String surname = reader.getCellData("Data", "password", rowNum);
	    System.out.println(surname);	
	    String eded = reader.getCellData("Data", "eded", rowNum);
	    System.out.println(eded);	
	    String wsws = reader.getCellData("Data", "wsws", rowNum);
	    System.out.println(wsws);	
	       
	driver.findElement(By.xpath("//DIV[@id='search']/DIV[2]/INPUT")).click();		
	driver.findElement(By.xpath("//DIV[@id='search']/DIV[2]/INPUT")).sendKeys(username);
	Thread.sleep(1000);
	element.sendKeys(Keys.ENTER);
	
	Utilities.waitForAjaxToFinish(driver, 120);	
	Boolean pageLoadMus = Utilities.waitPageLoad(driver, 120);
	if(pageLoadMus = true) {
	
	//*********Uyarı varmı kontrolü************	
	try {			
	//Utilities.capturescreenshot(driver, "Case"+rowNum+"POPUP");
	Utilities.waitVisible(driver,driver.findElement(By.xpath("//DIV[@id='q'DIV[3]/DIV")));
	driver.findElement(By.xpath("//DIV[@id='q']/DIV[1]//BUTTON")).click();	
	System.out.println("Case"+rowNum+"POP UP Kliklendi");
	}catch(Exception e) {
	System.out.println("Case"+rowNum+"Uyarı Pop UpGelmedi");
	//Utilities.capturescreenshot(driver, "Case"+rowNum+"POPUP_GELMEDI");
	}
	}else {
		System.out.println("Case"+rowNum+"Search not Pageload");
	}
	 
	//******** Search Page ******************
	WebElement iframe1 = driver.findElement(By.id("rdrd"));
	driver.switchTo().frame(iframe1);
	System.out.println("Case"+rowNum+"1_SWICH OLDU");	
	try {		
		System.out.println("Case"+rowNum+"wait search");
		Utilities.waitVisible(driver,driver.findElement(By.xpath("//[1]")));
		Utilities.capturescreenshot(driver, "Case"+rowNum+"name");		
	}catch(Exception e) {
		System.out.println("Case"+rowNum+"Search Page not Load");
		Utilities.capturescreenshot(driver, "Case"+rowNum+"name");
		}
	driver.switchTo().defaultContent();	
	Utilities.waitClickable(driver, By.id("dedele"));
	System.out.println("Case"+rowNum+"SEARCH");
		try {
			driver.findElement(By.id("edededggle")).click();
		}catch(Exception e) {
			System.out.println("Case"+rowNum+"Catche"+e);
			driver.findElement(By.id("toggle")).click();
		}		
	//****************
	Utilities.waitClickable(driver,By.xpath("ygygyg"));
	driver.findElement(By.xpath("uhuhuh")).click();
	Utilities.waitForAjaxToFinish(driver, 120);	
	//WebElement dedededed = Utilities.findElement(driver, By.xpath("SELECT"));
	Boolean pageLoad = Utilities.waitPageLoad(driver, 120);
	if(pageLoad = true) {
		//Switch Frame*************
		WebElement iframe = driver.findElement(By.id("gug"));
		driver.switchTo().frame(iframe);
		System.out.println("Case"+rowNum+"2_SWICH OLDU");
		Utilities.capturescreenshot(driver, "Case"+rowNum+"serachpage");
		//driver.findElement(By.xpath("//HTML/BODY/DIV/TABLE/TBODY/TR[3]/TD[2]/SELECT")).click();
		driver.findElement(By.xpath("//TBODY/TR[3]/TD[2]/SELECT/OPTION[contains(.,'"+ddd+"')]")).click();
		Utilities.waitForAjaxToFinish(driver, 120);		
		Utilities.findElement(driver, By.xpath("//HTML/BODY/D[2]/TABLE/TBODY/TR/TD[1]/INPUT")).sendKeys(search);
		//driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[/INPUT")).sendKeys("deded");
		driver.findElement(By.xpath("//HTML/TR[5]/TD[2]/INPUT")).sendKeys(name);
		Utilities.capturescreenshot(driver, "Case"+rowNum);
		driver.findElement(By.xpath("/HTML/BODY/DIV/BUTTON")).click();
		WebElement name = driver.findElement(By.xpath("ededed"));
		boolean surname = Utilities.isVisible(name, driver);
		if(name = true){
			Utilities.capturescreenshot(driver, "Case"+rowNum+"deded");
			String name = driver.findElement(By.xpath("ededed")).getText();
			System.out.println("Case"+rowNum+"****"+surname);
			reader.setCellData("Data", "name", rowNum, name);
		}else{
			Utilities.capturescreenshot(driver, "Case"+rowNum+"notsearch");
			System.out.println("Case"+rowNum+"not search");
			}
	}else {
		System.out.println("Case"+rowNum+"PageLoad Olmadı*******Search Page not Load");
		Utilities.capturescreenshot(driver, "Case"+rowNum+"SearchpagenotLoad");
	}		
	//Switch Frame Back**********	
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//DIV[@id='uhuhu']/DIV[20]/DIV/DIV[2]")).click();		
	}
 }
//@AfterClass
// public void closeBrowser(){
//	Utilities.capturescreenshot(driver, "TestFinish");
// driver.quit();
// }

}
