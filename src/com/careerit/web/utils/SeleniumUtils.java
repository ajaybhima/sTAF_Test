package com.careerit.web.utils;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	
	static HashMap<String, Object> hashMap = new HashMap<String, Object>();
	
	public static void waitForElement(WebDriver driver,ElementLocator elementDetails){
			try{
				
				WebDriverWait wait = new WebDriverWait(driver,30);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementDetails.getelementValue())));
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementDetails.getelementValue())));
				//WebElement element=driver.findElement(By.xpath(elementDetails.getelementValue()));
				//wait.until(ExpectedConditions.visibilityOf((element)));
			}
			catch(Exception e){
					throw new RuntimeException(elementDetails.getelementName()+" not found in the page.");
			}
	}
	
	public static void waitForElementToBeClickable(WebDriver driver,ElementLocator elementDetails){
		WebElement element;	
			try{
				WebDriverWait wait = new WebDriverWait(driver,30);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementDetails.getelementValue())));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementDetails.getelementValue())));
				element=driver.findElement(By.xpath(elementDetails.getelementValue()));
				wait.until(ExpectedConditions.visibilityOf((element)));
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementDetails.getelementValue())));
			}
			catch(Exception e){
					throw new RuntimeException(elementDetails.getelementName()+" not found in the page.");
			}
	}
	public static boolean isVisible(WebDriver driver,ElementLocator elementDetails){
			boolean flagValue=false;
			for(int x=0;x<3;x++){
				try{
					WebDriverWait wait = new WebDriverWait(driver,5);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementDetails.getelementValue())));
					WebElement element=driver.findElement(By.xpath(elementDetails.getelementValue()));
					wait.until(ExpectedConditions.visibilityOf((element)));
					flagValue=true;
					break;
				}
				catch(Exception e){
					
				}
			}
			return flagValue;
	}
	public static void mouseHover(WebDriver driver,ElementLocator elementDetails){
		SeleniumUtils.waitForElement(driver, elementDetails);
		try {
			By by_element_1 = By.xpath(elementDetails.getelementValue());
			Actions action = new Actions(driver);
			WebElement element_1 = driver.findElement(by_element_1);
			action.moveToElement(element_1).build().perform();
		} catch (Exception e) {
			throw new RuntimeException(elementDetails.getelementName()+" mouse hover failed");
		}
	}
	
	public static void storeKeyValue(String key,Object value){
		 hashMap.put(key, value);
	 }
	 public static Object retrieveKeyValue(String key){
		 Object hashValue=hashMap.get(key);
		 return hashValue;
	 }
	 
	 public static void deleteKeyValue(String key){
		 hashMap.remove(key);
	 }
	

}
