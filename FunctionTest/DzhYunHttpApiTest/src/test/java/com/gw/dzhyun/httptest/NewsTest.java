package com.gw.dzhyun.httptest;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class NewsTest {
	
	@ Test
	public void testObjNewTypeonea() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=DESC&start=0&count=5&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testObjNewTypeoneb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=DESC&start=0&count=5&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testObjNewTypeonec() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=DESC&start=0&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testObjNewTypeoned() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=DESC&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testObjNewTypeonee() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=ASC&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testObjNewTypeonef() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=ASC&start=0&count=5&obj=SH600128&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}

	
	
	@ Test
	public void testNewTypeonea() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=DESC&start=0&count=5&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testNewTypeoneb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=DESC&start=0&count=5&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testNewTypeonec() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=DESC&start=0&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
		TreeSet<String> tst = new TreeSet<String>();
		JSONArray sa = tranjson.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput");
		for(int i=0;i<sa.size();i++)
		{
			String title = sa.getJSONObject(i).getString("title");
			String source = sa.getJSONObject(i).getString("source");
			String context = sa.getJSONObject(i).getString("context");
			String date = sa.getJSONObject(i).getString("date");
			String tmpstr = title+source+context+date;
			if(tst.contains(tmpstr))
				System.out.println(tmpstr);
			tst.add(tmpstr);
			     
		}
		System.out.println(tst.size()+"xxxxxxxxx"+sa.size());
		assertTrue(tst.size()==sa.size());
		
	}
	@ Test
	public void testNewTypeoned() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=DESC&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
		TreeSet<String> tst = new TreeSet<String>();
		JSONArray sa = tranjson.getJSONObject("Data").getJSONArray("RepDataXinWenXinXiZhongXinOutput");
		for(int i=0;i<sa.size();i++)
		{
			String title = sa.getJSONObject(i).getString("title");
			String source = sa.getJSONObject(i).getString("source");
			String context = sa.getJSONObject(i).getString("context");
			String date = sa.getJSONObject(i).getString("date");
			String tmpstr = title+source+context+date;
			if(tst.contains(tmpstr))
				System.out.println(tmpstr);
			tst.add(tmpstr);
		}
		System.out.println(tst.size()+"xxxxxxxxx"+sa.size());
		assertTrue(tst.size()==sa.size());
	}
	@ Test
	public void testNewTypeonee() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=3&sort=ASC&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
		
	}
	@ Test
	public void testNewTypeonef() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news?type=1&sort=ASC&start=0&count=5&token=d3c15a7683174548911cdf512e69d703";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataXinWenXinXiZhongXinOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
