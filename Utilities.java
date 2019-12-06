package Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;


public class Utilities {

public static void capturescreenshot(WebDriver driver , String screenshotName)
{
	try 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File source=ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		FileUtils.copyFile(source, new File("C:\\Users\\ta45490\\eclipse-workspace\\IABLogin\\screenshots\\"+screenshotName+timeStamp+".png"));
		
		
		System.out.println("Screenshot taken");
	}
	catch (Exception e)
	{
		System.out.println("Exception while taking screenshot"+e.getMessage());
	}
}
public static WebElement waitVisible(WebDriver driver, WebElement element){
	try { 
	 WebDriverWait wait = new WebDriverWait(driver, 20);
    //wait.until(ExpectedConditions.elementToBeClickable(element));
    wait.until(ExpectedConditions.visibilityOf(element));
    System.out.println("*****Find Element Visible*****"+element);    
	}
	catch (Exception e) {
		System.out.println("*****Element Visible Deðil*****"+element);
	}
	return element;
	
	}

public static void waitClickable(WebDriver driver, By by){
	try { 
	 WebDriverWait wait = new WebDriverWait(driver, 20);
    //wait.until(ExpectedConditions.elementToBeClickable(element));  
    wait.until(ExpectedConditions.elementToBeClickable(by));
    System.out.println("*****Find Element Clickable*****"+by);    
	}
	catch (Exception e) {
		System.out.println("*****Element Clickable Deðil*****"+by);
	}
	
	}

public static boolean isVisible(WebElement el, WebDriver driver) 
{
    try{
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.visibilityOf(el));
        System.out.println("*****Find Element Visible*****"+el);
        return true;
    }
    catch (Exception e){
        return false;
    }
}
public static WebElement findElement(WebDriver driver, By elem) {
	WebElement element;
	try {
      element = driver.findElement(elem);
      if(element.isEnabled()) {
      return element;
      }
      else {
    	  System.out.println("*****Element Not Enabled*****"); 
      }
    }catch(NoSuchElementException e) {
        
    }
	return null;
}

public static boolean isClickable(WebElement el, WebDriver driver) 
{
    try{
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(el));
        System.out.println("*****Find Element Clickable*****"+el);
        return true;
    }
    catch (Exception e){
        return false;
    }
}

}