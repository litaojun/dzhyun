/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *
 *负责板块统计数据查询请求
 *统计数据包括：
 *成交额，领涨股，停牌家数，涨停跌停，流通市值，总市值，涨跌平，股票个数，平均静态市盈率
 *ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
     涉及字段：
   1、blockstat?gql=block 板块类别
   2、field 
   3、or、and
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
import com.gw.dzhyun.util.MyblockstatisticsUtil;
import com.gw.dzhyun.util.TranYfloatMain;
//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.gw.dzhyun.util.TranYfloatMain;

/**
 * 
 * @author Wangying001
 * @date 2016年1月8日
 */
public class blockstatistics {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String block= "股票\\\\地区板块\\\\北京市";    //板块类别       
	String field= "ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv";
	//板块统计的字段
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
		 * 1.1获取板块类别(prop类型)
        	市场分类
		 * @throws Exception 
		 */
		@Test
		public void t101estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\市场分类\\\\上证A股"; 
		
			//blockstat?gql=block=股票\\市场分类\\上证A股&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
			
			//yfloat转换
			/*JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
			  System.out.println(jsonyfloatResponse);*/
		
		}
		
		/**
		 * 1.2获取板块类别(prop类型)
        	中证行业
		 * @throws Exception 
		 */
		@Test
		public void t102estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\中证行业\\\\信息技术\\\\软件与服务\\\\软件\\\\系统软件"; 
		
			//blockstat?gql=block=股票\\中证行业\\信息技术\\软件与服务\\软件\\系统软件&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.3获取板块类别(prop类型)
        	中信标普行业(GICS标准)
		 * @throws Exception 
		 */
		@Test
		public void t103estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\中信标普行业（GICS标准）\\\\公用事业\\\\公用事业\\\\电力公用事业\\\\电力公用事业"; 
		
			//blockstat?gql=block=股票\\中信标普行业（GICS标准）\\公用事业\\公用事业\\电力公用事业\\电力公用事业&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
	
		/**
		 * 1.4获取板块类别(prop类型)
        	地区板块
		 * @throws Exception 
		 */
		@Test
		public void t104estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\地区板块\\\\四川省\\\\乐山市"; 
		
			//blockstat?gql=block=股票\\地区板块\\四川省\\乐山市&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.5获取板块类别(prop类型)
        	指数成份股
		 * @throws Exception 
		 */
		@Test
		public void t105estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\指数成份股\\\\中证指数\\\\中证主题指数\\\\耐用服装"; 
		
			//blockstat?gql=block=股票\\指数成份股\\中证指数\\中证主题指数\\耐用服装&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.6获取板块类别(prop类型)
        	其他板块
		 * @throws Exception 
		 */
		@Test
		public void t106estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\其他板块\\\\ST股票"; 
		
			//blockstat?gql=block=股票\\其他板块\\ST股票&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.7获取板块类别(prop类型)
        	财汇行业(GICS标准)
		 * @throws Exception 
		 */
		@Test
		public void t107estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\财汇行业（GICS标准）\\\\日常消费品\\\\家庭与个人用品"; 
		
			//blockstat?gql=block=股票\\财汇行业（GICS标准）\\日常消费品\\家庭与个人用品&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.8获取板块类别(prop类型)
        	国标行业
		 * @throws Exception 
		 */
		@Test
		public void t108estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\国标行业\\\\制造业\\\\皮革、毛皮、羽毛（绒）及其制品业\\\\皮革制品制造"; 
		
			//blockstat?gql=block=股票\\国标行业\\制造业\\皮革、毛皮、羽毛（绒）及其制品业\\皮革制品制造&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.9获取板块类别(prop类型)
        	巨潮行业分类
		 * @throws Exception 
		 */
		@Test
		public void t109estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\巨潮行业分类\\\\工业\\\\工业品\\\\通用机械\\\\其他通用机械"; 
		
			//blockstat?gql=block=股票\\巨潮行业分类\\工业\\工业品\\通用机械\\其他通用机械&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.10获取板块类别(prop类型)
        	证监会行业
		 * @throws Exception 
		 */
		@Test
		public void t110estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\证监会行业\\\\建筑业\\\\土木工程建筑业"; 
		
			//blockstat?gql=block=股票\\证监会行业\\建筑业\\土木工程建筑业&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.11获取板块类别(prop类型)
        	AMAC行业分类
		 * @throws Exception 
		 */
		@Test
		public void t111estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\AMAC行业分类\\\\文化、体育和娱乐业"; 
		
			///blockstat?gql=block=股票\\AMAC行业分类\\文化、体育和娱乐业&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.12获取板块类别(prop类型)
        	申万行业
		 * @throws Exception 
		 */
		@Test
		public void t112estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\申万行业\\\\电子\\\\元件\\\\被动元件"; 
		
			//blockstat?gql=block=股票\\申万行业\\电子\\元件\\被动元件&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.13获取板块类别(prop类型)
        	大智慧行业(经典)
		 * @throws Exception 
		 */
		@Test
		public void t113estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\大智慧行业（经典）\\\\建材"; 
		
			//blockstat?gql=block=股票\\大智慧行业（经典）\\建材&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.14获取板块类别(prop类型)
        	大智慧行业
		 * @throws Exception 
		 */
		@Test
		public void t114estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\大智慧行业\\\\运输物流\\\\铁路运输业"; 
		
			//blockstat?gql=block=股票\\大智慧行业\\运输物流\\铁路运输业&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 1.15获取板块类别(prop类型)
        	大智慧概念
		 * @throws Exception 
		 */
		@Test
		public void t115estblockstatisticspropSort() throws Exception {
		
			String block= "股票\\\\大智慧概念\\\\燃料电池"; 
		
			//blockstat?gql=block=股票\\大智慧概念\\燃料电池&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
		
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block + "&field=" + field+ "&token=" + token;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockstatisticsprop = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockstatisticsprop);
			System.out.println(blockstatisticsprop);
		
		}
		
		/**
		 * 2.1获取板块个股(用gql板块参数)
	                             市场分类 成份股
		 * @throws Exception 
		 */
		@Test
		public void t201estblockobjSort() throws Exception {
			
			String block= "股票\\\\市场分类\\\\上证A股 or block=股票\\\\市场分类\\\\上证B股"; 
			
			//blockstat?gql=block=股票\\市场分类\\上证A股 or block=股票\\市场分类\\上证B股&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
			
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block+ "&field=" + field+ "&token=" + token;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockobj = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.2获取板块个股(用gql板块参数)
	                               中证行业 成份股
		 * @throws Exception 
		 */
		@Test
		public void t202estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\上海市 and block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海"; 
			
			//blockstat?gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
			
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block+ "&field=" + field+ "&token=" + token;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockobj = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.3获取板块个股(用gql板块参数)
	                               中信标普行业(GICS标准) 成份股 
		 * @throws Exception 
		 */
		@Test
		public void t203estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\上海市 and block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海 or block=股票\\\\市场分类\\\\全部A股"; 
			
			//blockstat?gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海 or block=股票\\市场分类\\全部A股&field=ChengJiaoE,LingZhangGu,TingPaiJiaShu,ZhangTingDieTing,LiuTongShiZhi,ZongShiZhi,ZhangDiePing,GuPiaoGeShu,PingJunJingTaiShiYingLv
			
			String urlString = "http://" + ip + ":" +port + "/blockstat?gql=block=" + block+ "&field=" + field+ "&token=" + token;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：板块统计返回null",ret);
			JSONArray blockobj = MyblockstatisticsUtil.getblockstatisticsProp(ret);
			assertNotNull("错误：板块统计返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
}
