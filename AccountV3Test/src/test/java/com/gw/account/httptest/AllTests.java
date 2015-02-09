package com.gw.account.httptest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.atopcloud.testcasefilter.MyTestCaseFilter;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * 根据scenario/runconfig.csv文件读取测试用例。如果第一行为all，则执行所有用例，否则执行。
 * @author Administrator
 *
 */
//@RunWith(Suite.class)
//@SuiteClasses({QuoteDynaTest.class})  //,QuoteKlineTest.class  多个class都好分割
public class AllTests {
	//需要执行的testcase类及其方法。存储在map中。其中，method是key，类的class名称为value.
	private static Map<String, Class> scenario = new HashMap<String,Class>();
	
	public static Test suite() throws NoTestsRemainException, ClassNotFoundException, IOException
	{
//		System.out.println("suite");
		TestSuite suite = new TestSuite("根据runconfig.csv内容运行指定的方法！");
		JUnit4TestAdapter testAdapter;
		/*
		 * 未使用过滤的情况
		testAdapter = new JUnit4TestAdapter(QuoteDynaTest.class);
		suite.addTest(testAdapter);
		return suite;
		*/
		
		/*产生TestSuite（使用过滤的情况）*/
		if(getScenario())
		{
			for(Map.Entry<String, Class> entry : scenario.entrySet())	
			{
				Class theClass=entry.getValue();
				testAdapter = new JUnit4TestAdapter(theClass);
				
				String method = entry.getKey();  //method为key
				testAdapter.filter(new MyTestCaseFilter(method));
				
				suite.addTest(testAdapter);
			}
			return suite;
		}
		else
			return null;
	}
	
	/**
	 * 读取scenario/runconfig.csv,<class,method>写入info
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static boolean getScenario() throws IOException, ClassNotFoundException
	{
//		System.out.println("getScenario");
		String runConfig = System.getProperty("user.dir")+"/config/scenario.csv"; //使用“/”，win与linux通用 。
//		System.out.println(runConfig);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(runConfig),"utf-8"));
		String line=null;
		String method=null;
		String classname=null;
		Class theClass=null;
		int counter=0;
		while((line = br.readLine()) != null)
		{
			++counter;
			line = line.trim();
			if((counter == 1) && (!line.equalsIgnoreCase("all")))  //第一行不是all，则不运行测试
			{
				br.close();
				return false;
			}
			else
			{ 
				if( counter > 1)  //从第二行开始读 
				{
				classname = line.split(":")[0].trim();
//				System.out.println(classname);
				theClass=Class.forName(classname);
				
				method =line.split(":")[1].trim();
//				System.out.println(method);
				
				scenario.put(method,theClass);
				}
			}
		}
		br.close();
		return true;
	}
}
