/**
 * @classnmae AnnouncemtStock.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.news;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;

/**
 * @author Litaojun
 * @date   2015年12月4日
 */
public class AnnouncemtStock {
	private String token = "00000001:1540719619:8d09a5e481658ca3b09d4ae2badceb00a02473fc";
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	@Test
	public void testOneStockAllAnnouncemt() throws SAXException, Exception
	{
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?obj="+ Obj+"&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getJSONArray("Data");
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	
	@Test
	public void testMultStockAllAnnouncemt() throws SAXException, Exception
	{
		String Objs[] = {"SH600128","SH600000"};
		String dstObjs[] = new String[Objs.length];
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?obj="+ String.format("%s,%s", Objs)+"&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsnarr = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput");
		assertTrue(jsnarr!=null && jsnarr.size()==Objs.length);
		for(int i=0;i<jsnarr.size();i++)
		{
				String dstobj = jsnarr.getJSONObject(i).getString("Obj");
				int  countnum = jsnarr.getJSONObject(i).getInteger("TotalCount");
				JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(i).getJSONArray("Data");
				assertTrue(countnum>1 && countnum==stocknews.size());
				dstObjs[i] = dstobj;
		}
		System.out.println(dstObjs[0]+dstObjs[1]);
		assertArrayEquals(Objs,dstObjs);
	}
	@Test
	public void testOneStockCanshu() throws SAXException, Exception
	{
		String Obj = "SH600000";
		Object[] csarr = new Object[]{Obj,Integer.parseInt("2"),Integer.parseInt("3")};
		String urlstrcs = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?obj=%s"+"&start=%d"+"&count=%d"+"&token="+token;
		
		String urlstr = String.format(urlstrcs, csarr);
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getJSONArray("Data");
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
	public void testOneStockAnnouncemtSortAsc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?obj="+ Obj+"&sort=asc&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getJSONArray("Data");
		
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
	public void testOneStockAnnouncemtSortDesc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?obj="+ Obj+"&sort=desc&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getJSONArray("Data");
		
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
	
	@Test
	public void testOneStockAllNewsPB() throws SAXException, Exception
	{
		String Obj = "SH600128";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/stock?output=pb&obj="+ Obj+"&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		String dstobj = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getString("Obj");
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiOutput").getJSONObject(0).getJSONArray("Data");
		assertTrue(countnum>1 && countnum==stocknews.size());
		assertEquals(Obj,dstobj);
	}
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
