package com.gw.account.httptest;

import static org.junit.Assert.assertTrue;

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



import com.atopcloud.util.MyUname;
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

public class T {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SAXException, InterruptedException {
//		String stl = "uname=lidb20150211140053&usertid=15839328&result=0";
//		int num = stl.indexOf("usertid=");
//		int num2 = stl.indexOf("&result");
//		String s = stl.substring(num+8, num2);
//		String s1 = stl.substring(stl.lastIndexOf("usertid=")+8);
//		System.out.println(s1);
//		System.out.print(s);
		testMustParamsEmail();
	}
	public static void testMustParamsEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		
		String keytpval = "&keytp=";
		String keyval = "&key=";
		
		String keytp = "email";
		String key="renguohua1@gw.com.cn";
		
		String params = keytpval+keytp+keyval+key;
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.2:必填字段全部正确书写请求（email）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		assertTrue("True",accresult.contains("result=0"));		
		
      //校验redis中的uid对应的用户名是否正确		
		String uname = MyUname.Uname(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuname = myredis.getValue("k_1:"+key+"");
		assertEquals(myredisuname,uname);
		 //校验bdb中的uid对应的用户名是否正确	
		//boolean ret = MyCheckBdb.CheckBdb(uname,"","","k_1:"+key+"","","","");
		//assertTrue(uname,ret);						
	}
	private static void assertEquals(String myredisuname, String uname) {
		// TODO Auto-generated method stub
		
	}
}
