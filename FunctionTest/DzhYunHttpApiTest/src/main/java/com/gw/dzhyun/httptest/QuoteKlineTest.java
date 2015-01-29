/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 */
package com.gw.dzhyun.httptest;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
/**
 * @author Lizhiqiang
 *
 */
public class QuoteKlineTest {

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
	 * 
	 * @throws IOException
	 * @throws SAXException
	 */
	@Test
	public void testUserget() throws IOException, SAXException {
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		
		//使用 WebRequest设置相关的请求参数
		String urlString = "http://10.15.144.101/quote/kline?obj=SH600000&period=1day&field=time,high,low&begin_time=20140101-000000&end_time=20141231-000000";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(get);  
		
		System.out.printf("recv : %s", response.getText());  
		
		
		//post	
		//PostMethodWebRequest post = new PostMethodWebRequest(url);
        // fail("MYTEST");
	}
	
	

}
