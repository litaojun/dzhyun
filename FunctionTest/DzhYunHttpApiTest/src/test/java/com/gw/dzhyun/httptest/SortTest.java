/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *
 *负责处理大行情各字段的实时排序查询请求
	涉及字段：
	1、market市场:SH/SZ/B$/SO
	2、block板块
	3、obj个股
	4、field（必须含）
	5、desc:true为降序，false为升序，默认升序（0值排最后）
	6、start
	7、count
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
import com.gw.dzhyun.util.MySortUtil;
import com.gw.dzhyun.util.TranYfloatMain;
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
public class SortTest {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String market= "SH";            //按市场分
	String block= "股票\\\\地区板块\\\\北京市";           
	String obj= "SH000001,SH000300,SH600600,SH900901,SZ000005,SZ200028,SZ399009,SO400005,SO430010,B$993060";         //按个股分
	String field= "ZuiXinJia";		//排序的字段
	String start= "0";
	String count= "30";
	String desc= "true";		   //true为降序，false为升序，默认升序
	String token= "5158e9ef5b9e4c059173882648549ac7";
	
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
	 * 1.1按市场查询排序(SH、SZ、B$、SO)
        market=SH&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t101estmarketSHSort() throws Exception {
		
		String market= "SH"; 
		String field= "ZuiXinJia";
		
		//sort/range?market=SH&field=ZuiXinJia&desc=true
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&desc=" + desc + "&token=" + token ;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort+"\n");
		
		//yfloat转换
		/*JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		System.out.println(jsonyfloatResponse);*/
		
	}
	
	/**
	 * 1.2按市场查询排序(SH、SZ、B$、SO)
        market=SZ&desc=false 升序
	 * @throws Exception 
	 */
	@Test
	public void t102estmarketSZSort() throws Exception {
		
		String market= "SZ"; 
		String desc= "false";
		
		//sort/range?market=SZ&field=ZuiXinJia&desc=false
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&desc=" + desc  + "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
	
	/**
	 * 1.3按市场查询排序(SH、SZ、B$、SO)
        market=B$&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t103estmarketB$Sort() throws Exception {
		
		String market= "B$"; 
		String desc= "true";
		
		//sort/range?market=B$&field=ZuiXinJia&desc=true
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&desc=" + desc  + "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
	
	/**
	 * 1.4按市场查询排序(SH、SZ、B$、SO)
        market=SO&desc=false 升序
	 * @throws Exception 
	 */
	@Test
	public void t104estmarketSOSort() throws Exception {
		
		String market= "SO"; 
		String desc= "false";
		
		//sort/range?market=SO&field=ZuiXinJia&desc=false
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&desc=" + desc  + "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
	
	/**
	 * 1.5按市场查询排序
        market=SH,SZ&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t105estmarketSort() throws Exception {
		
		String market= "SH,SZ";    
		
		//sort/range?market=SH,SZ&field=ZuiGaoJia&desc=true
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&desc=" + desc+ "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
	
	/**
	 * 1.6按市场 desc缺省排序
        market=SH,SZ,B$,SO desc默认升序
	 * @throws Exception 
	 */
	@Test
	public void t106estmarketdefaultSort() throws Exception {
		
		String market= "SH,SZ,B$,SO";    
		
		//sort/range?market=SH,SZ,B$,SO&field=ZuiXinJia 
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field + "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
	
	/**
	 * 1.7按市场排序  筛选前N条
	     start=0&count=30 (排序前30条)
	 * @throws Exception 
	 */
	@Test
	public void t107estmarketcountSort() throws Exception {
			
		String market= "SH";
		String start= "0";
		String count= "30";
			
		//sort/range?market=SH&field=ZuiXinJia&start=0&count=30 
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+  "&start=" + start + "&count=" +count+ "&token=" + token ;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
	
	/**
	 * 1.8按市场排序  筛选前N条（不含start）
	    count=30，不含start(排序前30条)
	 * @throws Exception 
	 */
	@Test
	public void t108estmarketcountSort() throws Exception {
			
		String market= "SZ";
		String count= "30";
			
		//sort/range?market=SZ&field=ZuiXinJia&desc=true&count=30
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+  "&desc=" + desc + "&count=" +count + "&token=" + token;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}	
		
	/**
	 * 1.9按市场排序  筛选前N条（count=0）
	    start=0&count=0 (全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t109estmarketcountSort() throws Exception {
			
		String market= "B$";
		String start= "0";
		String count= "0";
			
		//sort/range?market=SH&field=ZuiXinJia&desc=true&start=0&count=0 
		
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field + "&desc=" + desc+ "&start=" + start + "&count=" +count+ "&token=" + token;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}	
		
	/**
	 * 1.10按市场排序  筛选前N条（不含count）
	     start=0 ,不含count(全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t110estmarketcountSort() throws Exception {
			
		String market= "SO";
		String field= "ZuiXinJia";
		String start= "0";
			
		//sort/range?market=SZ&field=ZuiXinJia&start=0
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field  + "&start=" + start+ "&token=" + token;
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 1.11按市场  field排序
	     field=ZuiGaoJia
	 * @throws Exception 
	 */
	@Test
	public void t111estmarketfieldSort() throws Exception {
				
		String market= "SZ";
		String field= "ZuiGaoJia";
				
		//sort/range?market=SZ&field=ZuiGaoJia
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?market="  + market + "&field=" + field+ "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}	
		
	/**
	 * 2.1按板块排序(用gql板块参数)
	       gql=block=股票\\地区板块\\上海市&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t201estblockSort() throws Exception {
			
		String block= "股票\\\\地区板块\\\\北京市"; 
		String desc= "false";
			
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiXinJia&desc=true
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block=" + block + "&field=" + field+ "&desc=" + desc + "&token=" + token;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(ret);	
	}
		
	/**
	 * 2.2按板块排序(用gql板块参数)
	          （block取or ||）gql=block=股票\\地区板块\\上海市 or block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海&desc=false 升序
	 * @throws Exception 
	 */
	@Test
	public void t202estblockSort() throws Exception {
			
		String block= "股票\\\\地区板块\\\\北京市 or block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海"; 
		String desc= "true";
			
		//sort/range?gql=block=股票\\地区板块\\北京市 or block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海&field=ZuiXinJia&desc=false
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field+ "&desc=" + desc+ "&token=" + token ;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
			
	}
		
	/**
	 * 2.3按板块排序(用gql板块参数)
	                 （block取and &&） gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海
           desc默认升序
	 * @throws Exception 
	 */
	@Test
	public void t203estblockSort() throws Exception {
			
		String block= "股票\\\\地区板块\\\\上海市 and block=股票\\\\指数成份股\\\\大智慧策略指数\\\\板块指数\\\\上海"; 
			  
		//sort/range?gql=block=股票\\地区板块\\上海市 and block=股票\\指数成份股\\大智慧策略指数\\板块指数\\上海&field=ZuiXinJia
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field+ "&token=" + token;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
		
	/**
	 * 2.4按板块排序  筛选前N条
	     start=0&count=30 (排序前30条)
	 * @throws Exception 
	 */
	@Test
	public void t204estblockcountSort() throws Exception {
				
		String start= "0";
		String count= "30";
				
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiXinJia&start=0&count=30
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field+  "&start=" + start + "&count=" +count + "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
		
	/**
	 * 2.5按板块排序  筛选前N条（不含start）
	    count=30，不含start(排序前30条)
	 * @throws Exception 
	 */
	@Test
	public void t205estblockcountSort() throws Exception {
				
		String count= "30";
				
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiXinJia&desc=true&count=30
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field+  "&desc=" + desc + "&count=" +count + "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}	
			
	/**
	 * 2.6按板块排序  筛选前N条（count=0）
	    start=0&count=0 (全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t206estblockcountSort() throws Exception {
				
		String start= "0";
		String count= "0";
				
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiXinJia&desc=true&start=0&count=0
			
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block  + "&field=" + field + "&desc=" + desc+ "&start=" + start + "&count=" +count+ "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	

	}	
			
	/**
	 * 2.7按板块  筛选前N条排序（不含count）
	     start=0 ,不含count(全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t207estblockcountSort() throws Exception {
				
		String field= "ZuiXinJia";
		String start= "0";
				
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiXinJia&start=0
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field  + "&start=" + start+ "&token=" + token;
		String type="json";
			
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
			
	/**
	 * 2.8按板块 field排序
	     field=ZuiGaoJia
	 * @throws Exception 
	 */
	@Test
	public void t208estblockfieldSort() throws Exception {
					
		String field= "ZuiGaoJia";
					
		//sort/range?gql=block=股票\\地区板块\\北京市&field=ZuiGaoJia
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?gql=block="  + block + "&field=" + field+ "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}	
			
		
	/**
	 * 3.1按个股查询排序(SH、SZ、SH,SZ)
	     obj=SH600000&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t301estobjSHSort() throws Exception {
				
		String obj= "SH600000"; 
		String field= "ZuiXinJia";
				
		//sort/range?obj=SH600000&field=ZuiXinJia&desc=true
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj=" + obj + "&field=" + field+ "&desc=" + desc + "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
			
	/**
	 * 3.2按个股查询排序(SH、SZ、SH,SZ)
	     obj=SH600000&desc=false 升序
	 * @throws Exception 
	 */
	@Test
	public void t302estobjSOSort() throws Exception {
				
		String obj= "SO400064"; 
		String field= "ZuiXinJia";
				
		//sort/range?obj=SO400064&field=ZuiXinJia&desc=false
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj=" + obj + "&field=" + field + "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
		}
		
	/**
	 * 3.3按个股查询排序(SH、SZ、SH,SZ)
	      obj=SH600000,SZ000001&desc=true 降序
	 * @throws Exception 
	 */
	@Test
	public void t303estobjSort() throws Exception {
			
		String obj= "SH600000,SZ000001";    
				
		//sort/range?obj=SH600000,SZ000001&field=ZuiXinJia&desc=true
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&desc=" + desc+ "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
		
	/**
	 * 3.4按个股查询排序(SH、SZ、SO、B$)
	      obj=SH600000,SZ000001&desc缺省排序
	 * @throws Exception 
	 */
	@Test
	public void t304estobjAscSort() throws Exception {
				
		String obj= "SH600000,SZ000001,SO400002,B$998002";  
			
				
		//sort/range?obj=SH600000,SZ000001,SO400002,B$998002&field=ZuiXinJia&desc=false
				
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+  "&token=" + token;
		String type="json";
				
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
			
		
	/**
	 * 3.5按个股 筛选前N条排序
	      start=0&count=2 (排序前2条)
	 * @throws Exception 
	 */
	@Test
	public void t305estobjcountSort() throws Exception {
					
		String obj= "SH600000,SO400002,SH600600,SZ000001";
		String start= "0";
		String count= "2";
					
		//sort/range?obj=SH600000,SO400002,SH600600,SZ000001&field=ZuiXinJia&start=0&count=2
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+  "&start=" + start + "&count=" +count + "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
			
	/**
	 * 3.6按个股 筛选前N条排序（不含start）
	      count=2，不含start(排序前2条)
	 * @throws Exception 
	 */
	@Test
	public void t306estobjcountSort() throws Exception {
					
		String obj= "SH600000,SH600600,SZ000001,B$998002";
		String count= "2";
					
		//sort/range?obj=SH600000,SH600600,SZ000001&field=ZuiXinJia&desc=true&count=2
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+  "&desc=" + desc + "&count=" +count + "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}	
				
	/**
	 * 3.7按个股 筛选前N条排序（count=0）
	      start=0&count=0 (全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t307estobjcountSort() throws Exception {
					
		String obj= "SH600000,SH600600,SZ000001,SO430544,B$993074";
		String start= "0";
		String count= "0";
					
		//sort/range?obj=SH600000,SH600600,SZ000001&field=ZuiXinJia&desc=true&start=0&count=0 
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field + "&desc=" + desc+ "&start=" + start + "&count=" +count+ "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}	
				
	/**
	 * 3.8按个股 筛选前N条排序（不含count）
	       start=0 ,不含count(全部代码排序)
	 * @throws Exception 
	 */
	@Test
	public void t308estobjcountSort() throws Exception {
					
		String obj= "SH600638,SH600600,SZ000001,SO400002,B$993806";
		String field= "ZuiXinJia";
		String start= "0";
					
		//sort/range?obj=SH600000,SH600600,SZ000001&field=ZuiXinJia&start=0
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field  + "&start=" + start+ "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
				
	/**
	 * 3.9.1按个股 field排序,字段：个股信息
	       field=ZhongWenJianCheng
	 * @throws Exception 
	 */
	@Test
	public void t3091estobjfieldSort() throws Exception {
						
		String field= "ZhongWenJianCheng";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.9.2按个股 field排序,字段：个股信息
	       field=LeiXing
	 * @throws Exception 
	 */
	@Test
	public void t3092estobjfieldSort() throws Exception {
						
		String field= "LeiXing";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	* 3.9.3按个股 field排序,字段：个股信息
	       field=ZiLeiXing
	 * @throws Exception 
	 */
	@Test
	public void t3093estobjfieldSort() throws Exception {
						
		String field= "ZiLeiXing";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.9.4按个股 field排序,字段：个股信息
	       field=LeiXingMingCheng
	 * @throws Exception 
	 */
	@Test
	public void t3094estobjfieldSort() throws Exception {
						
		String field= "LeiXingMingCheng";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	* 3.9.5按个股 field排序,字段：个股信息
	       field=ChengJiaoLiangDanWei
	 * @throws Exception 
	 */
	@Test
	public void t3095estobjfieldSort() throws Exception {
						
		String field= "ChengJiaoLiangDanWei";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.1按个股 field排序,字段：动态行情
	       field=ZuiXinJia
	 * @throws Exception 
	 */
	@Test
	public void t3101estobjfieldSort() throws Exception {
						
		String field= "ZuiXinJia";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
		}
		
	/**
	 * 3.10.2按个股 field排序,字段：动态行情
	       field=KaiPanJia
	 * @throws Exception 
	 */
	@Test
	public void t3102estobjfieldSort() throws Exception {
						
		String field= "KaiPanJia";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
					
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort+"\n");
		}
		
	/**
	 * 3.10.3按个股 field排序,字段：动态行情
	       field=ZuiGaoJia
	 * @throws Exception 
	 */
	@Test
	public void t3103estobjfieldSort() throws Exception {
						
		String field= "ZuiGaoJia";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.4按个股 field排序,字段：动态行情
	       field=ZuiDiJia
	 * @throws Exception 
	 */
	@Test
	public void t3104estobjfieldSort() throws Exception {
						
		String field= "ZuiDiJia";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.5按个股 field排序,字段：动态行情
	       field=ZuoShou
	 * @throws Exception 
	 */
	@Test
	public void t3105estobjfieldSort() throws Exception {
						
		String field= "ZuoShou";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.6按个股 field排序,字段：动态行情
	         field=JunJia
	 * @throws Exception 
	 */
	@Test
	public void t3106estobjfieldSort() throws Exception {
						
		String field= "JunJia";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.7按个股 field排序,字段：动态行情
	       field=ZhangDie
	 * @throws Exception 
	 */
	@Test
	public void t3107estobjfieldSort() throws Exception {
						
		String field= "ZhangDie";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.8按个股 field排序,字段：动态行情
	       field=ZhangFu
	 * @throws Exception 
	 */
	@Test
	public void t3108estobjfieldSort() throws Exception {
						
		String field= "ZhangFu";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.9按个股 field排序,字段：动态行情
	       field=ZhenFu
	 * @throws Exception 
	 */
	@Test
	public void t3109estobjfieldSort() throws Exception {
						
		String field= "ZhenFu";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.10按个股 field排序,字段：动态行情
	          field=ChengJiaoLiang
	 * @throws Exception 
	 */
	@Test
	public void t3110estobjfieldSort() throws Exception {
						
		String field= "ChengJiaoLiang";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	* 3.10.11按个股 field排序,字段：动态行情
	       field=XianShou
	 * @throws Exception 
	 */
	@Test
	public void t3111estobjfieldSort() throws Exception {
						
		String field= "XianShou";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);			
	}
		
	/**
	* 3.10.12按个股 field排序,字段：动态行情
	       field=ChengJiaoE
	 * @throws Exception 
	 */
	@Test
	public void t3112estobjfieldSort() throws Exception {
						
		String field= "ChengJiaoE";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
					
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);	
	}
		
	/**
	 * 3.10.13按个股 field排序,字段：动态行情
	      field=ZongChengJiaoBiShu/总笔
	 * @throws Exception 
	 */
	@Test
	public void t3113estobjfieldSort() throws Exception {
						
		String field= "ZongChengJiaoBiShu";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.14按个股 field排序,字段：动态行情
	       field=NeiPan
	 * @throws Exception 
	 */
	@Test
	public void t3114estobjfieldSort() throws Exception {
						
		String field= "NeiPan";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.15按个股 field排序,字段：动态行情
	       field=WaiPan
	 * @throws Exception 
	 */
	@Test
	public void t3115estobjfieldSort() throws Exception {
						
		String field= "WaiPan";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.16按个股 field排序,字段：动态行情
	       field=ShiYingLv
	 * @throws Exception 
	 */
	@Test
	public void t3116estobjfieldSort() throws Exception {
						
		String field= "ShiYingLv";
		String obj="SH000001,SH000300,SH600600,SH900901,SZ200028,SZ399009,B$993060,SO400005,SO400010,SZ000001,SZ000005,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.17按个股 field排序,字段：动态行情
	       field=ShiJingLv
	 * @throws Exception 
	 */
	@Test
	public void t3117estobjfieldSort() throws Exception {
						
		String field= "ShiJingLv";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.18按个股 field排序,字段：动态行情
	       field=ZongShiZhi
	 * @throws Exception 
	 */
	@Test
	public void t3118estobjfieldSort() throws Exception {
						
		String field= "ZongShiZhi";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.19按个股 field排序,字段：动态行情
	       field=LiuTongShiZhi
	 * @throws Exception 
	 */
	@Test
	public void t3119estobjfieldSort() throws Exception {
						
		String field= "LiuTongShiZhi";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.20按个股 field排序,字段：动态行情
	       field=WeiBi
	 * @throws Exception 
	 */
	@Test
	public void t3120estobjfieldSort() throws Exception {
						
		String field= "WeiBi";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.21按个股 field排序,字段：动态行情
	       field=WeiCha
	 * @throws Exception 
	 */
	@Test
	public void t3121estobjfieldSort() throws Exception {
						
		String field= "WeiCha";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.10.22按个股 field排序,字段：动态行情
	       field=FenZhongZhangFu5
	 * @throws Exception 
	 */
	@Test
	public void t3122estobjfieldSort() throws Exception {
						
		String field= "FenZhongZhangFu5";
		String obj="SH000001,SH000300,SZ399009,B$993060,SO400005,SO400006,SZ000018,SZ000001,SH900901,SH600600,SZ000029,SO430010";
					
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ShiYingLv
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);		
	}
		
	/**
	 * 3.11.01按个股 field排序,字段：买卖盘
	       field=WeiTuoMaiRuJia1
	 * @throws Exception 
	 */
	@Test
	public void t32101estobjfieldSort() throws Exception {
						
		String field= "WeiTuoMaiRuJia1";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort+"\n");					
	}
		
	/**
	 * 3.11.02按个股 field排序,字段：买卖盘
	       field=WeiTuoMaiChuJia1
	 * @throws Exception 
	 */
	@Test
	public void t32102estobjfieldSort() throws Exception {
						
		String field= "WeiTuoMaiChuJia1";
						
		//sort/range?obj=SH000001,SH000300,SH600600,SH900901,SZ000001,SZ200028,SZ399009&field=ZhongWenJianCheng
						
		String urlString = "http://" + ip + ":" +port + "/sort/range?&obj="  + obj + "&field=" + field+ "&token=" + token;
		String type="json";
						
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：排序返回null",ret);
		JSONArray sort = MySortUtil.getsortByObj(ret);
		assertNotNull("错误：排序返回null",sort);
		System.out.println(sort);
	}
		
}
