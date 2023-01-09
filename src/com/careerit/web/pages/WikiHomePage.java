package com.careerit.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.careerit.web.utils.ElementLocator;
import com.careerit.web.utils.SeleniumUtils;
import com.careerit.web.utils.TestNGRunUtils;

public class WikiHomePage {
	
	ElementLocator searchBox= new ElementLocator("searck-box", "//input[@id='searchInput']", ElementLocator.XPATH);
	ElementLocator langDropdown= new ElementLocator("Language-dropdown", "//div[@id='search-input']/div/div/select[@id='searchLanguage']", ElementLocator.XPATH);
	ElementLocator searchButton= new ElementLocator("searck-Button", "//button[@class='pure-button pure-button-primary-progressive']", ElementLocator.XPATH);
	ElementLocator searchButton2= new ElementLocator("searck-Button", "//button[@class='pure-button pure-button-primary-progressive']", ElementLocator.XPATH);
	
	

	private WebDriver driver;
	
	public WikiHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WikiHomePage enterSearchKey(String searchKey){
		System.out.println("New Change");
		TestNGRunUtils.reportLog("Enter search key - "+searchKey);
		SeleniumUtils.waitForElement(driver, searchBox);
		WebElement searchInput=driver.findElement(By.xpath(searchBox.getelementValue()));
		System.out.println("Enter search key - "+searchKey);	
		searchInput.clear();
		searchInput.sendKeys(searchKey);
		searchInput.sendKeys(Keys.TAB);
		System.out.println("Search Key has beeen entered");
		return this;
	}
	public WikiHomePage selectLanguage(String lang){
		TestNGRunUtils.reportLog("Select Language - "+lang);
		SeleniumUtils.waitForElement(driver, langDropdown);
		WebElement lanSelect=driver.findElement(By.xpath(langDropdown.getelementValue()));
		Select languageDropdown= new Select(lanSelect);
		languageDropdown.selectByValue(lang);
		return this;
	}
	public WikiHomePage clickSearchButton(){
		TestNGRunUtils.reportLog("Click on Search Button");
		SeleniumUtils.waitForElement(driver, searchButton);
		WebElement searchButtonIcon=driver.findElement(By.xpath(searchButton.getelementValue()));
		searchButtonIcon.click();
		return this;
	}
	
	

}
