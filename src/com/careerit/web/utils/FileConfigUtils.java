package com.careerit.web.utils;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class FileConfigUtils {
	
	
	public static Properties initialize(String filePath)  {
		SortedProperties testdataConfig = new SortedProperties();
		try {
			testdataConfig.load(new FileInputStream(filePath));
		} 
		catch (Exception e) {System.out.println("script exception"+e);}
		return testdataConfig;
	}
	public static String readConfigFile(String key,String filePath) {
		String propertyValue=null;
		try{

			Properties testdataConfig=initialize(filePath);

			propertyValue = testdataConfig.getProperty(key);
		}
		catch (Exception e) {System.out.println("script exception"+e);}
		return propertyValue; 
	}

}

@SuppressWarnings("serial")
class SortedProperties extends Properties {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration keys() {
		Enumeration keysEnum = super.keys();
		Vector<String> keyList = new Vector<String>();
		while(keysEnum.hasMoreElements()){
			keyList.add((String)keysEnum.nextElement());
		}
		Collections.sort(keyList);
		return keyList.elements();
	}


}