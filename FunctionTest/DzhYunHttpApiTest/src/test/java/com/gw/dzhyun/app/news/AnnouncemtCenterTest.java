/**
 * @classnmae AnnouncemtCenterTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.news;

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
public class AnnouncemtCenterTest {
	private String token = "00000001:1540719619:8d09a5e481658ca3b09d4ae2badceb00a02473fc";

	@Test
	public void testAnnouncemtCenterAll() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/center?token="+token;
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
		assertTrue(countnum>0 && countnum==stocknews.size());
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
		}
	}
	@Test
	public void testAnnouncemtCenterAllSortAsc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/center?sort=asc&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
		System.out.println("countnum="+countnum);
		System.out.println("stocknews.size="+stocknews.size());
		assertTrue(countnum>1 && countnum==stocknews.size());
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
			als.add(i,tmpdata.getString("date"));
		}
		ArrayList<String> srcals = new ArrayList<String>(als);
		Collections.sort(als);
		assertEquals(als,srcals);
		assertTrue(countnum>1 && countnum==stocknews.size());
	}
	
	@Test
	public void testAnnouncemtCenterMulSortDesc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/announcemt/center?sort=desc&start=30&count=20&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataGongGaoXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
		System.out.println("countnum="+countnum);
		System.out.println("stocknews.size="+stocknews.size());
		assertTrue(countnum>1 && countnum>=stocknews.size() &&stocknews.size()==20);
		for(int i=0;i<stocknews.size();i++)
		{
			JSONObject tmpdata = stocknews.getJSONObject(i);
			assertTrue(tmpdata.containsKey("source"));
			assertTrue(tmpdata.containsKey("date"));
			assertTrue(tmpdata.containsKey("title"));
			assertTrue(tmpdata.containsKey("context"));
			als.add(i,tmpdata.getString("date"));
		}
		ArrayList<String> srcals = new ArrayList<String>(als);
		Collections.reverse(srcals);
		Collections.sort(als);
		assertEquals(als,srcals);

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
