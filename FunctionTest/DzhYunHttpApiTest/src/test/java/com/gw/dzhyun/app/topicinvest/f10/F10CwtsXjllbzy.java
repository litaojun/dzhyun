package com.gw.dzhyun.app.topicinvest.f10;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class F10CwtsXjllbzy {
	@ Test
	public void testF10GsgkObja() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/xjllbzy?obj=SH600745&token=79abd7c26b6e46e4aa7f1a03e9680bcd";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsXjllbzyOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
	@ Test
	public void testF10GsgkObjb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/xjllbzy?obj=SH600745&field=czxjlr,jyxjlr,jyxjlc,tzxjje&start=-3&count=2&token=79abd7c26b6e46e4aa7f1a03e9680bcd";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsXjllbzyOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
	@ Test
	public void testF10GsgkObjc() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/xjllbzy?obj=SH600745&field=jyxjlc,tzxjje&start=3&count=2&token=79abd7c26b6e46e4aa7f1a03e9680bcd";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsXjllbzyOutput");
//		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(data+"\n");
	}
	public static void main(String[] args) throws SAXException, Exception 
	{
		// TODO Auto-generated method stub
        String[] urlstr = {"http://v2.yundzh.com/news?type=1&sort=DESC&start=0&count=5&obj=SH600128&token=79abd7c26b6e46e4aa7f1a03e9680bcd",
        		"http://v2.yundzh.com/news?type=3&sort=DESC&start=0&count=5&obj=SH600128&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/news?type=1&sort=DESC&start=0&count=5&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/news?type=3&sort=DESC&start=0&count=5&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gsgk?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/cwts/zycwzb?obj=SH600128&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/cwts/xjllbzy?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/zxjb/djdcwzb?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/zxjb/djdleb?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gdjc/gdhs?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gbfh/gbjg?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gdjc/sdgd?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gdjc/sdltgd?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae",
        		"http://v2.yundzh.com/f10/gbfh/fhkg?obj=SH600745&token=c8b4fdf737494cd8934edcb7a51fc6ae"};
        for(int i=0;i<urlstr.length;i++)
        {
        	String retstr = MyHttpUtil. getQuoteDyna(urlstr[i],"json");
        	System.out.println(urlstr[i]+"\n");
        	System.out.println(retstr+"\n");
        }
	}

}
