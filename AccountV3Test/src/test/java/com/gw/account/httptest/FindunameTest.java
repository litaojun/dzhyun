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

public class FindunameTest {
	
	//下面的变量设置缺省值
	private String method = "findunamebykey";            //=findunamebykey
	private String keytyp;              //email,mobile,lotterid,deviceid,idcard,qqid,lcb,wxid,xcid ）
	private String key;              //string;50 字节是密码明文或MD5 加密串（HTTP 接口需填为MD5 加密串）
	private String appid;             //10 字节否默认不填，除非明确告知需填入分配的appid
	private String sql;
	private String rediskey;
	private String curtimeuname = MyCurrentTime.MyTime(); //使每次传递用户名不重复 ，不用初始数据库
	private String params;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	

//正常参数验证
	
	@Test
	//Case1.1.1:必填字段全部正确书写请求（mobile）
	public void testMustParamsMobile() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=mobile&key=13673991650";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.1:必填字段全部正确书写请求（mobile）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	@Test
	//Case1.1.2:必填字段全部正确书写请求（email）
	public void testMustParamsEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=email&key=renguohua@gw.com.cn";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.2:必填字段全部正确书写请求（email）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	@Test
	//Case1.1.3:必填字段全部正确书写请求（idcard）
	public void testMustParamsIdcard() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=412724198412072233";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.3:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	@Test
	//Case1.1.3.1:必填字段全部正确书写请求（idcard）
	public void testMustParamsIdcard31() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=41272419841207251X";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.3.1:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	@Test
	//Case1.1.3.2:必填字段全部正确书写请求（idcard）
	public void testMustParamsIdcard32() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=41272445698745X";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.3.2:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	@Test
	//Case1.1.3.3:必填字段全部正确书写请求（idcard）
	public void testMustParamsIdcard33() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=41272419841207251x";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.3.3:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	@Test
	//Case1.1.3.4:必填字段全部正确书写请求（idcard）
	public void testMustParamsIdcard34() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=41272445698745x";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.3.4:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	@Test
	//Case1.1.4:必填字段全部正确书写请求（qqid）
	public void testMustParamsQqid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=qqid&key=564911127";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.4:必填字段全部正确书写请求（qqid）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	@Test
	//Case1.1.5:必填字段全部正确书写请求（wxid）
	public void testMustParamsWxid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=wxid&key=rain_xcy";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.5:必填字段全部正确书写请求（wxid）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	
	@Test
	//Case1.1.6:必填字段全部正确书写请求（xcid）
	public void testMustParamsXcid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=xcid&key=6543210987";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.6:必填字段全部正确书写请求（xcid）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	
// method参数大小写敏感	
	@Test
	//Case1.2.1:必填字段全部正确书写请求（mobile）
	public void testMustParamsmethodMobile() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=mobile&key=13673991650";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.1:必填字段全部正确书写请求（mobile）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	
	@Test
	//Case1.2.2:必填字段全部正确书写请求（email）
	public void testMustParamsmethodEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=email&key=renguohua@gw.com.cn";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.2:必填字段全部正确书写请求（email）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	
	@Test
	//Case1.2.3:必填字段全部正确书写请求（idcard）
	public void testMustParamsmethodIdcard() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=idcard&key=412724198412072233";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.3:必填字段全部正确书写请求（idcard）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	
	@Test
	//Case1.2.4:必填字段全部正确书写请求（qqid）
	public void testMustParamsmethodQqid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=qqid&key=564911127";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.4:必填字段全部正确书写请求（qqid）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	

	@Test
	//Case1.2.5:必填字段全部正确书写请求（wxid）
	public void testMustParamsmethodWxid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=wxid&key=rain_xcy";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.5:必填字段全部正确书写请求（wxid）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}	
	
	@Test
	//Case1.2.6:必填字段全部正确书写请求（xcid）
	public void testMustParamsmethodXcid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		params = "&keytp=xcid&key=6543210987";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.2.6:必填字段全部正确书写请求（xcid）=======");		
		String accresult = AccInterface.testFindunamebykeyD(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue("",ret);						
	}
	
//key参数大小写敏感
	
		@Test
		//Case1.3.1:必填字段全部正确书写请求（mobile）
		public void testMustParamsMobileKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=MOBILE&key=13673991650";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.1:必填字段全部正确书写请求（mobile）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case1.3.2:必填字段全部正确书写请求（email）
		public void testMustParamsEmailKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=EMAIL&key=renguohua@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.2:必填字段全部正确书写请求（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case1.3.3:必填字段全部正确书写请求（idcard）
		public void testMustParamsIdcardKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=IDCARD&key=412724198412072233";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.3:必填字段全部正确书写请求（idcard）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case1.3.4:必填字段全部正确书写请求（qqid）
		public void testMustParamsQqidKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=QQID&key=564911127";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.4:必填字段全部正确书写请求（qqid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case1.3.5:必填字段全部正确书写请求（wxid）
		public void testMustParamsWxidKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=WXID&key=rain_xcy";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.5:必填字段全部正确书写请求（wxid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case1.3.6:必填字段全部正确书写请求（xcid）
		public void testMustParamsXcidKey() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=XCID&key=6543210987";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case1.3.6:必填字段全部正确书写请求（xcid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

//参数为空验证
		
		@Test
		//Case2.1.1:keytp为空（mobile）
		public void testMustParamsMobilenull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=13673991650";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.1:keytp为空（mobile）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.1.2:keytp为空（email）
		public void testMustParamsEmailnull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=renguohua@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.2:keytp为空（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.1.3:keytp为空（idcard）
		public void testMustParamsIdcardnull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=412724198412072233";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.3:keytp为空（idcard）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.1.4:keytp为空（qqid）
		public void testMustParamsQqidnull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=564911127";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.4:keytp为空（qqid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case2.1.5:keytp为空（wxid）
		public void testMustParamsWxidnull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=rain_xcy";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.5:keytp为空（wxid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.1.6:keytp为空（xcid）
		public void testMustParamsXcidnull() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= &key=6543210987";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.1.6:keytp为空（xcid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}		
		
//正常参数验证
		
		@Test
		//Case2.2.1:key为空（mobile）
		public void testMustParamsMobileNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=mobile&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.1:key为空（mobile）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.2.2:key为空（email）
		public void testMustParamsEmailNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.2:key为空（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.2.3:key为空（idcard）
		public void testMustParamsIdcardNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=idcard&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.3:key为空（idcard）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.2.4:key为空（qqid）
		public void testMustParamsQqidNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=qqid&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.4:key为空（qqid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case2.2.5:key为空（wxid）
		public void testMustParamsWxidNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=wxid&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.5:key为空（wxid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.2.6:key为空（xcid）
		public void testMustParamsXcidNULL() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=xcid&key= ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.2.6:key为空（xcid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}		
		
//keytp参数包含空格
		
		@Test
		//Case2.3.1:keytp参数包含空格（mobile）
		public void testMustParamsnullMobile() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp= mobile&key=13673991650";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.1:keytp参数包含空格（mobile）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.3.2:keytp参数包含空格（email）
		public void testMustParamsnullEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email &key=renguohua@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.2:keytp参数包含空格（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case2.3.3:keytp参数包含空格（idcard）
		public void testMustParamsnullIdcard() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=id card&key=412724198412072233";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.3:keytp参数包含空格（idcard）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.3.4:keytp参数包含空格（qqid）
		public void testMustParamsnullQqid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=ｑｑｉｄ&key=564911127";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.4:keytp参数包含空格（qqid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case2.3.5:keytp参数包含空格（wxid）
		public void testMustParamsnullWxid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=wxdi&key=rain_xcy";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.5:keytp参数包含空格（wxid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.3.6:keytp参数包含空格（xcid）
		public void testMustParamsnullXcid() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=xcidCase2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格&key=6543210987";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.6:keytp参数包含空格（xcid）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case2.3.7:keytp参数包含空格（mobile）
		public void testMustParamsnulMobile() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=mob~!@#$%^&*()<>ile&key=13673991650";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case2.3.7:keytp参数包含空格（mobile）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}		

//参数逻辑约束及边界值验证
		
		@Test
		//Case3.1.1:参数逻辑及边界验证（email）
		public void testMustParamsemail1() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohuagw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.1:参数逻辑及边界验证（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case3.1.2:参数逻辑及边界验证（email）
		public void testMustParamsEmail2() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.2:参数逻辑及边界验证（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case3.1.3:参数逻辑及边界验证（email）
		public void testMustParamsEmail3() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua@";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.3:参数逻辑及边界验证（email）=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case3.1.4:参数逻辑及边界验证（email）
		public void testMustParamsEmail4() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua@.gwcom.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.4:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case3.1.5:参数逻辑及边界验证（email）
		public void testMustParamsEmail5() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua@gw.com.";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.5:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	

		@Test
		//Case3.1.6:参数逻辑及边界验证（email）
		public void testMustParamsEmail6() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua@gw.com.ｃｎ";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.6:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}		
		@Test
		//Case3.1.7:参数逻辑及边界验证（email）
		public void testMustParamsEmail7() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua~!@#$%^&*()@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.7:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}
		
		@Test
		//Case3.1.8:参数逻辑及边界验证（email）
		public void testMustParamsEmail8() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua~!@#$%^&*()@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.8:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}	
		
		@Test
		//Case3.1.9:参数逻辑及边界验证（email）
		public void testMustParamsEmail9() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohua@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.9:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);						
		}		
		@Test
		//Case3.1.10:参数逻辑及边界验证（email）
		public void testMustParamsEmail10() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
			params = "&keytp=email&key=renguohua1@gw.com.cn";
			
			//请求输入
			Thread.sleep(1001);
			System.out.println("======Case3.1.10:参数逻辑及边界验证（email））=======");		
			String accresult = AccInterface.testFindunamebykey(params);	
			
			//校验返回值
			assertTrue("True",accresult.contains("result=0"));		
			String Myuid = MyUid.Uidex(accresult);
			String uname = MyUid.Una(accresult);
			MyRedisUtil myredis = new MyRedisUtil();			
			String myredisuid = myredis.getValue("uid:"+Myuid+"");
					
			//校验redis中的uid对应的用户名是否正确
			assertEquals(myredisuid,uname);
			boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
			assertTrue("",ret);		
		}
			@Test
			//Case3.2.1:参数逻辑及边界值（mobile）
		public void testMustParamsMobile1() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=abcdefg";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.1:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
		
			@Test
			//Case3.2.2:参数逻辑及边界值（mobile）
		public void testMustParamsMobile2() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=abcdefg";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.2:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.2.3:参数逻辑及边界值（mobile）
		public void testMustParamsMobile3() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=abcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnm";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.3:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
		
			@Test
			//Case3.2.4:参数逻辑及边界值（mobile）
		public void testMustParamsMobile4() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=~!@#$%^&*()_+<>{}|";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.4:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}	
		
			@Test
			//Case3.2.5:参数逻辑及边界值（mobile）
		public void testMustParamsMobile5() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=1";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.5:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}	
			@Test
			//Case3.2.6:参数逻辑及边界值（mobile）
		public void testMustParamsMobile6() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=1367933165";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.6:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.2.7:参数逻辑及边界值（mobile）
		public void testMustParamsMobile7() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136793316502";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.7:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.2.8:参数逻辑及边界值（mobile）
		public void testMustParamsMobile8() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=13679331650234";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.8:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}	
			
			@Test
			//Case3.2.9:参数逻辑及边界值（mobile）
		public void testMustParamsMobile9() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136793316502345";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.9:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			
			@Test
			//Case3.2.10:参数逻辑及边界值（mobile）
		public void testMustParamsMobile10() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=1367933165023456";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.10:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}	
			@Test
			//Case3.2.11:参数逻辑及边界值（mobile）
		public void testMustParamsMobile11() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=23673991650";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.11:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.2.12:参数逻辑及边界值（mobile）
		public void testMustParamsMobile12() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key= 13673991650";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.12:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.2.13:参数逻辑及边界值（mobile）
		public void testMustParamsMobile13() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136 73991650";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.13:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.2.14:参数逻辑及边界值（mobile）
		public void testMustParamsMobile14() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=13673991650 ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.14:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			
			@Test
			//Case3.2.15:参数逻辑及边界值（mobile）
		public void testMustParamsMobile15() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=1367399165X";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.15:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.2.16:参数逻辑及边界值（mobile）
		public void testMustParamsMobile16() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136739916X5";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.16:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.2.17:参数逻辑及边界值（mobile）
		public void testMustParamsMobile17() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136739916X5";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.17:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}	
			
			@Test
			//Case3.2.18:参数逻辑及边界值（mobile）
		public void testMustParamsMobile18() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=136739916５０";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.18:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			
			@Test
			//Case3.2.19:参数逻辑及边界值（mobile）
		public void testMustParamsMobile19() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=mobile&key=13673991651";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.2.19:参数逻辑及边界值（mobile）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
						
			@Test
			//Case3.3.1:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard1() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=4127";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.1:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.3.2:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard2() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=41275678910234";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.2:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
 
			@Test
			//Case3.3.3:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard3() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=4127567891023456";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.3:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.4:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard4() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=412756789102345678";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.4:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.3.5:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard5() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=412756789102345678412756789102345678412756789102345678";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.5:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.3.6:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard6() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=41272445698745N";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.6:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.7:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard7() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=41272419841207251n";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.7:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.3.8:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard8() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key= 41272445698745X";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.8:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}
			@Test
			//Case3.3.9:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard9() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=412724456 98745X";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.9:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.10:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard10() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=41272445698745X ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.10:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.11:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard11() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=41272419841207251x ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.11:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.12:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard12() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=4127241984120725 1x";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.12:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.13:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard13() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key= 41272419841207251x";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.13:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.14:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard14() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=~!@#$%^&*()_+<>";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.14:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.3.15:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard15() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=~!@#$%^&*()_+<>{}|";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.15:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}						
			@Test
			//Case3.3.16:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard16() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=４１２７２４１９８４１２０７２２３３";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.16:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			
			@Test
			//Case3.3.17:参数逻辑及边界值（idcard）
		public void testMustParamsIdcard17() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=idcard&key=４１２７２４４５６９８７４５ｘ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.3.17:参数逻辑及边界值（idcard）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.4.1:参数逻辑及边界值（wxid）
		public void testMustParamsWxid1() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=ｒａｉｎ＿ｘｃｙ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.1:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.4.2:参数逻辑及边界值（wxid）
		public void testMustParamsWxid2() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=rain_xcy ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.2:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.4.3:参数逻辑及边界值（wxid）
		public void testMustParamsWxid3() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=rai n_xcy";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.3:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.4.4:参数逻辑及边界值（wxid）
		public void testMustParamsWxid4() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key= rain_xcy";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.4:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
			@Test
			//Case3.4.5:参数逻辑及边界值（wxid）
		public void testMustParamsWxid5() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=~!@#$%^&*()_+<>{}|";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.5:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.4.6:参数逻辑及边界值（wxid）
		public void testMustParamsWxid6() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.6:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.4.7:参数逻辑及边界值（wxid）
		public void testMustParamsWxid7() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=wxid&key=RAin_xcy";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.4.7:参数逻辑及边界值（wxid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.5.1:参数逻辑及边界值（xcid）
		public void testMustParamsXcid1() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key=testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.1:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.5.2:参数逻辑及边界值（xcid）
		public void testMustParamsXcid2() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key= 6543210987";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.2:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}				
			@Test
			//Case3.5.3:参数逻辑及边界值（xcid）
		public void testMustParamsXcid3() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key=6 543210987";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.3:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}
			@Test
			//Case3.5.4:参数逻辑及边界值（xcid）
		public void testMustParamsXcid4() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key=6543210987  ";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.4:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}
			@Test
			//Case3.5.5:参数逻辑及边界值（xcid）
		public void testMustParamsXcid5() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key=６５４３２１０９８７";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.5:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}		
			@Test
			//Case3.5.6:参数逻辑及边界值（xcid）
		public void testMustParamsXcid6() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
				params = "&keytp=xcid&key=~!@#$%^&*()_+{}|<>?";
				
				//请求输入
				Thread.sleep(1001);
				System.out.println("======Case3.5.6:参数逻辑及边界值（xcid）=======");		
				String accresult = AccInterface.testFindunamebykey(params);	
				
				//校验返回值
				assertTrue("True",accresult.contains("result=0"));		
				String Myuid = MyUid.Uidex(accresult);
				String uname = MyUid.Una(accresult);
				MyRedisUtil myredis = new MyRedisUtil();			
				String myredisuid = myredis.getValue("uid:"+Myuid+"");
						
				//校验redis中的uid对应的用户名是否正确
				assertEquals(myredisuid,uname);
				boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
				assertTrue("",ret);						
			}			
				
}
