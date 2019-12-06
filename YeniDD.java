package NARLogin;

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

 
public class YeniDD {
	
	 public static WebDriver driver = new InternetExplorerDriver();
	 WebDriverWait wait = new WebDriverWait(driver, 10);
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
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
         driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
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
 
 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
 Utilities.capturescreenshot(driver, "NARLogin");   
 	  driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[1]/INPUT")).clear();
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[1]/INPUT")).sendKeys("15901");
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[2]/DIV[2]/INPUT")).sendKeys("Cc707070");
      driver.findElement(By.xpath("//DIV[@id='dip-login-formbox']/DIV[4]/INPUT")).click();
      try{
  	  System.out.println("Beklenen Element Gelecekmi?????? **************");
  	  element = driver.findElement(By.xpath("//DIV[@id='dip-scv-search']/DIV[2]/INPUT"));
    }catch (Exception e){
  	  System.out.println("Login  HATAAAAAA **************");
  	  Utilities.capturescreenshot(driver, "_LoginHata_");
    }
      //finally {
  	//  Utilities.capturescreenshot(driver, "_Anasayfa_");
  	//  AssertJUnit.assertNotNull(element);
	 //     Log.info("logggggg denemeeeeee deedededededededed");
	  //    System.out.println("Anasayfa Element Geldi **************");  
    //}  
         
 } 

 @Test(priority=1)
public void musteriAra() throws InterruptedException 
     {
	Xls_Reader reader = new Xls_Reader("C:\\Users\\ta45490\\eclipse-workspace\\NAR\\src\\testData\\TestData.xlsx");

	int rowCount = reader.getRowCount("Data");
	
	
	for(int rowNum = 2; rowNum<=rowCount; rowNum++) {
			
	System.out.println("Case"+rowNum+"BASLIYOR"); 
	    String musteriNo = reader.getCellData("Data", "MusteriNo", rowNum);
	    System.out.println(musteriNo);
	    String ParaBirimi = reader.getCellData("Data", "ParaBirimi", rowNum);
	    System.out.println(ParaBirimi);	
	    String tutar = reader.getCellData("Data", "Tutar", rowNum);
	    System.out.println(tutar);	
	    String vade = reader.getCellData("Data", "Vade", rowNum);
	    System.out.println(vade);	
	       
	driver.findElement(By.xpath("//DIV[@id='dip-scv-search']/DIV[2]/INPUT")).click();		
	driver.findElement(By.xpath("//DIV[@id='dip-scv-search']/DIV[2]/INPUT")).sendKeys(musteriNo);
	element.sendKeys(Keys.ENTER);
	
	//*********Uyarý varmý kontrolü************
	try {
	WebDriverWait wait = new WebDriverWait(driver, 25);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@id='dip-scv-box']/DIV[1]/APP-SCV-INDIVIDUAL/DIV[3]/DIV")));		
	//Utilities.capturescreenshot(driver, "Case"+rowNum+"POPUP");
	driver.findElement(By.xpath("//DIV[@id='dip-scv-box']/DIV[1]/APP-SCV-INDIVIDUAL/DIV[3]/DIV/DIV/DIV[1]/BUTTON")).click();	
	System.out.println("Case"+rowNum+"POP UP Kliklendi");
	}catch(Exception e) {
	System.out.println("Case"+rowNum+"Uyarý Pop UpGelmedi");
	//Utilities.capturescreenshot(driver, "Case"+rowNum+"POPUP_GELMEDI");
	}
	 
	//******** Menü Klikleme/Ekraný Seçme ******************
	WebElement iframe1 = driver.findElement(By.id("prtfoy"));
	driver.switchTo().frame(iframe1);
	System.out.println("Case"+rowNum+"1_SWICH OLDU");	
	try {		
		System.out.println("Case"+rowNum+"Müþteri Bilgileri Bekleniyor");
		Utilities.isVisible(driver.findElement(By.xpath("//DIV[@id='nar-page-content']/DIV/DIV/DIV/DIV/DIV[1]/TABLE/TBODY/TR/TD[1]")), driver);
		Utilities.capturescreenshot(driver, "Case"+rowNum+"MUSBILGILERI");		
	}catch(Exception e) {
		System.out.println("Case"+rowNum+"Müþteri Bilgileri Gelmedi");
		Utilities.capturescreenshot(driver, "Case"+rowNum+"MUSBILGELMEDI");
		}
	driver.switchTo().defaultContent();	
	driver.findElement(By.xpath("//DIV[@id='dip-scvbar']/DIV/DIV/DIV[1]")).click();
	System.out.println("Case"+rowNum+"MENU KLIK");	
	//EKRAN SEÇÝMÝ****************
	try {
	driver.findElement(By.xpath("//DIV[@id='dip-megamenu']/DIV/DIV/APP-FAVORITE/DIV/UL/LI/A[contains(.,'Mevduat Hesapla')]")).click();
	}catch(Exception e) {
		System.out.println("Case"+rowNum+"Menu Açýlmadý Ekran KLIKLENEMEDI "+e);
		//Utilities.capturescreenshot(driver, "Case"+rowNum+"MENUACILMADI");
		driver.findElement(By.xpath("//DIV[@id='dip-scvbar']/DIV/DIV/DIV[1]")).click();
		driver.findElement(By.xpath("//DIV[@id='dip-megamenu']/DIV/DIV/APP-FAVORITE/DIV/UL/LI/A[contains(.,'Mevduat Hesapla')]")).click();
	}
	//Switch Frame*************
	WebElement iframe = driver.findElement(By.id("dip_tab_iframe_19"));
	driver.switchTo().frame(iframe);
	System.out.println("Case"+rowNum+"2_SWICH OLDU");

	boolean hesEkran = Utilities.isVisible(driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[3]/TD[2]/SELECT")), driver);
	if(hesEkran = true) {
		Utilities.capturescreenshot(driver, "Case"+rowNum+"HESAPLAMAEKRANI");
		driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[3]/TD[2]/SELECT")).click();
		driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[3]/TD[2]/SELECT/OPTION[contains(.,'"+ParaBirimi+"')]")).click();
		Thread.sleep(2000);
		Utilities.findElement(driver, By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[4]/TD[2]/TABLE/TBODY/TR/TD[1]/INPUT")).sendKeys(tutar);
		//driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[4]/TD[2]/TABLE/TBODY/TR/TD[1]/INPUT")).sendKeys("150000");
		driver.findElement(By.xpath("//HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[3]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[5]/TD[2]/INPUT")).sendKeys(vade);
		Utilities.capturescreenshot(driver, "Case"+rowNum);
		driver.findElement(By.xpath("/HTML/BODY/DIV/DIV/TABLE[1]/TBODY/TR[4]/TD/TABLE/TBODY/TR/TD/DIV/BUTTON")).click();
		WebElement FOran = driver.findElement(By.xpath("//HTML/BODY/DIV[1]/DIV/TABLE[1]/TBODY/TR[5]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[1]/TD[3]/SPAN[@class='gwt-InlineLabel']"));
		boolean faizOran = Utilities.isVisible(FOran, driver);
		if(faizOran = true){
			Utilities.capturescreenshot(driver, "Case"+rowNum+"ORANI");
			String Oran = driver.findElement(By.xpath("//HTML/BODY/DIV[1]/DIV/TABLE[1]/TBODY/TR[5]/TD/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[1]/TD[3]/SPAN[@class='gwt-InlineLabel']")).getText();
			System.out.println("Case"+rowNum+"****"+Oran);
			reader.setCellData("Data", "FaizOran", rowNum, Oran);
		}else{
			Utilities.capturescreenshot(driver, "Case"+rowNum+"ORANGELMEDI");
			System.out.println("Case"+rowNum+"Oran Gelmedi");
			}
	}else {
		System.out.println("Case"+rowNum+"HESAPLAMA Ekraný Gelmedi");
		Utilities.capturescreenshot(driver, "Case"+rowNum+"HESAPLAMAEKRANI_GELMEDI");
	}		
	//Switch Frame Back**********	
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//DIV[@id='dip-nartab']/DIV[20]/DIV/DIV[2]")).click();		
	}
 }
//@AfterClass
// public void closeBrowser(){
//	Utilities.capturescreenshot(driver, "TestBitis");
// driver.quit();
// }

}