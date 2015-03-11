package com.gw.account.httptest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.xml.sax.SAXException;

import com.atopcloud.util.MyCurrentTime;
import com.atopcloud.util.MyDatabaseUtil;
import com.atopcloud.util.MyRedisUtil;
import com.atopcloud.util.MyUid;
import com.atopcloud.util.MyBdbUtil;
import com.atopcloud.util.MyCheckBdb;



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

public class UpdpassTest {
	
	//下面的变量设置缺省值
	private String method = "adduser";            //=adduser
	private String uname = "lidbv3";              //string 50 字节是不少于4 个字节的中英文字符（非法字符定义详细↗ ）
	private String upass = "123456";              //string;50 字节是密码明文或MD5 加密串（HTTP 接口需填为MD5 加密串）
	private String mobile = "20150212173701";        //string;15 字节否验证过的手机号
	private String email = "1918880552@qq.com";   //string;50 字节否验证过的邮箱
	private String vname = "Hello是你好";           // string;50 字节否昵称（nickname）
	private String appid = "test001";             //10 字节否默认不填，除非明确告知需填入分配的appid
	private String sql;
	private String rediskey;
	private String curtimeuname = MyCurrentTime.MyTime(); //使每次传递用户名不重复 ，不用初始数据库

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	//Case1:必填字段全部正确书写请求
	public void testMustParamsseq() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		System.out.println("======Case1:必填字段全部正确书写请求=======");		
		String accresult = AccInterface.testUpdpass("&uname=lidb072020&upass=111111");	
		assertTrue("True",accresult.contains("result=0"));
						
	}
	
	@Test
	//Case2:非必填字段全部正确书写请求mobile(uname取唯一key)
	public void testAllParams() throws IOException,SAXException, ClassNotFoundException, InterruptedException{		
		System.out.println("======Case2:非必填字段全部正确书写请求mobile(uname取唯一key)=======");		
		String accresult = AccInterface.testUpdpass("&uname=10226104452&upass=111111&opass=111111&keytp=mobile");	
		assertTrue("True",accresult.contains("result=0"));							
	}
	
	@Test
	//Case3:非必填字段全部正确书写请求email(uname取唯一key)
	public void testEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{		
		System.out.println("======Case3:非必填字段全部正确书写请求email(uname取唯一key)=======");		
		String accresult = AccInterface.testUpdpass("&uname=10302163214@qq.com&upass=111111&opass=111111&keytp=email");	
		assertTrue("True",accresult.contains("result=0"));							
	}
	
	@Test
	//Case4:中文用户名密码修改
	public void testChnUname() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case4:中文用户名密码修改=======");
		String accresult = AccInterface.testUpdpass("&uname=你好&upass=111111");	
		assertTrue("True",accresult.contains("result=0"));															
	}
		
		
	
	@Test
	//Case5:缺失用户名修改
	public void testDefectUname() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case5:缺失用户名修改=======");
		String accresult = AccInterface.testUpdpass("&upass=123456&opass=111111&keytp=mobile");	
		assertTrue("True",accresult.contains("result=101"));
														
	}
	
	@Test
	//Case6:缺失新密码修改
	public void testDefectUpass() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case6:缺失新密码修改=======");
		String accresult = AccInterface.testUpdpass("&uname=lidb020080&opass=123456&keytp=mobile");	
		assertTrue("True",accresult.contains("result=101"));														
	}
	
	@Test
	//Case7:缺失Keytp修改
	public void testDefectKeytp() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case7:缺失新密码修改=======");
		String accresult = AccInterface.testUpdpass("uname=15901620429&upass=123456&opass=111111");	
		assertTrue("True",accresult.contains("result=101"));														
	}
	
	@Test
	//Case8:缺失key字段时
	public void testDefectkey() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case8:缺失key字段时=======");
		String accresult = AccInterface.testUpdpass("&upass=111111&opass=123456&keytp=email");	
		assertTrue("True",accresult.contains("result=101"));		
												
	}
	
	
	@Test
	//Case9:修改成相同的密码
	public void testSamepass() throws IOException,SAXException, InterruptedException{
		System.out.println("======Case9:修改成相同的密码=======");
		String accresult = AccInterface.testUpdpass("&uname=lidb834792&upass=123456");	
		assertTrue("True",accresult.contains("result=1"));															
	}
	
	
	@Test
	//Case10:修改后密码51字节
	public void testpass51chars () throws IOException,SAXException, InterruptedException{
		System.out.println("======Case10:修改后密码51字节=======");
		String accresult = AccInterface.testUpdpass("&uname=lidb072004&upass=lidb50000000000000000000000000000000000000000000001");	
		assertTrue("True",accresult.contains("result=59"));															
	}
	
	
	@Test
	//Case11:字段名错误修改
	public void testErrorParams () throws IOException,SAXException, InterruptedException{
		System.out.println("======Case11:字段名错误注册=======");
		String accresult = AccInterface.testUpdpass("&un=lidb072004&upass=111111");	
		assertTrue("True",accresult.contains("result=101"));															
	}
	
	@Test
	
	//Case12:原始密码错误修改
	public void testErrorupass() throws IOException,SAXException, ClassNotFoundException, InterruptedException{		
		System.out.println("======Case2:非必填字段全部正确书写请求mobile(uname取唯一key)=======");		
		String accresult = AccInterface.testUpdpass("&uname=10226104452&upass=111111&opass=123456&keytp=mobile");	
		assertTrue("True",accresult.contains("result=0"));							
	}
	
	//Case13:不存在的用户名修改
	public void testNotuname() throws IOException,SAXException, ClassNotFoundException, InterruptedException{		
		System.out.println("======Case13:不存在的用户名修改=======");		
		String accresult = AccInterface.testUpdpass("&uname=12testtt&upass=111111&opass=123456");	
		assertTrue("True",accresult.contains("result=0"));							
	}	
	
}
