package com.gw.dzhyun.httptest;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gw.dzhyun.util.GetTestJson;
import com.gw.dzhyun.util.TestCaseManagr;
import com.gw.dzhyun.util.TestCaseStyle;
import com.gw.dzhyun.util.TranYfloatMain;

@RunWith(Parameterized.class)
public class IndicatorCalcTest extends GetTestJson
{
	public IndicatorCalcTest(TestCaseStyle tcs,String[] classPara)
	{
		this.tcs = tcs; 
		this.classPara = classPara;
	}
	
	@ Test
	public void testIndicatorCalc() throws SAXException, Exception
	{
		JSONObject sentHttpReq = this.sentHttpReq();
		assert(sentHttpReq!=null);
		System.out.println(sentHttpReq.toString()+"\n");
	}
   
//	@ ig
//	public void testIndicatorCalctss() throws SAXException, Exception
//	{
//		JSONObject sentHttpReq = this.sentHttpReq();
//		assert(sentHttpReq!=null);
//		System.out.println(sentHttpReq.toString()+"\n");
////		TranYfloatMain tym = new TranYfloatMain(sentHttpReq,"RepDataZhiBiaoShuChu");
////		JSONObject tranjson = tym.dealJsonArray();
////		System.out.println(tranjson+"\n");
//	}
	
	@Parameters 
	public static Collection dateFeed() throws IOException
	{
		 TestCaseManagr a = new TestCaseManagr("E:\\环境文档\\测试用例.xlsx","指标计算接口",126);
		 //TestCaseManagr a = new TestCaseManagr("E:\\环境文档\\测试用例.xlsx","指标查询接口",19);
		 List allcoll =new LinkedList();
		 allcoll.addAll(a.getCurList(new String[] {"obj","name","period","begin_time","end_time","start","count","parameter"}));
		 //allcoll.addAll(b.getCurList(new String[] {"name","type","output"}));
	     return allcoll;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	    String  charEncode = java.net.URLEncoder.encode("&");  
	    System.out.println("字符& 转译后的值为：" + charEncode );  //输出：<span style="color:#CC0000">%26</span>  
       String urlencode = java.net.URLEncoder.encode("http://10.15.144.80:80/indicator/query?name=W&R&type=ind&output=json");
       System.out.println("urlencode="+urlencode);
	}

}
