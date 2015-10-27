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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
//import com.gw.dzhyun.util.MyQuoteDynaUtil;
import com.gw.dzhyun.util.MyBlockUtil;
import com.gw.dzhyun.util.TranYfloatMain;
//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.gw.dzhyun.util.TranYfloatMain;

/**
 * @author wangying001
 *
 */
public class BlockTest {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String find= "地区";           //板块类别
	String block= "股票\\\\地区板块\\\\北京市";           
	String obj= "SH000001,SH000300,SH600600,SH900902,SZ000001,SZ200028,SZ399009,SZ002002,SZ300033,SZ300159";         //按个股分
	String field= "ZuiXinJia";		//排序的字段
	String start= "0";
	String count= "30";
	String desc= "true";		   //true为降序，false为升序，默认升序
	
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
		public void t101estblockpropSort() throws Exception {
		
			String find= "市场分类"; 
		
			//block/prop?find=市场分类
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.2获取板块类别(prop类型)
        	中证行业
		 * @throws Exception 
		 */
		@Test
		public void t102estblockpropSort() throws Exception {
		
			String find= "中证行业"; 
		
			//block/prop?find=中证行业
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.3获取板块类别(prop类型)
        	中信标普行业(GICS标准)
		 * @throws Exception 
		 */
		@Test
		public void t103estblockpropSort() throws Exception {
		
			String find= "中信标普行业(GICS标准)"; 
		
			//block/prop?find=中信标普行业(GICS标准)
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
	
		/**
		 * 1.4获取板块类别(prop类型)
        	地区板块
		 * @throws Exception 
		 */
		@Test
		public void t104estblockpropSort() throws Exception {
		
			String find= "地区板块"; 
		
			//block/prop?find=地区板块
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.5获取板块类别(prop类型)
        	指数成份股
		 * @throws Exception 
		 */
		@Test
		public void t105estblockpropSort() throws Exception {
		
			String find= "指数成份股"; 
		
			//block/prop?find=指数成份股
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.6获取板块类别(prop类型)
        	其他板块
		 * @throws Exception 
		 */
		@Test
		public void t106estblockpropSort() throws Exception {
		
			String find= "其他板块"; 
		
			//block/prop?find=其他板块
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.7获取板块类别(prop类型)
        	财汇行业(GICS标准)
		 * @throws Exception 
		 */
		@Test
		public void t107estblockpropSort() throws Exception {
		
			String find= "财汇行业(GICS标准)"; 
		
			//block/prop?find=财汇行业(GICS标准)
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.8获取板块类别(prop类型)
        	国标行业
		 * @throws Exception 
		 */
		@Test
		public void t108estblockpropSort() throws Exception {
		
			String find= "国标行业"; 
		
			//block/prop?find=国标行业
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.9获取板块类别(prop类型)
        	巨潮行业分类
		 * @throws Exception 
		 */
		@Test
		public void t109estblockpropSort() throws Exception {
		
			String find= "巨潮行业分类"; 
		
			//block/prop?find=巨潮行业分类
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.10获取板块类别(prop类型)
        	证监会行业
		 * @throws Exception 
		 */
		@Test
		public void t110estblockpropSort() throws Exception {
		
			String find= "证监会行业"; 
		
			//block/prop?find=证监会行业
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.11获取板块类别(prop类型)
        	AMAC行业分类
		 * @throws Exception 
		 */
		@Test
		public void t111estblockpropSort() throws Exception {
		
			String find= "AMAC行业分类"; 
		
			//block/prop?find=AMAC行业分类
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.12获取板块类别(prop类型)
        	申万行业
		 * @throws Exception 
		 */
		@Test
		public void t112estblockpropSort() throws Exception {
		
			String find= "申万行业"; 
		
			//block/prop?find=申万行业
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.13获取板块类别(prop类型)
        	大智慧行业(经典)
		 * @throws Exception 
		 */
		@Test
		public void t113estblockpropSort() throws Exception {
		
			String find= "大智慧行业(经典)"; 
		
			//block/prop?find=大智慧行业(经典)
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.14获取板块类别(prop类型)
        	大智慧行业
		 * @throws Exception 
		 */
		@Test
		public void t114estblockpropSort() throws Exception {
		
			String find= "大智慧行业"; 
		
			//block/prop?find=大智慧行业
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 1.15获取板块类别(prop类型)
        	大智慧概念
		 * @throws Exception 
		 */
		@Test
		public void t115estblockpropSort() throws Exception {
		
			String find= "大智慧概念"; 
		
			//block/prop?find=大智慧概念
		
			String urlString = "http://" + ip + ":" +port + "/block/prop?find=" + find ;
			String type="json";
		
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockprop = MyBlockUtil.getBlockProp(ret);
			assertNotNull("错误：排序返回null",blockprop);
			System.out.println(blockprop);
		
		}
		
		/**
		 * 2.1获取板块个股(用gql板块参数)
	                             市场分类 成份股
		 * @throws Exception 
		 */
		@Test
		public void t201estblockobjSort() throws Exception {
			
			String block= "股票\\\\市场分类\\\\上证上市公司（同含AB以A股表示）"; 
			
			//block/obj?gql=block=股票\\市场分类\\上证上市公司（同含AB以A股表示）
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.2获取板块个股(用gql板块参数)
	                               中证行业 成份股
		 * @throws Exception 
		 */
		@Test
		public void t202estblockobjSort() throws Exception {
			
			String block= "股票\\\\中证行业\\\\信息技术\\\\软件与服务\\\\软件\\\\系统软件"; 
			
			//block/obj?gql=block=股票\\中证行业\\信息技术\\软件与服务\\软件\\系统软件
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.3获取板块个股(用gql板块参数)
	                               中信标普行业(GICS标准) 成份股 
		 * @throws Exception 
		 */
		@Test
		public void t203estblockobjSort() throws Exception {
			
			String block= "股票\\\\中信标普行业(GICS标准)\\\\公用事业\\\\公用事业\\\\电力公用事业\\\\电力公用事业"; 
			
			//block/obj?gql=block=股票\\中信标普行业(GICS标准)\\公用事业\\公用事业\\电力公用事业\\电力公用事业
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.4获取板块个股(用gql板块参数)
	                              地区板块 成份股
		 * @throws Exception 
		 */
		@Test
		public void t204estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\四川省\\\\乐山市"; 
			
			//block/obj?gql=block=股票\\地区板块\\四川省\\乐山市
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.5获取板块个股(用gql板块参数)
	                              指数成份股 成份股
		 * @throws Exception 
		 */
		@Test
		public void t205estblockobjSort() throws Exception {
			
			String block= "股票\\\\指数成份股\\\\中证指数\\\\中证主题指数\\\\耐用服装"; 
			
			//block/obj?gql=block=股票\\指数成份股\\中证指数\\中证主题指数\\耐用服装
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.6获取板块个股(用gql板块参数)
	                              其他板块  成份股
		 * @throws Exception 
		 */
		@Test
		public void t206estblockobjSort() throws Exception {
			
			String block= "股票\\\\其他板块\\\\ST股票"; 
			
			//block/obj?gql=block=股票\\其他板块\\ST股票
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.7获取板块个股(用gql板块参数)
	                              财汇行业(GICS标准)  成份股
		 * @throws Exception 
		 */
		@Test
		public void t207estblockobjSort() throws Exception {
			
			String block= "股票\\\\财汇行业(GICS标准)\\\\日常消费品\\\\家庭与个人用品"; 
			
			//block/obj?gql=block=股票\\财汇行业(GICS标准)\\日常消费品\\家庭与个人用品
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.8获取板块个股(用gql板块参数)
	                             国标行业  成份股
		 * @throws Exception 
		 */
		@Test
		public void t208estblockobjSort() throws Exception {
			
			String block= "股票\\\\国标行业\\\\制造业\\\\皮革、毛皮、羽毛(绒)及其制品业\\\\皮革制品制造"; 
			
			//block/obj?gql=block=股票\\国标行业\\制造业\\皮革、毛皮、羽毛(绒)及其制品业\\皮革制品制造
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.9获取板块个股(用gql板块参数)
	                            巨潮行业分类  成份股
		 * @throws Exception 
		 */
		@Test
		public void t209estblockobjSort() throws Exception {
			
			String block= "股票\\\\巨潮行业分类\\\\工业\\\\工业品\\\\通用机械\\\\其他通用机械"; 
			
			//block/obj?gql=block=股票\\巨潮行业分类\\工业\\工业品\\通用机械\\其他通用机械
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.10获取板块个股(用gql板块参数)
	                           证监会行业  成份股
		 * @throws Exception 
		 */
		@Test
		public void t210estblockobjSort() throws Exception {
			
			String block= "股票\\\\证监会行业\\\\建筑业\\\\土木工程建筑业"; 
			
			//block/obj?gql=block=股票\\证监会行业\\建筑业\\土木工程建筑业
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.11获取板块个股(用gql板块参数)
	        AMAC行业分类  成份股
		 * @throws Exception 
		 */
		@Test
		public void t211estblockobjSort() throws Exception {
			
			String block= "股票\\\\AMAC行业分类\\\\文化、体育和娱乐业"; 
			
			//block/obj?gql=block=股票\\AMAC行业分类\\文化、体育和娱乐业
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.12获取板块个股(用gql板块参数)
	                       申万行业  成份股
		 * @throws Exception 
		 */
		@Test
		public void t212estblockobjSort() throws Exception {
			
			String block= "股票\\\\申万行业\\\\电子\\\\元件\\\\被动元件"; 
			
			//block/obj?gql=block=股票\\申万行业\\电子\\元件\\被动元件
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.13获取板块个股(用gql板块参数)
	                       大智慧行业(经典)  成份股
		 * @throws Exception 
		 */
		@Test
		public void t213estblockobjSort() throws Exception {
			
			String block= "股票\\\\大智慧行业(经典)\\\\建材"; 
			
			//block/obj?gql=block=股票\\大智慧行业(经典)\\建材
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.14获取板块个股(用gql板块参数)
	                       大智慧行业  成份股
		 * @throws Exception 
		 */
		@Test
		public void t214estblockobjSort() throws Exception {
			
			String block= "股票\\\\大智慧行业\\\\运输物流\\\\铁路运输业"; 
			
			//block/obj?gql=block=股票\\大智慧行业\\运输物流\\铁路运输业
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 2.15获取板块个股(用gql板块参数)
	                       大智慧概念  成份股
		 * @throws Exception 
		 */
		@Test
		public void t215estblockobjSort() throws Exception {
			
			String block= "股票\\\\大智慧概念\\\\燃料电池"; 
			
			//block/obj?gql=block=股票\\大智慧概念\\燃料电池
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block=" + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 3.1获取板块个股(用gql板块参数)
	           （block取or ||）gql=block=股票\\地区板块\\北京市 or block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海
		 * @throws Exception 
		 */
		@Test
		public void t31estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\北京市 or block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海"; 
			
			//sort/range?gql=block=股票\\地区板块\\北京市 or block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block="  + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		/**
		 * 3.2按板块排序(用gql板块参数)
	                  （block取and &&） gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海
		 * @throws Exception 
		 */
		@Test
		public void t32estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\上海市 and block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海"; 
			  
			//sort/range?gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block="  + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
		
		/**
		 * 3.3按板块排序(用gql板块参数)
	                  （block取and &&） gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海 or block=股票\\市场分类\\全部A股
		 * @throws Exception 
		 */
		@Test
		public void t33estblockobjSort() throws Exception {
			
			String block= "股票\\\\地区板块\\\\上海市 and block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海 or block=股票\\\\市场分类\\\\全部A股"; 
			  
			//sort/range?gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海 or block=股票\\市场分类\\全部A股
			
			String urlString = "http://" + ip + ":" +port + "/block/obj?gql=block="  + block;
			String type="json";
			
			String ret =MyHttpUtil. getData(urlString,type);
			assertNotNull("错误：排序返回null",ret);
			JSONArray blockobj = MyBlockUtil.getBlockObj(ret);
			assertNotNull("错误：排序返回null",blockobj);
			System.out.println(blockobj);
			
		}
		
}
