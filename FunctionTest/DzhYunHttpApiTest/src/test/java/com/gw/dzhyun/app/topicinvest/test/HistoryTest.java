package com.gw.dzhyun.app.topicinvest.test;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class HistoryTest {
	public JSONObject findOneObjKxianData(String obj,String startdt,String enddate) throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/quote/kline?obj=%s&period=1day&begin_time=%s&end_time=%s&token=97629f030f8840cf9ea2fadf990f9a08";
		urlstr = String.format(urlstr, new String[]{obj,startdt+"-000000",enddate+"-173502"});
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataQuoteKlineSingle");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
		System.out.println("加一行测试代码");
		return tranjson;
	}

	public static void main(String[] args) throws SAXException, Exception
	{
		// TODO Auto-generated method stub
		TopicInvestTest tit = new TopicInvestTest();
		tit.testTopicInvestBasic();

	}

}
