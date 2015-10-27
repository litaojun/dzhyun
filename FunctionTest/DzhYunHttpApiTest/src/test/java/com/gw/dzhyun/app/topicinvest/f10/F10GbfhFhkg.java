package com.gw.dzhyun.app.topicinvest.f10;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class F10GbfhFhkg {
	@ Test
	public void testF10GbfhFhkgObja() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gbfh/fhkg?obj=SH600745&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GbfhFhkgOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10GbfhFhkgObjb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gbfh/fhkg?obj=SH600745&field=Zhjyr,Pgjg,mgzz&start=-3&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GbfhFhkgOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10GbfhFhkgObjc() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gbfh/fhkg?obj=SH600745&field=Pgjg,mgzz&start=3&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GbfhFhkgOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	public static void main(String[] args) throws SAXException, Exception {
		// TODO Auto-generated method stub
		//String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/sort/range?market=SO&field=ZuiXinJia&desc=true&token=dbf9682f90e64b7a9ac7a75c6203237f";
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") +"/sort/range?obj=SO420058&field=ZuiXinJia&desc=true&token=dbf9682f90e64b7a9ac7a75c6203237f";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataPaiXu");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}

}
