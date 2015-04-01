package com.atopcloud.util;

import org.xml.sax.SAXException;

import com.dzhyun.proto.Dzhoutput.QuoteDynaOutput;
import com.dzhyun.proto.Dzhoutput.QuoteDynaSingle;
import com.dzhyun.proto.Dzhua.UAResponse;
import com.google.protobuf.ByteString;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * 封装发送http请求的方法。
 * @author Administrator
 *
 */
public class MyHttpUtil {


	/**
	 * 获取动态行情。
	 * @param url  请求URL
	 * @param type 指定服务器返回的数据类型，json或pb
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public static String getQuoteDyna(String url,String type) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		
		//使用 WebRequest设置相关的请求参数
		GetMethodWebRequest get = new GetMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(get);  
		
		String ret=null;
		if(type.equalsIgnoreCase("json"))
		{
			ret = response.getText();
		}
//		else if(type.equalsIgnoreCase("pb"))
//		{
//			UAResponse uaresponse = UAResponse.parseFrom(response.getInputStream());
//			if(uaresponse.hasData())
//			{
//				//获取DATA部分
//				ByteString bs = uaresponse.getData();		
//				
//				//DATA部分也是protobuf格式，仍需要解析
//				QuoteDynaOutput output = QuoteDynaOutput.parseFrom(bs);
//				QuoteDynaSingle single = output.getResults(0);
//				String obj = single.getObj();		
//				
//			}
//		}
		return ret;
	}

	/**
	 * 获取服务端数据。
	 * @param url  请求URL
	 * @param type 指定服务器返回的数据类型，json或pb
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public static String getData(String url,String type) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		
		//使用 WebRequest设置相关的请求参数
		GetMethodWebRequest get = new GetMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(get);  
		
		String ret=null;
		if(type.equalsIgnoreCase("json"))
		{
			ret = response.getText();
		}
		return ret;
	}
	
}
