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
import org.xml.sax.SAXException;

import com.atopcloud.util.ByteBuffer2StringUtil;
import com.atopcloud.util.PropertiesManager;
import com.google.protobuf.ByteString;
//http unit
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

import dzhyun.Dzhoutput.QuoteDynaOutput;
import dzhyun.Dzhoutput.QuoteDynaSingle;
import dzhyun.Dzhua.UAResponse;







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
public class QuoteDynaTest {
	public String ip;
	public String port;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//System.out.println("setup");
		//初始化
		//读取环境变量
		String envPath = System.getProperty("user.dir") + "\\config\\env.properties";
		PropertiesManager pm = new PropertiesManager(envPath);
		 ip = pm.getValue("ip");
		 port=pm.getValue("port");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//System.out.println("teardown");
	}

	/**
	 * 发送一般request http请求
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public String sendHttpRequest(String url) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		GetMethodWebRequest get = new GetMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		//get
		WebResponse response = web.getResponse(get);  
		//response.getInputStream();
		return response.getText();
		
	}

	/**
	 * 发送post http请求，暂没用上。
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public String sendHttpPost(String url) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		PostMethodWebRequest post = new PostMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(post);  
		return response.getText();
	}
	/**
	 * 测试所有沪深A股代码
	 * @throws Exception 
	 */
	@Test
	public void testAllSZCodes() throws Exception {
		//若果不想运行本函数，添加fail语句
		fail("donot run this!");
		
		//读取测试用例参数
		String filePath = System.getProperty("user.dir") + "\\resources\\沪深A股.txt";
		//System.out.println("filepath="+filePath);
		BufferedReader breader =null;
		breader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		
		//发送请求
		String urlString =null;
		String code=null;    //沪深股代码
		int codeNum = 0;
		int succNum=0;
		while((code = breader.readLine()) !=null)
		{
			code = code.trim();
			codeNum++;
			//http://10.15.144.101/quote/dyna?obj=SH600000
			urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code;
			String ret = sendHttpRequest(urlString);
			if(ret.contains(code))  //如果返回字符串带有code，例如SH600000，一般是成功返回。
				succNum++;
			//System.out.println();
		}
		
		//关闭文件close
		breader.close();
		assertEquals(codeNum,succNum);
	}
	
	/**
	 * 带参数：field
	 * @throws Exception 
	 * @throws SAXException 
	 */
	@Test
	public void testField() throws SAXException, Exception
	{
		//发送请求
		String urlString =null;
		String code= "SH600000";    //沪深股代码

		//http://10.15.144.101/quote/dyna?obj=SH600000
		urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&field=time,lastclose";
		String ret = sendHttpRequest(urlString);
		assertTrue(ret.contains(code));
	}

	/**
	 * 带参数：out为json
	 * @throws Exception 
	 * @throws SAXException 
	 */
	@Test
	public void testJson() throws SAXException, Exception
	{
		//发送请求
		String urlString =null;
		String code= "SH600000";    //沪深股代码

		//http://10.15.144.101/quote/dyna?obj=SH600000
		urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&field=time,lastclose&output=json";
		String ret = sendHttpRequest(urlString);
		System.out.println(ret);
		assertTrue(ret.contains(code));
	}
	
	/**
	 * 带参数：out为pb
	 * @throws Exception 
	 * @throws SAXException 
	 */
	@Test
	public void testPb() throws SAXException, Exception
	{
		//发送请求
		String urlString =null;
		String code= "SH600000";    //沪深股代码

		//http://10.15.144.101/quote/dyna?obj=SH600000
		urlString = "http://" + ip + ":" +port + "/quote/dyna?obj=" + code + "&field=time,lastclose&output=pb&qid=test1";//&field=time,lastclose
		
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		//get
		WebResponse response = web.getResponse(get);  

		//通过文件得到消息实例，本例不需要通过文件，而是直接读取http请求返回的输入流
		//UAResponse uaresponse = UAResponse.parseFrom(new FileInputStream("C:\\Users\\shun\\Desktop\\test\\aaa.protoout"));//通过文件
		//通过http 请求返回的输入流得到消息实例
		UAResponse uaresponse = UAResponse.parseFrom(response.getInputStream());
//		System.out.println("qid="+uaresponse.getQid());
//		System.out.println("err="+uaresponse.getErr());
//		System.out.println("counter="+uaresponse.getCounter());
		if(uaresponse.hasData())
		{
			//获取DATA部分
			ByteString bs = uaresponse.getData();		
			//DATA部分也是protobuf格式，仍需要解析
			QuoteDynaOutput output = QuoteDynaOutput.parseFrom(bs);
			QuoteDynaSingle single = output.getResults(0);
			String obj = single.getObj();
			
			assertEquals(code,obj);
		}		
	}	
}

