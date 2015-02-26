package com.gw.account.httptest;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import junit.framework.Assert;
import junit.framework.TestCase;



public class AccInterface {
    //1.adduser接口的构造方法
	public static String testAdduser(String casename, String unameinput) throws IOException, SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=adduser&uname="+unameinput+"";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);  
		return response.getText();		
		
}
	//adduser接口的构造方法
	public static String testAdduser(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		System.out.println("Request:"+urlparam.trim());
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduser"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println("Response:" +response.getText());
	    return response.getText();
	}
	
	//adduserex接口的构造方法
		public static String testAdduserex(String params) throws IOException,SAXException{
			StringBuffer sb = new StringBuffer();
			sb.append(params);
			String urlparam = sb.toString();
			WebConversation web = new WebConversation();
			System.out.println("Request:"+urlparam.trim());
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduserex"+urlparam.trim();  
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);
			System.out.println("Response:" +response.getText());
		    return response.getText();
		}
	
	//2.userget接口的构造方法
	public static String testUserget(String casename, String unameinput) throws IOException, SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=userget&uname="+unameinput+"";
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);  
			return response.getText();				
	}
	//userget接口的构造方法
	public static String testUserget(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=userget"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	//3.login接口的构造方法
	public static String testLogin(String casename, String unameinput, String upassinput) throws IOException, SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=login&uname="+unameinput+"&upass="+upassinput+"";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);  
		System.out.println("\n");
		return response.getText();
	}	
	//login接口的构造方法
	public static String testLogin(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=login"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	
	//4.logout接口的构造方法
	public static String testLogout(String casename, String unameinput) throws IOException,SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=logout&uname="+unameinput+"";
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);  
			System.out.println("\n");
			return response.getText();
	}
	
	//logout接口的构造方法
	public static String testLogout(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=logout"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	
	//5.updpass接口的构造方法
	public static String testUpdpass(String casename, String unameinput, String upassinput) throws IOException,SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updpass&uname="+unameinput+"&upass"+upassinput+"";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);  
		System.out.println("\n");
		return response.getText();
    }	
	
	//login接口的构造方法
	public static String testUpdpass(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updpass"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	
	//6.updbase接口的构造方法
	public static String testUpdbase(String casename, String unameinput) throws IOException,SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updbase&uname="+unameinput+"";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);  
		System.out.println("\n");
		return response.getText();
    }
	
	//updbase接口的构造方法
	public static String testUpdbase(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updbase"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	
	//7.updextra接口的构造方法
		public static String testUpextra(String casename, String unameinput, String uMaketinput, String extrabufinput) throws IOException,SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updextra&uname="+unameinput+"&uMaket="+uMaketinput+"&extrabuf="+extrabufinput+"";
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);  
			System.out.println("\n");
			return response.getText();
	    }
		//updextra接口的构造方法
		public static String testUpextra(String params) throws IOException,SAXException{
			StringBuffer sb = new StringBuffer();
			sb.append(params);
			String urlparam = sb.toString();
			WebConversation web = new WebConversation();
			String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updextra"+urlparam.trim();  
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);
			System.out.println(response.getText());
		    return response.getText();
		}	
	
    //8.deluser接口的构造方法
	public static String testDeluser(String casename, String unameinput) throws IOException,SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=deluser&uname="+unameinput+"&upass="+unameinput+"";
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);  
			System.out.println("\n");
			return response.getText();
	}	
	//deluser接口的构造方法
	public static String testDeluser(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=deluser"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}	
}


