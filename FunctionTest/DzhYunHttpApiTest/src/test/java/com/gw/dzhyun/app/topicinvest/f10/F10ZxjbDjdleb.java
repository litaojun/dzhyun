package com.gw.dzhyun.app.topicinvest.f10;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class F10ZxjbDjdleb {
	@ Test
	public void testF10ZxjbDjdlebObja() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/zxjb/djdleb?obj=SH600745&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10ZxjbdjdlebOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
	@ Test
	public void testF10ZxjbDjdlebObjb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/zxjb/djdleb?obj=SH600745&field=yysjjfj,ssgdsy,date,xsfy&start=-1&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10ZxjbdjdlebOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
	@ Test
	public void testF10ZxjbDjdlebObjc() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/zxjb/djdleb?obj=SH600745&field=ssgdsy,date,xsfy&start=3&count=200&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10ZxjbdjdlebOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
}
