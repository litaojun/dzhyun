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
 * @author Lizhiqiang
 *
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

//	/**
//	 * 1.3测试1日K线，数量<=78条(json格式)，股票代码是SH600000。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testOneDayKLine() throws Exception {
//		String code="SZ000002";
//		String period= "1day";
//		///quote/kline?obj=SH600000&period=1day&start=-1&count=1
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period +"&start=-1&count=1";  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		//System.out.println(ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(ret);
//	}
//	
//	/**
//	 * 1.1测试1minK线，数量<=78条(json格式)，股票代码是SH600000,目前1min线数据错误。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testOneMinKLine() throws Exception {
//		String code="SZ000001";
//		String period= "1min";
//		///quote/kline?obj=SH600000&period=1min&start=-1&count=1
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		//System.out.println(ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(ret);
//	}
//	
//	/**
//	 * 1.2测试5minK线，股票代码是SH600000。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testFiveMinKLine() throws Exception {
//		String code="SH600000";
//		String period= "5min";
//		///quote/kline?obj=SH600000&period=5min
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(data);
//	}
	
	
//	/**
//	 * 1.1-1.6测试json格式，最新X条1day（或1min或5min）K线,股票代码是SH600000，period取值1day、1min、5min。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testHistoryPeriodKLine() throws Exception {
//		code="SH600008";
//		//period取值1day、1min、5min
//		period= "5min";
//		start= "1";
//		//count取值<=78，json格式
//		count= "1";
//		
//		//quote/kline?obj=SH600000&period=1min&start=-20&count=20
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(data);
//	}
		
//	/**
//	 * 1.7-1.9测试最新X条1day（或1min或5min）K线中某些指标项，股票代码是SH600008。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testHistoryPeriodFieldKLine() throws Exception {
//		code="SH600008";
//		//period取值1day、1min、5min
//		period= "5min";
//		start= "-78";
//		count= "78";
//		//field字段首字母大写
//		field= "ShiJian,ZuiGaoJia,ZuiDiJia,ChengJiaoBiShu";
//		
//		//quote/kline?obj=SH600000&period=5min&start=-10&count=10&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(data);
//	}
	
//	/**
//	 * 1.10-1.12测试一段时间的日K线，股票代码是SH600000。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testPeriodDayKLine() throws Exception {
//		code="SH600008";
//		period= "1day";
	    //period取值1day、1min、5min
//		begin_time= "20150403-000000";
//		end_time= "20150403-230000";
//		//quote/kline?obj=SH600000&period=1day&begin_time=20150402-000000&end_time=20150402-110000
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(data);
//	}

//	/**
//	 * 1.13-1.15测试一段时间的日K线中某些指标项，股票代码是SH600000。
//	 * @throws Exception 
//	 */
//	@Test
//	public void testPeriodFieldKLine() throws Exception {
//		code="SH600000";
//		period= "1day";
	    //period取值1day、1min、5min
//		field= "shiJian,zuiGaoJia,zuiDiJia";
//		begin_time= "20150403-000000";
//		end_time= "20150403-130000";
//		//quote/kline?obj=SH600000&period=1day&field=shiJian,zuiGaoJia,chenJiaoBiShu&begin_time=20150402-000000&end_time=20150402-110000
//		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time;  //每个测试方法需要修改
//		String type="json";
//		
//		String ret =MyHttpUtil. getData(urlString,type);
//		assertNotNull("错误：行情返回null",ret);
//		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
//		assertNotNull("错误：股票k线为null",data);
//		System.out.println(ret);
//	}
	
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
		/*JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);*/
		
		assert(ret!=null);
		System.out.println(ret+"\n");
		
		TranYfloatMain tym = new TranYfloatMain(ret,"RepDataQuoteKlineSingle");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
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
		
		//yfloat转换
		JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		System.out.println(jsonyfloatResponse);
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
		/*JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);*/
		
		assert(ret!=null);
		System.out.println(ret+"\n");
		
		TranYfloatMain tym = new TranYfloatMain(ret,"RepDataQuoteKlineSingle");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
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
