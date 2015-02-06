package com.gw.account.httptest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;


//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UsergetTest {
	
	@Before
	public void setUp() throws Exception {
		//System.out.println("setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//System.out.println("teardown");
	}


	@Test
	//获取一个不存在用户的信息
	public void testNotExistUname() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("Case2:获取一个不存在用户信息", "johnnyoo");
		assertTrue("True",accresult.contains("result=2"));
	}
	
	@Test
	//空用户名
	public void testNullUname() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("Case3:获取空用户名用户信息", "");
		assertEquals("-101",accresult);
	}
	
	@Test
	//用户名长度超过29位
	public void testUnamegt29() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("Case4:用户名长度超过29位", "abcdefghijklmnopqrstuvwxyzabcdefg");
		assertEquals("-101",accresult);
	}
	
	@Test
	//用户名长度28位
	public void testUnameeq28() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("Case4:用户名长度等于28位", "abcdefghijklmnopqrstuvwxyzabcdefg");
		assertTrue("True",accresult.contains("result=2"));	
	}
	@Test
	//用户名长度29位
	public void testUnameeq29() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("Case5:用户名长度等于28位", "abcdefghijklmnopqrstuvwxyzabcdefgh");
		assertTrue("True",accresult.contains("result=2"));	
		//System.out.printf("test: %s", accresult);
	}

	@Test
	//正常获取一个用户信息
	public void testNormalUserget() throws IOException,SAXException {
		String accresult = AccInterface.testUserget("&uname=johnny");
		assertTrue("True",accresult.contains("result=0"));	
	}
}
