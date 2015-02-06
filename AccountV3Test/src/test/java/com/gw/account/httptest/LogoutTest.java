package com.gw.account.httptest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

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

public class LogoutTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//空用户名
	public void testUnamenull() throws IOException,SAXException{
         String accresult = AccInterface.testLogout("Case1:空用户名退出", "");
         assertEquals("-101",accresult);
	}
	@Test 
	//测试登出一个用户
	public void testLogout() throws IOException,SAXException{
		String accresult = AccInterface.testLogout("Case2:登出一个用户", "johnny");
		assertTrue("True",accresult.contains("result=0"));
	}
    @Test
    //用户名不存在
    public void testUnameNotExist() throws IOException,SAXException{
    	String accresult = AccInterface.testLogout("Case3:用户名不存在", "notexist");
    	assertTrue("True",accresult.contains("result=2"));
    }
    @Test
    public void testUnamegt29() throws IOException,SAXException{
    	String accresult = AccInterface.testLogout("Case4:用户名超过29位","abcdefghijklmnopqrstuvwxyz1234");
    	assertEquals("-101","accresult");
    }
}
