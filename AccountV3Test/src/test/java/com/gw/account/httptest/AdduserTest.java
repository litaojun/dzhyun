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

public class AdduserTest {
	
	//下面的变量设置缺省值
	private String method = "adduser";//=adduser
	private String uname = "lidbv3";//string 50 字节是不少于4 个字节的中英文字符（非法字符定义详细↗ ）
	private String ringupass;//string;//50 字节是密码明文或MD5 加密串（HTTP 接口需填为MD5 加密串）
	private String mobile;//string;//15 字节否验证过的手机号
	private String email;// string;//50 字节否验证过的邮箱
	private String vname;// string;//50 字节否昵称（nickname）
	private String appid;//10 字节否默认不填，除非明确告知需填入分配的appid
	private String sql;
	private String rediskey;
	private String curtimeuname = MyCurrentTime.MyTime(); //使每次传递用户名不重复 ，不用初始数据库

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	@Test
	//正常添加一个用户
	public void testAdduser() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case1:正常注册用户", "johnnysharp", "123qwe");
		assertTrue("True",accresult.contains("result=0"));	
	}
	
	@Test
	//空用户名空密码
	public void testNulluname() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case2:空用户名空密码", "", "");
		assertEquals("-101",accresult);	
	}
	@Test
	//用户名超过29位
	public void testUnamegt29() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case3:用户名超过29位", "abcdefghijklmnopqrstuvwxyzabcdefg", "123qwe");
		assertEquals("-101",accresult);
	}
	@Test
	//用户名超过29位
	public void testUnameeq29() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case4:用户名等于29位", "abcdefghijklmnopqrstuvwxyzabc", "123qwe");
		assertTrue("True",accresult.contains("result=0"));
	}
	@Test
	//用户名超过28位
	public void testUnameeq28() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case5:用户名等于28位", "abcdefghijklmnopqrstuvwxyzab", "123qwe");
		assertTrue("True",accresult.contains("result=0"));
	}
	@Test
	//用户名是布尔类型,True或者False
	public void testUnameboolean() throws IOException,SAXException{
		Boolean ut = true;
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case6:用户名等于28位", "ut", "123qwe");
		assertEquals("-101",accresult);
	}
	@Test
	//密码为空
	public void testUpassnull() throws IOException,SAXException{
		String accresult = com.dzh.crm.servlet.AccInterface.testAdduser("Case7:密码为空", "ajohnny", "");
		assertEquals("-102",accresult);
	}
	
*/

	@Test
	//只传用户名密码正常测试
	public void testUnameupass() throws IOException,SAXException, ClassNotFoundException, SQLException{
		
		String accresult = AccInterface.testAdduser("&uname=lidb"+curtimeuname+"&upass=123qwe");					
		int accmysql = MyDatabaseUtil.dosureQuerySql("SELECT * FROM T_MOBILE_BIND_INFO");
		System.out.println(accmysql);			
		assertTrue("True",accresult.contains("result=0"));
		MyRedisUtil accre = new MyRedisUtil();
		String accredis = accre.getValue("GwInetActApp:RECIEVE_CODE:2:ZVQBJY");	
		System.out.print(accredis);
		
	}
	
/*
	public void testEmail() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("&uname=johnny4&upass=123qwe");				
		try {
			int accmysql = MyDatabaseUtil.doQuerySql("10.15.201.24", "3306", "root", "", "INETACT", "SELECT * FROM T_MOBILE_BIND_INFO");
			System.out.print(accmysql);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertTrue("True",accresult.contains("result=0"));
		MyRedisUtil accre = new MyRedisUtil();
		String accredis = accre.getValue("GwInetActApp:RECIEVE_CODE:2:ZVQBJY");		
		System.out.print(accredis);
		
	}
*/	
/*
	@Test
	public void testEmail() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("正常登录","johnny","123qwe","");
		assertTrue("True",accresult.contains("result=0"));
		System.out.print(accresult);
	}
*/	
	/*
	@Test
	public void testEmail2() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("","johnny","123qwe","%26email%3Djohnny%40sohu.com");
		assertTrue("True",accresult.contains("result=0"));
		System.out.print(accresult);
	}
	*/
	/*
	@Test
	public void testEmail() throws IOException,SAXException{
		WebConversation web = new WebConversation();
		String url = "http://10.15.108.114:9001/AccService/AccServlet.do?method=adduser&uname=bjohnny&upass=123qwe&email=soh@sohu.com";
		GetMethodWebRequest get = new GetMethodWebRequest(url);
		WebResponse response = web.getResponse(get);
		System.out.print(response.getText());
		assertTrue("True",response.getText().contains("result=0"));
		
	}
	*/
}
