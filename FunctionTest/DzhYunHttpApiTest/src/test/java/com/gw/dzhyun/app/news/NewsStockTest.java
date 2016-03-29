/**
 * @classnmae NewsStockTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.news;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.app.singleProperty.SinglePropertyUtilTool;
import com.gw.dzhyun.svc.singleProperty.BanKuaiChengFenGuInfo;
import com.gw.dzhyun.util.BytesHexStringTran;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * @author Litaojun
 * @date   2015年12月4日
 */
public class NewsStockTest {
	private String token = "00000001:1540719619:8d09a5e481658ca3b09d4ae2badceb00a02473fc";

	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	@Test
	public void testOneStockAllNews() throws SAXException, Exception
	{
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj="+ Obj+"&token="+token;
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getJSONArray("Data");
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	
	@Test
	public void testMultStockAllNews() throws SAXException, Exception
	{
		String Objs[] = {"SH600128","SH600000"};
		String dstObjs[] = new String[Objs.length];
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj="+ String.format("%s,%s", Objs)+"&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsnarr = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput");
		assertTrue(jsnarr!=null && jsnarr.size()==Objs.length);
		for(int i=0;i<jsnarr.size();i++)
		{
				String dstobj = jsnarr.getJSONObject(i).getString("Obj");
				int  countnum = jsnarr.getJSONObject(i).getInteger("TotalCount");
				JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(i).getJSONArray("Data");
				assertTrue(countnum>1 && countnum==stocknews.size());
				dstObjs[i] = dstobj;
		}
		assertArrayEquals(Objs,dstObjs);
	}
	@Test
	public void testOneStockCanshu() throws SAXException, Exception
	{
		String Obj = "SH600128";
		Object[] csarr = new Object[]{Obj,Integer.parseInt("2"),Integer.parseInt("3")};
		String urlstrcs = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj=%s"+"&start=%d"+"&count=%d"+"&token="+token;
		String urlstr = String.format(urlstrcs, csarr);
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getJSONArray("Data");
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
		}
		assertTrue(countnum>1 && countnum>=stocknews.size()&& stocknews.size()==3 );
		assertEquals(Obj,dstobj);
	}
	
	@Test
	public void testOneStockNewsSortAsc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj="+ Obj+"&sort=asc&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getJSONArray("Data");
		
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
			als.add(i, tmpdata.getString("date"));
		}
		ArrayList<String> srcals = new ArrayList<String>(als);
		Collections.sort(als);
		assertEquals(als,srcals);
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	
	@Test
	public void testOneStockNewsSortDesc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj="+ Obj+"&sort=desc&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getJSONArray("Data");
		
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
			als.add(i, tmpdata.getString("date"));
		}
		ArrayList<String> srcals = new ArrayList<String>(als);
		Collections.reverse(srcals);
		Collections.sort(als);
		assertEquals(als,srcals);
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	
	
	public void testOneStockAllNewsPB() throws SAXException, Exception
	{
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?output=pb&obj="+ Obj;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiOutput").getJSONObject(0).getJSONArray("Data");
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	@Test
	public void testNewsStockAllPb() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/stock?obj=SH600128&output=pb&token="+token;
		
		DzhNewsInfo.XinWenXinXiOutput uaresponse = NewsStockTest.getQuoteDyna(urlstr);
		//DzhNewsInfo.XinWenXinXiZhongXinOutput
		String obj = uaresponse.getObj();
		//System.out.println(" obj="+ obj);
		long i = uaresponse.getTotalCount();
		
		//uaresponse.getTotalCount()
		//System.out.println("i="+i);
		String title = uaresponse.getData(0).getTitle();
		//System.out.println("title="+title);
	}
	public static DzhNewsInfo.XinWenXinXiOutput getQuoteDyna(String url) throws Exception, SAXException
	{

		
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		System.out.println("url="+url);
		GetMethodWebRequest get = new GetMethodWebRequest(url);
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(get);  
		byte[] mybtt = response.getBytes();
		//System.out.println("mybtt.length="+mybtt.length);
		//System.out.println("mybtt="+mybtt);
		String a = BytesHexStringTran.bytesToHexString(mybtt);
		byte[] mybt = NewsCenterTest.toByteArray(response.getInputStream());
		//System.out.println("mybt.length="+mybt.length);
		//System.out.println("mybt="+mybt);
		String b = BytesHexStringTran.bytesToHexString(mybt);
		//System.out.println("a="+a);
		//System.out.println("b="+b);
		DzhNewsInfo.XinWenXinXiOutput ggxxop = DzhNewsInfo.XinWenXinXiOutput.parseFrom(mybt);
		//DzhNewsInfo.GongGaoXinXi uaresponse = DzhNewsInfo.GongGaoXinXi.parseFrom(mybt);
		//String titlename = uaresponse.getTitle();
		//System.out.println("titlename="+titlename);
		return ggxxop;

	}
	public void testAddNewsStok()
	{
		long  a = 6241054863724118023L;
	}
	public static void main(String[] args) throws SAXException, Exception {
		// TODO Auto-generated method stub
		NewsStockTest nst = new NewsStockTest();
		nst.testNewsStockAllPb();
		ArrayList<String> alss = new ArrayList<String>();
		alss.add(0, "20150401");
		alss.add(1, "20150701");
		alss.add(2, "20150801");
		alss.add(3, "20150901");
		ArrayList<String> als = new ArrayList<String>();
		als.add(0, "20150801");
		als.add(1, "20150901");
		als.add(2, "20150701");
		als.add(3, "20150401");
		if(als.equals(alss))
			System.out.println("xxx");
		else
			System.out.println("yyyy");
		//assertEquals(als,alss);
		ArrayList<String> srcals = new ArrayList<String>(als);
		Collections.sort(als);
		System.out.println(als);
		System.out.println(srcals);
		if(als.equals(alss))
			System.out.println("xxx11");
		else
			System.out.println("yyyy11");
		assertEquals(als,alss);
		System.out.println(als);
		Collections.reverse(als);
		System.out.println(als);
		
		
	}

}
