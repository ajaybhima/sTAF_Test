package com.careerit.web.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.careerit.web.pages.WikiContentPage;
import com.careerit.web.pages.WikiHomePage;
import com.careerit.web.utils.*;

public class WikiPediaTests {
	
	@Test (groups={"SmokeTest","RegressionTest"})
	public void WikiHOmeTestCase_01() throws Exception{
		
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		
		TestInitializer startTest=new TestInitializer(); 
		WebDriver driver=startTest.initializeDriver();
		
		try {
			WikiHomePage homePage=new WikiHomePage(driver);
			String searchKey=ExcelReadWriteUtils.readFromExcel("./resources/TestDataSheet.xlsx", 1, 0);
			String languageData=ExcelReadWriteUtils.readFromExcel("./resources/TestDataSheet.xlsx", 1, 1);
			homePage.enterSearchKey(searchKey);
			homePage.selectLanguage(languageData);
			homePage.clickSearchButton();
			WikiContentPage contentPage= new WikiContentPage(driver);
			contentPage.readHeaderText();
			TestInitializer.onTestPass(testCaseName);
			ExcelReadWriteUtils.writeToExcel("./resources/TestDataSheet.xlsx", "PASS", 1, 2);
			startTest.closeDriver(driver);
			
		} catch (Exception e) {
			TestInitializer.onTestFailureScreenshot(driver, testCaseName, e);
			ExcelReadWriteUtils.writeToExcel("./resources/TestDataSheet.xlsx", "FAIL", 1, 2);
			startTest.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
		
	}
	@Test (groups={"SmokeTest"})
	public void WikiHOmeTestCase_02() throws Exception{
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		TestInitializer startTest=new TestInitializer(); 
		WebDriver driver=startTest.initializeDriver();
		
		try {
			WikiHomePage homePage=new WikiHomePage(driver);
			homePage.enterSearchKey("Cricket");
			homePage.selectLanguage("en");
			homePage.clickSearchButton();
			WikiContentPage contentPage= new WikiContentPage(driver);
			contentPage.readHeaderText2();
			TestInitializer.onTestPass(testCaseName);
			startTest.closeDriver(driver);
			
		} catch (Exception e) {
			TestInitializer.onTestFailureScreenshot(driver, testCaseName, e);
			startTest.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
		
	}
	@Test (groups={"SmokeTest"})
	public void WikiHOmeTestCase_03() throws Exception{
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		TestInitializer startTest=new TestInitializer(); 
		WebDriver driver=startTest.initializeDriver();
		
		try {
			WikiHomePage homePage=new WikiHomePage(driver);
			homePage.enterSearchKey("USA");
			homePage.selectLanguage("en");
			homePage.clickSearchButton();
			WikiContentPage contentPage= new WikiContentPage(driver);
			contentPage.readHeaderText();
			TestInitializer.onTestPass(testCaseName);
			startTest.closeDriver(driver);
			
		} catch (Exception e) {
			TestInitializer.onTestFailureScreenshot(driver, testCaseName, e);
			startTest.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
		
	}
	@Test (groups={"SmokeTest"})
	public void WikiHOmeTestCase_04() throws Exception{
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		TestInitializer startTest=new TestInitializer(); 
		WebDriver driver=startTest.initializeDriver();
		
		try {
			WikiHomePage homePage=new WikiHomePage(driver);
			homePage.enterSearchKey("Selenium");
			homePage.selectLanguage("en");
			homePage.clickSearchButton();
			WikiContentPage contentPage= new WikiContentPage(driver);
			contentPage.readHeaderText();
			TestInitializer.onTestPass(testCaseName);
			startTest.closeDriver(driver);
			
		} catch (Exception e) {
			TestInitializer.onTestFailureScreenshot(driver, testCaseName, e);
			startTest.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
		
	}
	@Test (groups={"SmokeTest"})
	public void WikiHOmeTestCase_05() throws Exception{
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		TestInitializer startTest=new TestInitializer(); 
		WebDriver driver=startTest.initializeDriver();
		
		try {
			WikiHomePage homePage=new WikiHomePage(driver);
			homePage.enterSearchKey("Hyderabad");
			homePage.selectLanguage("hi");
			homePage.clickSearchButton();
			WikiContentPage contentPage= new WikiContentPage(driver);
			contentPage.readHeaderText();
			TestInitializer.onTestPass(testCaseName);
			startTest.closeDriver(driver);
			
		} catch (Exception e) {
			TestInitializer.onTestFailureScreenshot(driver, testCaseName, e);
			startTest.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
		
	}

}
