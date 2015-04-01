/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *功能说明：
 *1）参数从resources/沪深A股.txt读取，依次遍历所有；
 */
package com.gw.dzhyun.httptest;

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

/**
 * @author Lizhiqiang
 *
 */
public class QuoteDynaTest{
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


	/**
	 * 测试所有沪深A股代码
	 * @throws Exception 
	 */
//	@Test
//	public void testAllSZCodes() throws Exception {
//		//若果不想运行本函数，添加fail语句
//		fail("donot run this!");
//		
//		//读取测试用例参数
//		String filePath = System.getProperty("user.dir") + "\\resources\\沪深A股.txt";
//		//System.out.println("filepath="+filePath);
//		BufferedReader breader =null;
//		breader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
//		
//		//发送请求
//		String urlString =null;
//		String code=null;    //沪深股代码
//		int codeNum = 0;
//		int succNum=0;
//		String ip = MyConfigUtil.getConfig("ip");
//		String port=MyConfigUtil.getConfig("port");
//		while((code = breader.readLine()) !=null)
//		{
//			code = code.trim();
//			codeNum++;
//			//http://10.15.144.101/quote/dyna?obj=SH600000
//			urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code;
//			String ret = MyHttpUtil.sendHttpRequest(urlString).getText();
//			if(ret.contains(code))  //如果返回字符串带有code，例如SH600000，一般是成功返回。
//				succNum++;
//			//System.out.println();
//		}
//		
//		//关闭文件close
//		breader.close();
//		assertEquals(codeNum,succNum);
//	}
	
	/**
	 * 带参数：field
	 * @throws Exception 
	 * @throws SAXException 
	 */
//	@Test
//	public void testField() throws SAXException, Exception
//	{
//		//http://10.15.144.101/quote/dyna?obj=SH600000
//		String urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&field=time,lastclose";
//		String ret = MyHttpUtil.sendHttpRequest(urlString).getText();
//		assertTrue(ret.contains(code));
//	}

	/**
	 * 带参数：out为json的情况。
	 * 需要：1）比较输出结果与直接从行情系统QTRS取到的数据的一致性；2）比较取到结果的时间戳与直接从QTRS取到数据的时间戳一致。
	 * @throws Exception 
	 * @throws SAXException 
	 */
	@Test
	public void testOutputJson() throws SAXException, Exception
	{
		code="SH600000";
		String urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&output=json";  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getQuoteDyna(urlString,type);
		System.out.println(ret);
		assertNotNull("错误：行情返回null",ret);
		JSONObject data = MyQuoteDynaUtil.getQuoteDynaByObjCode(ret, code);
		assertNotNull("错误：股票数据为null",data);
	}
	
	/**下面这个注释方法不要删除，不过也不要编辑。
	 * 带参数：out为pb
	 * @throws Exception 
	 * @throws SAXException 
	 */
//	@Test
//	public void testOutputPb() throws SAXException, Exception
//	{
//		//http://10.15.144.101/quote/dyna?obj=SH600000
//		String urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&field=time,lastclose&output=pb&qid=test1";//&field=time,lastclose
//		WebResponse response = MyHttpUtil.sendHttpRequest(urlString);  
//
//		UAResponse uaresponse = UAResponse.parseFrom(response.getInputStream());
//		if(uaresponse.hasData())
//		{
//			//获取DATA部分
//			ByteString bs = uaresponse.getData();		
//			//DATA部分也是protobuf格式，仍需要解析
//			QuoteDynaOutput output = QuoteDynaOutput.parseFrom(bs);
//			QuoteDynaSingle single = output.getResults(0);
//			String obj = single.getObj();
//			//断言
//			assertEquals(code,obj);
//		}		
//	}

}

