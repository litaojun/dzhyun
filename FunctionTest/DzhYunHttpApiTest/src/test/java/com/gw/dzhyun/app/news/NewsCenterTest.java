/**
 * @classnmae NewsCenterTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.BytesHexStringTran;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * @author Litaojun
 * @date   2015年12月4日
 */
public class NewsCenterTest {
	private String token = "00000001:1540719619:8d09a5e481658ca3b09d4ae2badceb00a02473fc";
	public static byte[] toByteArray(InputStream input)
			  throws IOException
			{
			  ByteArrayOutputStream output = new ByteArrayOutputStream();
			  copy(input, output);
			  return output.toByteArray();
			}
	public static int copy(InputStream input, OutputStream output)
			  throws IOException
			{
			  long count = copyLarge(input, output);
			  if (count > 2147483647L) {
			    return -1;
			  }
			  return (int)count;
			}

			public static long copyLarge(InputStream input, OutputStream output)
			  throws IOException
			{
			  byte[] buffer = new byte[4096];
			  long count = 0L;
			  int n = 0;
			  while (-1 != (n = input.read(buffer))) {
			    output.write(buffer, 0, n);
			    count += n;
			  }
			  return count;
			}
	public static DzhNewsInfo.XinWenXinXiZhongXinOutput getQuoteDyna(String url) throws Exception, SAXException
	{

		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		//System.out.println("url="+url);
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
		DzhNewsInfo.XinWenXinXiZhongXinOutput ggxxop = DzhNewsInfo.XinWenXinXiZhongXinOutput.parseFrom(mybt);
		//DzhNewsInfo.GongGaoXinXi uaresponse = DzhNewsInfo.GongGaoXinXi.parseFrom(mybt);
		//String titlename = uaresponse.getTitle();
		//System.out.println("titlename="+titlename);
		return ggxxop;

	}

	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * @throws Exception 
	 * @throws SAXException 
	 * 
	 */
	@Test
	public void testNewsCenterAll() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/center?token="+token;
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
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
	public void testNewsCenterAllSortAsc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/center?sort=asc&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
		//System.out.println("countnum="+countnum);
		//System.out.println("stocknews.size="+stocknews.size());
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
	public void testNewsCenterMulSortDesc() throws SAXException, Exception
	{
		ArrayList<String> als = new ArrayList<String>();
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/center?sort=desc&start=30&count=20&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		int  countnum = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getInteger("TotalCount");
		JSONArray stocknews = data.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput").getJSONObject(0).getJSONArray("data");
		//System.out.println("countnum="+countnum);
		//System.out.println("stocknews.size="+stocknews.size());
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
	
	
	@Test
	public void testNewsCenterAllPb() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/news/center?output=pb&token="+token;
		
		DzhNewsInfo.XinWenXinXiZhongXinOutput uaresponse = NewsCenterTest.getQuoteDyna(urlstr);
		//DzhNewsInfo.XinWenXinXiZhongXinOutput
		long i = uaresponse.getTotalCount();
		
		//uaresponse.getTotalCount()
		//System.out.println("i="+i);
		String title = uaresponse.getData(0).getTitle();
		//System.out.println("title="+title);
	}
	public static void main(String[] args) throws SAXException, Exception {
		// TODO Auto-generated method stub
		NewsCenterTest nct = new NewsCenterTest();
		nct.testNewsCenterAllPb();
	}

}
