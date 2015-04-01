/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 */
package com.gw.dzhyun.httptest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;






import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.MyQuoteDynaUtil;
import com.gw.dzhyun.util.MyQuoteKlineUtil;
//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
/**
 * @author Lizhiqiang
 *
 */
public class QuoteKlineTest {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String code= "SH601519";    //沪深股代码
	String period= "1day";    
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
	 * 测试1日K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testOneDayKLine() throws Exception {
		code="SH600000";
		///quote/kline?obj=SH600000&period=1day
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONObject data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
	}
	
	

}
