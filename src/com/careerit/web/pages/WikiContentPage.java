package com.careerit.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.careerit.web.utils.ElementLocator;
import com.careerit.web.utils.SeleniumUtils;
import com.careerit.web.utils.TestNGRunUtils;

public class WikiContentPage {
	
	ElementLocator headerText= new ElementLocator("Header-Text", "//h1[@id='firstHeading']", ElementLocator.XPATH);
	ElementLocator headerText2= new ElementLocator("Header-Text", "//h1[@id='firstHeading-ajay']", ElementLocator.XPATH);
	
	private WebDriver driver;
	public WikiContentPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WikiContentPage readHeaderText(){
		TestNGRunUtils.reportLog("Reading Header of Wiki Content Page");
		SeleniumUtils.waitForElement(driver, headerText);
		WebElement lblHeaderText=driver.findElement(By.xpath(headerText.getelementValue()));
		String headerTxt=lblHeaderText.getText();
		TestNGRunUtils.reportLog("Header Text in Content Page - "+headerTxt);
		return this;
	}
	
	public WikiContentPage readHeaderText2(){
		TestNGRunUtils.reportLog("Reading Header of Wiki Content Page");
		SeleniumUtils.waitForElement(driver, headerText2);
		WebElement lblHeaderText=driver.findElement(By.xpath(headerText.getelementValue()));
		String headerTxt=lblHeaderText.getText();
		TestNGRunUtils.reportLog("Header Text in Content Page - "+headerTxt);
		return this;
	}

}
