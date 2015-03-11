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
	//下面的变量设置缺省值
	private String method = "adduser";//=adduser
	private String uname;//string 50 字节是不少于4 个字节的中英文字符（非法字符定义详细↗ ）
	private String ringupass;//string;//50 字节是密码明文或MD5 加密串（HTTP 接口需填为MD5 加密串）
	private String mobile;//string;//15 字节否验证过的手机号
	private String email;// string;//50 字节否验证过的邮箱
	private String vname;// string;//50 字节否昵称（nickname）
	private String appid;//10 字节否默认不填，除非明确告知需填入分配的appid
	private String sql;
	private String rediskey;
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
	////Case1:获取一个不存在用户的信息
	public void testNotExistUname() throws IOException,SAXException {
		System.out.println("======Case1:获取一个不存在用户的信息=======");	
		String accresult = AccInterface.testUserget("&uname=johnnyoo");
		assertTrue("True",accresult.contains("result=2"));
	}
	
	@Test
	////Case2:非必填字段全部正确书写请求
	public void testAllParamsnotpass() throws IOException,SAXException {
		System.out.println("======Case2:非必填字段全部正确书写请求=======");	
		String accresult = AccInterface.testUserget("&uname=lidb&gettp=12288&passmd5=123456&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=0"));
	}
	
	@Test
	////Case3:查找中文用户名
	public void testChnUname() throws IOException,SAXException {
		System.out.println("======Case3:查找中文用户名=======");	
		String accresult = AccInterface.testUserget("&uname=你好&gettp=12288&passmd5=&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=0"));
	}
	
	@Test
	////Case4:缺失用户名时查找
	public void testDefectUname() throws IOException,SAXException {
		System.out.println("======Case4:缺失用户名时查找=======");	
		String accresult = AccInterface.testUserget("&gettp=12288&passmd5=&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=101"));
		assertTrue("True",accresult.contains("uname_bad"));
	}
	
	@Test
	////Case5:缺失gettp时查找
	public void testDefectgettp() throws IOException,SAXException {
		fail("donot run this!");
		System.out.println("======Case5:缺失gettp时查找=======");	
		String accresult = AccInterface.testUserget("&uname=你好&passmd5=&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=101"));
		assertTrue("True",accresult.contains("uname_bad"));
	}
	
	@Test
	////Case6:非必填字段全部书写请求，密码错误
	public void testAllParams() throws IOException,SAXException {
		System.out.println("======Case6:非必填字段全部书写请求，密码错误=======");	
		String accresult = AccInterface.testUserget("&uname=lidb&gettp=12288&passmd5=123457&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=59"));
	}
	
	@Test
	////Case7:非必添字段缺失
	public void testDefect() throws IOException,SAXException {
		System.out.println("======Case7:非必添字段缺失=======");	
		String accresult = AccInterface.testUserget("&uname=lidb&gettp=12288");
		assertTrue("True",accresult.contains("result=0"));
	}
	
	@Test
	////Case8:gettp不为12288时查找
	public void testErrorgettp() throws IOException,SAXException {
		fail("donot run this!");
		System.out.println("======Case8:gettp不为12288时查找=======");	
		String accresult = AccInterface.testUserget("&uname=lidb&gettp=18&passmd5=123456&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=101"));
	}
	
	@Test
	////Case9:字段名错误，非必填错误
	public void testErrorpass() throws IOException,SAXException {
		System.out.println("======Case9:字段名错误，非必填错误=======");	
		String accresult = AccInterface.testUserget("&uname=lidb&pad5=123456&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=0"));
	}
	
	@Test
	////Case10:字段名错误，用户名错误
	public void testErroruname() throws IOException,SAXException {
		System.out.println("======Case10:字段名错误，用户名错误=======");	
		String accresult = AccInterface.testUserget("&ume=lidb&passmd5=123456&exreq={\"ver\":\"1.0\"}");
		assertTrue("True",accresult.contains("result=101"));
		assertTrue("True",accresult.contains("uname_bad"));
	}
	
	
	
/*

	@Test
	//获取一个不存在用户的信息
	public void testNotExistUname1() throws IOException,SAXException {
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
	
//	@Test
//	//测试uname最大长度
//	public void testAdduserMaxUnameLength() throws IOException,SAXException {
//		uname = "111111111111111111111111111111111111111111111111111";
//		String accresult = AccInterface.testUserget();
//		
//		assertTrue("True",accresult.contains("result=0"));	
//		
//	}
 * 
 */
}
