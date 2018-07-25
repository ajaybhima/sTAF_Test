package com.careerit.web.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class TestInitializer {
	
	public DesiredCapabilities internetExploreCapability(){
		DesiredCapabilities ieCapability=null;
		ieCapability=DesiredCapabilities.internetExplorer();
		ieCapability.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		ieCapability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieCapability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCapability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		ieCapability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
		ieCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return ieCapability;
	}
	
	public DesiredCapabilities firefoxCapability(String baseSite){
		DesiredCapabilities firefoxCapability=null;
		firefoxCapability=DesiredCapabilities.firefox();
		return firefoxCapability;
	}
	public DesiredCapabilities chromeCapability(String baseSite){
		DesiredCapabilities chromeCapability=null;
		chromeCapability=DesiredCapabilities.chrome();
		return chromeCapability;
	}
	
	public WebDriver initializeDriver() 
	{
		WebDriver driver=null;
		for(int x=0;x<3;x++){
			try{
				DesiredCapabilities capability = null ;
				String browser=FileConfigUtils.readConfigFile("Selenium_Test_Browser", "./test.properties").toLowerCase();
				String seleniumHost=FileConfigUtils.readConfigFile("Selenium_Server_Host", "./test.properties");
				String seleniumPort=FileConfigUtils.readConfigFile("Selenium_Server_Port", "./test.properties");
				String baseSite=FileConfigUtils.readConfigFile("Selenium_Test_Url", "./test.properties");
				//String testGroup=FileConfigUtils.readConfigFile("TestNG_Test_Groups", "./test.properties");
				
				
				if(browser.contains("ie")|| browser.contains("iexplore")||browser.contains("internet")||browser.contains("iehta")){
					//capability=internetExploreCapability();
					System.setProperty("webdriver.ie.driver", "./lib/IEDriverServer.exe");
					//driver= new InternetExplorerDriver();
				}
				
				else if(browser.contains("ff")||browser.contains("firefox")||browser.contains("fire")){
					//capability=firefoxCapability(baseSite);
					System.setProperty("webdriver.gecko.driver", "./lib/geckodriver.exe");
					driver= new FirefoxDriver();
				}
				else if(browser.contains("google")||browser.contains("chrome")||browser.contains("googlechrome")){
					//capability=chromeCapability(baseSite);
					System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
					driver = new ChromeDriver();
				}
				

				//driver=new RemoteWebDriver(new URL("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),capability);

				driver.manage().window().maximize();
				
				driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(70,TimeUnit.SECONDS);
				driver.get(baseSite);
				TestNGRunUtils.reportLog("Launching - "+baseSite+" in "+browser+ " browser");
				break;

			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				try{driver.close();}
				catch(Exception e1){}
				try{driver.quit();}
				catch(Exception e1){}
				if(x==3){
					throw new RuntimeException(e.toString());
				}
			}
		}
		return driver;
	}

	
	//@AfterTest (alwaysRun=true)
	public  void closeDriver(WebDriver driver) 
	{
		try{
			try{
				driver.close();
			}catch(Exception e1){}
			driver.quit();
		}
		catch(Exception e){}
	}

	public static void onTestFailureScreenshot(WebDriver driver,String testCaseName,Exception exception) {
		System.out.println(testCaseName+" : Failed");
		
		
		String path;
		try {
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				path = "./build/test-output/screenshots/" + testCaseName+".png";
				FileUtils.copyFile(source, new File(path));
				Reporter.log(path);
			
		}
		catch(IOException e) {
			try{
				driver.close();
			}catch(Exception e1){}
			driver.quit();
			path = "Failed to capture screenshot: " + e.getMessage();
			Reporter.log(path);
		}
	}
	
	public static void onTestPass(String testCaseName){
		System.out.println(testCaseName+" : Passed");
	}
	
}
