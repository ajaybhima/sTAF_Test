package com.careerit.web.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGRunUtils {

	public static String startTime;
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public static void main(String[] args) throws IOException
	{
		try{
			
			
			List testGroupList = new ArrayList();
			List testPackageList = new ArrayList();
			XmlSuite suite = new XmlSuite();
			suite.setName("CAREER IT SELENIUM TEST");
			suite.setParallel("methods");
			String threadCount=FileConfigUtils.readConfigFile("TestNG_Thread_Count", "./test.properties");
			suite.setThreadCount(Integer.parseInt(threadCount));
			// suite.setDataProviderThreadCount(TestProperties.DATA_PROVIDER_THREAD_COUNT);
			XmlTest test = new XmlTest(suite);
			test.setJunit(false);
			test.setName("CAREER IT SELENIUM AUTOMATION");
			String groups=FileConfigUtils.readConfigFile("TestNG_Test_Groups", "./test.properties");
			//testGroupList.add(groups);
			String[] groupArray=groups.split(",");
			testGroupList.addAll(Arrays.asList(groupArray));

			test.setIncludedGroups(testGroupList);
			String packages=FileConfigUtils.readConfigFile("TestNG_Package_Name", "./test.properties");
			testPackageList.add(new XmlPackage(packages));
			//  testPackageList.addAll(Arrays.asList(packages.split(",")));
			test.setPackages(testPackageList);
			String buildName=".";

			List suites = new ArrayList();
			suites.add(suite);
			TestNG tng = new TestNG();

			tng.setOutputDirectory((new StringBuilder()).append(buildName).append("/build/test-output").toString());
			tng.setXmlSuites(suites);
			//tng.addListener(new com.ulta.ecom.utils.ScreenshotUtils());

			tng.run();

			FileWriter fstream = new FileWriter((new StringBuilder()).append(buildName).append("/build/test-output").append("/run.xml").toString());
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(suite.toXml());
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("");
					
			
		}
		

	}
	public static void reportLog(String message){
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
		Calendar cal = Calendar.getInstance();
		String Timestamp = dateFormat.format(cal.getTime());
		message =Timestamp+" : "+message;
		Reporter.log(message);																	
	}

}
