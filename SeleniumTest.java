
package NARLogin;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import Utility.Log;
import Utility.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType; yeni versiyon da kullanýlan
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

 
public class SeleniumTest {
	
	 public static WebDriver driver = new InternetExplorerDriver();
	 WebElement element;
	 public static XSSFWorkbook workbook;
	 public static XSSFSheet sheet;
	 public static XSSFCell cell;
	 public static File src;
	  
 @BeforeClass
     public void openBrowser() throws InterruptedException{
	 //String service = "C:\\.selenium\\iedriver\\IEDriverServer.exe";
	 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
	 System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
	 //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
     //System.setProperty("webdriver.chrome.driver", "/Users/Example/Driver/chromedriver");
	 driver.manage().window().maximize();
     driver.manage().deleteAllCookies();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
         //driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
         driver.get("http://eadesktopuat.gm.isbank.com.tr/Advisor/advisor.htm"); 
     
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
 } 
 
 @Test(priority=0)
     public void Login(){
 
 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
 Utilities.capturescreenshot(driver, "NARLogin");   
 	  driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[1]/INPUT")).clear();
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[1]/INPUT")).sendKeys("");
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[2]/INPUT")).sendKeys("");
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[4]/INPUT")).click();
      try{
    	  System.out.println("Beklenen Element Gelecekmi?????? **************");
 element = driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV/DIV[2]/INPUT"));
 }catch (Exception e){
 }
      AssertJUnit.assertNotNull(element);
      Log.info("logggggg denemeeeeee deedededededededed");
      System.out.println("Beklenen Element Geldi **************");      
         
     }
 
 
@Test(priority=1)public void subeSecim()
     {
	System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	Utilities.capturescreenshot(driver, "subeSecim");
	 WebElement sube = driver.findElement(By.id("sel1"));	 
	 driver.findElement(By.xpath("//select[@id='sel1']//option[contains(.,'4299')]")).click();
	 driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV/DIV[2]/INPUT")).click();   
      try{
    	  System.out.println("Login oldu Ana sayfa gelecekmi????? *********************");
 element = driver.findElement(By.xpath("//DIV[@id='dip-scv-search']/DIV[2]/INPUT"));
      }catch (Exception e){
 }
      AssertJUnit.assertNotNull(element);
      System.out.println( "Login oldu Ana sayfa geldi *********************");
      Utilities.capturescreenshot(driver, "NARAnasayfa");
     }


@AfterClass
 public void closeBrowser(){
	Utilities.capturescreenshot(driver, "TestBitis");
 driver.quit();
 }

}