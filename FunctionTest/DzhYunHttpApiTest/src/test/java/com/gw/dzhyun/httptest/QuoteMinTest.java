package com.gw.dzhyun.httptest;
/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *功能说明：
 *1）参数从resources/沪深A股.txt读取，依次遍历所有；
 */

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONObject;
import com.atopcloud.testcasefilter.MyTestCaseFilter;
import com.atopcloud.util.ByteBuffer2StringUtil;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.atopcloud.util.PropertiesManager;
import com.dzhyun.proto.Dzhoutput.QuoteDynaOutput;
import com.dzhyun.proto.Dzhoutput.QuoteDynaSingle;
import com.dzhyun.proto.Dzhua.UAResponse;
import com.google.protobuf.ByteString;
import com.googlecode.protobuf.format.JsonFormat;
import com.gw.dzhyun.util.MyQuoteDynaUtil;
//http unit
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;




//io
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.Properties;

public class QuoteMinTest 
{
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String code= "SH601519";    //沪深股代码
	
	/**
	 * @throws java.lang.Exception
	 */ 
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
	
	@ Test
	public void testFenShiNormal() throws SAXException, Exception
	{
		String urlString = "http://" + ip + ":" +port + "/quote/min?obj=" + code + "&output=json";  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getQuoteDyna(urlString,type);
		System.out.println("ret="+ret);
		assertNotNull("错误：行情返回null",ret);
		//JSONObject data = MyQuoteDynaUtil.getQuoteDynaByObjCode(ret, code);
		//int errRet = data.getIntValue("Err");
		//System.out.println("errRet=="+errRet);
		//assertSame(0,errRet);
		//assertNotNull("错误：股票数据为null",data);
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
