/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *
 *测试动态行情-K线，目前支持SH/SZ/B$/SO/ZI/SW/HI/SF
     （json格式，查询数量count:<=78条）
       涉及字段：
  1、period:1day、1min、5min、15min、30min、60min
  2、field:ShiJian,ZuiGaoJia...
  3、begin_time=YYYYMMDD-HHMMSS
     &end_time=YYYYMMDD-HHMMSS
  4、split:0、1、2，0是不除权，1是前复权，2是后复权，默认取0
  */
package com.gw.dzhyun.httptest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;









import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
//import com.gw.dzhyun.util.MyQuoteDynaUtil;
import com.gw.dzhyun.util.MyQuoteKlineUtil;
import com.gw.dzhyun.util.TranYfloatMain;
import com.gw.dzhyun.util.TranYfloatStatic;
//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.gw.dzhyun.util.TranYfloatStatic;

/**
 * 
 * @author Wangying001
 * @date 2015年11月12日
 */
public class QuoteKlineTest {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String code= "SH601519";    //沪深股代码
	String period= "1day"; 
	String start= "-1"; 
	String count= "1";
	String field= "ShiJian,ZuiGaoJia,ZuiDiJia";
	String begin_time= "20140101-000000";
	String end_time= "20141231-000000";
	String token="58cd20c8affb4013ae7f3301d420e245";
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
	 * 1.1测试json格式，最新1条1day K线,股票代码是SH600000，period取值1day。
	 * @throws Exception 
	 */
	@Test
	public void test1DayKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "1day";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600008&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
		
		//yfloat转换
		/*JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		  System.out.println(jsonyfloatResponse);*/
	}
	
	/**
	 * 1.2测试json格式，最新78条1day K线,股票代码是SH600000，period取值1day。
	 * @throws Exception 
	 */
	@Test
	public void test78DayKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "1day";
		start= "78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600000&period=1day&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 1.3测试json格式，最新1条1min K线,股票代码是SH600000，period取值1min。
	 * @throws Exception 
	 */
	@Test
	public void test1MinKLine() throws Exception {
		code="SH600600";
		//period取值1day、1min、5min
		period= "1min";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=1min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data+"\n");
		
		/*//yfloat转换
		JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		System.out.println(jsonyfloatResponse);*/
	}
	
	/**
	 * 1.4测试json格式，最新78条1min K线,股票代码是SH600000，period取值1min。
	 * @throws Exception 
	 */
	@Test
	public void test78OneMinKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "1min";
		start= "78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600000&period=1min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 1.5测试json格式，最新1条5min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test5MinKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "5min";
		start= "1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=5min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 1.6测试json格式，最新78条5min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test78FiveMinKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "5min";
		start= "78";
		//count取值<=48，json格式
		count= "78";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 2.1测试最新78条1day（或1min或5min）K线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void testField78DayKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "1day";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,ZuiGaoJia,ZuiDiJia,ChengJiaoBiShu";
		
		//quote/kline?obj=SH600004&period=1day&start=-78&count=78&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	
	/**
	 * 2.2测试最新78条1min K线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void testField78OneMinKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "1min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,ZuiGaoJia,ZuiDiJia,ChengJiaoBiShu";
		
		//quote/kline?obj=SH600000&period=1min&start=-78&count=78&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 2.3测试最新78条5minK线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void testField78FiveMinKLine() throws Exception {
		code="SH600008";
		//period取值1day、1min、5min
		period= "5min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,ZuiGaoJia,ZuiDiJia,ChengJiaoBiShu";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 3.1测试一段时间的日K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodDayKLine() throws Exception {
		code="SH600008";
		period= "1day";
		begin_time= "20150622-000000";
		end_time= "20150622-230000";
		
		//quote/kline?obj=SH600008&period=1day&begin_time=20150403-000000&end_time=20150403-173502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
		
	}
	
	/**
	 * 3.2测试一段时间的1min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodOneMinKLine() throws Exception {
		code="SH600008";
		period= "1min";
		begin_time= "20150403-000000";
		end_time= "20150403-230000";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600008&period=1min&begin_time=20150403-000000&end_time=20150403-173502&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 3.3测试一段时间的5min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodFiveMinKLine() throws Exception {
		code="SH600008";
		period= "5min";
		begin_time= "20150403-000000";
		end_time= "20150403-230000";
		start= "-48";
		//count取值<=78，json格式
		count= "48";
		
		//quote/kline?obj=SH600008&period=5min&begin_time=20150410-000000&end_time=20150410-173502&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	
	/**
	 * 4.1测试一段时间的日K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodFieldDayKLine() throws Exception {
		code="SH600000";
		period= "1day";
		field= "ShiJian,ZuiGaoJia,ZuiDiJia";
		begin_time= "20150403-000000";
		end_time= "20150403-130000";
		//quote/kline?obj=SH600008&period=1day&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu&begin_time=20150403-000000&end_time=20150403-110000
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(ret);
	}
	
	/**
	 * 4.2测试一段时间的1min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodFieldMinKLine() throws Exception {
		code="SH600000";
		period= "1min";
		field= "ShiJian,ZuiGaoJia,ZuiDiJia";
		begin_time= "20150403-000000";
		end_time= "20150403-130000";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600008&period=1min&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu&begin_time=20150403-000000&end_time=20150403-110000&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(ret);
	}

	/**
	 * 4.3测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void testPeriodFieldFiveMinKLine() throws Exception {
		code="SH600000";
		period= "5min";
		field= "ShiJian,ZuiGaoJia,ZuiDiJia";
		begin_time= "20150403-000000";
		end_time= "20150403-130000";
		start= "-48";
		//count取值<=78，json格式
		count= "48";
		
		//quote/kline?obj=SH600008&period=5min&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu&begin_time=20150403-000000&end_time=20150403-110000&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(ret);
	}
}
