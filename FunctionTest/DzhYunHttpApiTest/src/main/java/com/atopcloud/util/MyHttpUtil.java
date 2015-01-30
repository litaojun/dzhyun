package com.atopcloud.util;

import org.xml.sax.SAXException;

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
	 * 发送一般request http请求，返回WebResponse。
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public static WebResponse sendHttpRequest(String url) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		GetMethodWebRequest get = new GetMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(get);  
		//response.getInputStream();
		return response;
		
	}
	

	/**
	 * 发送post http请求，暂没用上。
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws SAXException
	 */
	public static WebResponse sendHttpPost(String url) throws Exception, SAXException
	{
		// WebConversation是HttpUnit的中心，您使用它来展开与HTTP相关的协议对话
		WebConversation  	web = new WebConversation(); 
		//使用 WebRequest设置相关的请求参数
		PostMethodWebRequest post = new PostMethodWebRequest(url);
			
		//WebConversation连接目的网页，然後得到回应WebResponse
		WebResponse response = web.getResponse(post);  
		return response;
	}
}
