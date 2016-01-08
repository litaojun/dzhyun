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
	String code= "SH601519";    //代码
	String codeSO= "SO430011";   //三板市场（深三板）
	String codeSF= "SFIC1601";   //上海金融期货指数
	String codeSH= "SH600000";   //上证代码
	String codeSZ= "SZ000777";   //深证代码
	String codeB$= "B$991036";   //板块代码
	String codeZI= "ZI000008";   //中证指数
	String codeHI= "HIHSCCI";    //恒生指数
	String codeSW= "SW801164";   //申万指数
	String codeSC= "SCag0001";   //上海期货
	String codeZC= "ZCCF0001";   //郑州商品
	String codeDC= "DCa0001";   //大连商品
	String comma= ","; 
	String period= "1day"; 
	String start= "-1"; 
	String count= "1";
	String field= "ShiJian,ZuiGaoJia,ZuiDiJia";
	String begin_time= "20140101-000000";
	String end_time= "20141231-000000";
	String split= "0";
	String token="5158e9ef5b9e4c059173882648549ac7";
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
	 * 1.1.1测试json格式，最新1条1day K线,股票代码是SH/SZ，period取值1day。
	 * @throws Exception 
	 */
	@Test
	public void test111DayKLine() throws Exception {
		code=codeSH.concat(comma).concat(codeSZ);
		code=codeSH+comma+codeSZ;
		//period取值1day、1min、5min、15min、30min、60min
		period= "1day";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600008,SZ000777&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
		
		//yfloat转换
		/*JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		  System.out.println(jsonyfloatResponse);*/
	}
	
	/**
	 * 1.1.2测试json格式，最新1条1day K线, SO/SF/SH/SZ/B$/ZI市场代码
	 * @throws Exception 
	 *//*
	@Test
	public void test112DayKLine() throws Exception {
		code=code1.concat(comma).concat(code2).concat(comma).concat(code3).concat(comma).concat(code4).concat(comma).concat(code5).concat(comma).concat(code6);
		//		.concat(comma).concat(code5).concat(comma).concat(code6).concat(comma).concat(code7).concat(comma).concat(code8);
		
		//code=code1+comma+code2+comma+code3;
		
		period= "1day";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SO430011,SFIC1511,SH600008,SZ000777,B$991036,ZI000008,HIHSCCI,SW801164&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}*/
	
	/**
	 * 1.1.2测试json格式，最新1条1day K线, SO/SF/B$/ZI市场代码
	 * @throws Exception 
	 */
	@Test
	public void test112DayKLine() throws Exception {
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		//		.concat(comma).concat(code5).concat(comma).concat(code6).concat(comma).concat(code7).concat(comma).concat(code8);
		
		//code=code1+comma+code2+comma+code3;
		
		period= "1day";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SO430011,SFIC1511,SH600008,SZ000777,B$991036,ZI000008,HIHSCCI,SW801164&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
		
	}
	
	/**
	 * 1.1.3测试json格式，最新1条1day K线, HI/SW市场代码
	 * @throws Exception 
	 */
	@Test
	public void test113DayKLine() throws Exception {
		code=codeHI.concat(comma).concat(codeSW).concat(comma).concat(codeSF);
		
		//code=code1+comma+code2+comma+code3;
		
		period= "1day";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SO430011,SFIC1511,SH600008,SZ000777,B$991036,ZI000008,HIHSCCI,SW801164&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.1.4测试json格式，最新1条1day K线,股票代码是SH/SZ，period取值1day。
	 * @throws Exception 
	 */
	@Test
	public void test114DayKLine() throws Exception {
		code=codeSH.concat(comma).concat(codeSZ);
		code=codeSH+comma+codeSZ;
		//period取值1day、1min、5min、15min、30min、60min
		period= "1day";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600008,SZ000777&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.1.5测试json格式，最新1条1day K线, SO/SF/B$/ZI市场代码
	 * @throws Exception 
	 */
	@Test
	public void test115DayKLine() throws Exception {
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		//		.concat(comma).concat(code5).concat(comma).concat(code6).concat(comma).concat(code7).concat(comma).concat(code8);
		
		//code=code1+comma+code2+comma+code3;
		
		period= "1day";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SO430011,SFIC1511,SH600008,SZ000777,B$991036,ZI000008,HIHSCCI,SW801164&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
		
	}
	
	/**
	 * 1.1.6测试json格式，最新1条1day K线, HI/SW市场代码
	 * @throws Exception 
	 */
	@Test
	public void test116DayKLine() throws Exception {
		code=codeHI.concat(comma).concat(codeSW);
		
		//code=code1+comma+code2+comma+code3;
		
		period= "1day";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SO430011,SFIC1511,SH600008,SZ000777,B$991036,ZI000008,HIHSCCI,SW801164&period=1day&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.1.6测试json格式，最新1条1day K线, SW市场代码
	 * @throws Exception 
	 *//*
	@Test
	public void test116DayKLine() throws Exception {
		code="SW801164";
		period= "1day";
		start= "-10";
		//count取值<=78，json格式
		count= "10";
		
		//quote/kline?obj=SW801164&period=1day&start=-10&count=10
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		System.out.println(data);
	}
	*/
	/**
	 * 1.1.7测试json格式，最新1条1day K线, SO市场代码
	 * @throws Exception 
	 */
	@Test
	public void test117DayKLine() throws Exception {
		code="SH600004";
		period= "1day";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600004&period=1day&start=-10&count=10
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.1.8测试json格式，最新1条1day K线, SF市场代码
	 * @throws Exception 
	 */
	@Test
	public void test118DayKLine() throws Exception {
		code="SFIC1511";
		period= "1day";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SFIC1511&period=1day&start=-10&count=10
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.2.1测试json格式，最新1条1min K线,股票代码是SH600000，period取值1min。
	 * @throws Exception 
	 */
	@Test
	public void test121MinKLine() throws Exception {
		//code="SO430011,SFIC1511,SH600000,SZ000777,B$991036,ZI000008";
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		
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
		/*/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data+"\n");
	}
	
	/**
	 * 1.2.2测试json格式，最新78条1min K线,股票代码是SH600000，period取值1min。
	 * @throws Exception 
	 */
	@Test
	public void test122MinKLine() throws Exception {
		//code="HIHSCCI,SW801164";
		code=codeHI.concat(comma).concat(codeSW);
		//period取值1day、1min、5min
		period= "1min";
		start= "-78";
		//count取值<=78，json格式
		count= "78";
		
		//quote/kline?obj=SH600000&period=1min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.3.1测试json格式，最新1条5min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test131FiveMinKLine() throws Exception {
		//code="SO430011,SFIC1511,SH600000,SZ000777,B$991036,ZI000008";
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		
		//period取值1day、1min、5min
		period= "5min";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=5min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.3.2测试json格式，最新78条5min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test132FiveMinKLine() throws Exception {
		//code="HIHSCCI,SW801164";
		code=codeHI.concat(comma).concat(codeSW);
		
		//period取值1day、1min、5min
		period= "5min";
		start= "-78";
		//count取值<=48，json格式
		count= "78";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.4.1测试json格式，最新1条15min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test141FifMinKLine() throws Exception {
		//code="SO430011,SFIC1511,SH600000,SZ000777,B$991036,ZI000008";
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		
		period= "15min";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=15min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.4.2测试json格式，最新78条15min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test142FifMinKLine() throws Exception {
		//code="HIHSCCI,SW801164";
		code=codeHI.concat(comma).concat(codeSW);
		period= "15min";
		start= "-78";
		count= "78";
		
		//quote/kline?obj=SH600000&period=15min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.5.1测试json格式，最新1条30min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test151ThirtyMinKLine() throws Exception {
		//code="SO430011,SFIC1511,SH600000,SZ000777,B$991036,ZI000008";
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		
		period= "30min";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=30min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.5.2测试json格式，最新78条30min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test152ThirtyMinKLine() throws Exception {
		//code="HIHSCCI,SW801164";
		code=codeHI.concat(comma).concat(codeSW);
		
		period= "30min";
		start= "-78";
		count= "78";
		
		//quote/kline?obj=SH600000&period=30min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.6.1测试json格式，最新1条30min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test161SixtyMinKLine() throws Exception {
		//code="SO430011,SFIC1511,SH600000,SZ000777,B$991036,ZI000008";
		code=codeSO.concat(comma).concat(codeSF).concat(comma).concat(codeSH).concat(comma).concat(codeSZ).concat(comma).concat(codeB$).concat(comma).concat(codeZI);
		
		period= "60min";
		start= "-1";
		//count取值<=78，json格式
		count= "1";
		
		//quote/kline?obj=SH600000&period=60min&start=-1&count=1
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 1.6.2测试json格式，最新78条30min K线,股票代码是SH600000，period取值5min。
	 * @throws Exception 
	 */
	@Test
	public void test162SixtyMinKLine() throws Exception {
		//code="HIHSCCI,SW801164";
		code=codeHI.concat(comma).concat(codeSW);
		
		period= "60min";
		start= "-78";
		count= "78";
		
		//quote/kline?obj=SH600000&period=60min&start=-78&count=78
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 2.1测试最新78条1day（或1min或5min）K线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test21FieldDayKLine() throws Exception {
		code="SH600004";
		period= "1day";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu";
		
		//quote/kline?obj=SH600004&period=1day&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	
	/**
	 * 2.2测试最新78条1min K线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test22FieldMinKLine() throws Exception {
		code="SH600000";
		period= "1min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		
		//quote/kline?obj=SH600000&period=1min&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 2.3测试最新78条5minK线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test23FieldFiveMinKLine() throws Exception {
		code="SH600000";
		period= "5min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 2.4测试最新78条15minK线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test24FieldFifMinKLine() throws Exception {
		code="SH600000";
		period= "15min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 2.5测试最新78条30minK线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test25FieldThirtyMinKLine() throws Exception {
		code="SH600000";
		period= "30min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 2.6测试最新78条60minK线中field项，股票代码是SH600008。
	 * @throws Exception 
	 */
	@Test
	public void test26FieldSixtyMinKLine() throws Exception {
		code="SH600000";
		period= "60min";
		start= "-78";
		count= "78";
		//field字段首字母大写
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		
		//quote/kline?obj=SH600000&period=5min&start=-78&count=78&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&start=" + start + "&count=" + count+ "&field=" + field+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 3.1测试一段时间的日K线，begin_time&end_time,如果begintime和start一起出现，按照start处理
	 * @throws Exception 
	 */
	@Test
	public void test31PeriodDayKLine() throws Exception {
		code="SH600008";
		period= "1day";
		begin_time= "20160105-000000";
		end_time= "20160105-230000";
		
		//quote/kline?obj=SH600008&period=1day&begin_time=20150403-000000&end_time=20150403-173502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
		
	}
	
	/**
	 * 3.2测试一段时间的1min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test32PeriodMinKLine() throws Exception {
		code="SH600008";
		period= "1min";
		begin_time= "20160105-000000";
		end_time= "20160105-103502";
		
		//quote/kline?obj=SH600008&period=1min&begin_time=20151118-000000&end_time=20151118-103502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 3.3测试一段时间的5min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test33PeriodFiveMinKLine() throws Exception {
		code="SH600008";
		period= "5min";
		begin_time= "20160105-000000";
		end_time= "20160105-133502";
		
		//quote/kline?obj=SH600008&period=5min&begin_time=20151118-000000&end_time=20151118-103502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 3.4测试一段时间的15min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test34PeriodFiftMinKLine() throws Exception {
		code="SH600008";
		period= "15min";
		begin_time= "20160105-000000";
		end_time= "20160105-133502";
		
		//quote/kline?obj=SH600008&period=5min&begin_time=20151118-000000&end_time=20151118-103502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 3.5测试一段时间的30min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test35PeriodThirtyMinKLine() throws Exception {
		code="SH600008";
		period= "30min";
		begin_time= "20160105-000000";
		end_time= "20160105-133502";
		
		//quote/kline?obj=SH600008&period=5min&begin_time=20151118-000000&end_time=20151118-103502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 3.6测试一段时间的60min K线，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test36PeriodSixtyMinKLine() throws Exception {
		code="SH600008";
		period= "60min";
		begin_time= "20160105-000000";
		end_time= "20160105-133502";
		
		//quote/kline?obj=SH600008&period=5min&begin_time=20151118-000000&end_time=20151118-103502
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&begin_time=" + begin_time + "&end_time=" + end_time+ "&start=" + start + "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 4.1测试一段时间的日K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test41PeriodFieldDayKLine() throws Exception {
		code="SH600008";
		period= "1day";
		field= "ShiJian,ZuiGaoJia,ChengJiaoBiShu";
		begin_time= "20160105-000000";
		end_time= "20160105-130000";
		//quote/kline?obj=SH600008&period=1day&field=ShiJian,ZuiGaoJia,ChengJiaoBiShu&begin_time=20150403-000000&end_time=20150403-110000
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 4.2测试一段时间的1min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test42PeriodFieldMinKLine() throws Exception {
		code="SH600008";
		period= "1min";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20160105-090005";
		end_time= "20160105-100005";
		
		//quote/kline?obj=SH600008&period=1min&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu&begin_time=20151103-090000&end_time=20151103-100005
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}

	/**
	 * 4.3测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test43PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "5min";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20160105-090005";
		end_time= "20160105-100005";
		
		//quote/kline?obj=SH600008&period=5min&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu&begin_time=20151103-090000&end_time=20151103-100005
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 4.4测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test44PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "15min";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20160105-090005";
		end_time= "20160105-100005";
		
		//quote/kline?obj=SH600008&period=5min&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu&begin_time=20151103-090000&end_time=20151103-100005
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 4.5测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test45PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "30min";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20160105-090005";
		end_time= "20160105-100005";
		
		//quote/kline?obj=SH600008&period=5min&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu&begin_time=20151103-090000&end_time=20151103-100005
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 4.6测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test46PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "60min";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20160105-090005";
		end_time= "20160105-100005";
		
		//quote/kline?obj=SH600008&period=5min&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu&begin_time=20151103-090000&end_time=20151103-100005
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 5.1测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test51PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "1day";
		split="0";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20150619-000000";
		end_time= "20150623-170000";
		
		//quote/kline?obj=SH600000&period=1day&split=0&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu&begin_time=20150619-000000&end_time=20150623-170000
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period+ "&split=" + split + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 5.2测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test52PeriodFieldFiveMinKLine() throws Exception {
		code="SZ002741";
		period= "15min";
		split="1";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20151030-000000";
		end_time= "20151103-170000";
		
		//quote/kline?obj=SZ002741&period=15min&split=1&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu&begin_time=20151030-000000&end_time=20151103-170000
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period+ "&split=" + split + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
	/**
	 * 5.3测试一段时间的5min K线中field项，股票代码是SH600000。
	 * @throws Exception 
	 */
	@Test
	public void test53PeriodFieldFiveMinKLine() throws Exception {
		code="SH600008";
		period= "1day";
		split="2";
		field= "ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu,ShangZhangJiaShu,XiaDieJiaShu";
		begin_time= "20150619-000000";
		end_time= "20150623-170000";
		
		//quote/kline?obj=SH600000&period=1day&split=2&field=ShiJian,KaiPanJia,ZuiGaoJia,ZuiDiJia,ShouPanJia,ChengJiaoLiang,ChengJiaoE,ChengJiaoBiShu&begin_time=20150619-000000&end_time=20150623-170000
		String urlString = "http://" + ip + ":" +port + "/quote/kline?obj=" + code + "&period=" + period+ "&split=" + split + "&field=" + field+  "&begin_time=" + begin_time + "&end_time=" + end_time+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		/*assertNotNull("错误：股票k线为null",data);*/
		System.out.println(data);
	}
	
}
