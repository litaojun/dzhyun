package com.gw.dzhyun.app.topicinvest.f10;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class F10GdjcSdltgd {
	@ Test
	public void testF10GdjcSdltgdObja() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gdjc/sdltgd?obj=SH600745&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GdjcSdltgdOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10GdjcSdltgdObjb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gdjc/sdltgd?obj=SH600745&field=gbxz,Gbxz,Gsdm&start=-3&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GdjcSdltgdOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10GdjcSdltgdObjc() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/gdjc/sdltgd?obj=SH600745&field=gbxz,Gsdm&start=30&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10GdjcSdltgdOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
