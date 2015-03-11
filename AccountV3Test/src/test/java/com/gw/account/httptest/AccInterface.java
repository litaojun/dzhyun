package com.gw.account.httptest;
import java.io.IOException;

import org.apache.log4j.Logger;
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
	static Logger  log = Logger.getLogger(AccInterface.class);
	
    //1.adduser接口的构造方法
	public static String testAdduser(String casename, String unameinput) throws IOException, SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduser&uname="+unameinput+"";
        System.out.println("testAdduser: "+ urlString);
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
        System.out.println("testAdduser: " +response.getText());
		return response.getText();		
		
}
	//adduser接口的构造方法
	public static String testAdduser(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();

	//	System.out.println("Request:"+urlparam.trim());
		log.info("Request:" + urlparam.trim());
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduser"+urlparam.trim();  
//		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduser"+urlparam.trim();
        System.out.println("testAdduser: "+ urlString);
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
	//	System.out.println("Response:" +response.getText());
		log.info("Response:" +response.getText());
		System.out.println("testAdduser: " +response.getText());
	    return response.getText();
	}
	
	//adduserex接口的构造方法
		public static String testAdduserex(String params) throws IOException,SAXException{
			StringBuffer sb = new StringBuffer();
			sb.append(params);
			String urlparam = sb.toString();
			WebConversation web = new WebConversation();
			log.info("Request:" + urlparam.trim());
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=adduserex"+urlparam.trim();  
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);
			log.info("Response:" + response.getText());
		    return response.getText();
		}
	
	//2.userget接口的构造方法
	public static String testUserget(String casename, String unameinput) throws IOException, SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=userget&uname="+unameinput+"";
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
		log.info("Request:" + urlparam.trim());
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=userget"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		log.info("Response:" + response.getText());
	    return response.getText();
	}
//	//3.login接口的构造方法
//	public static String testLogin(String casename, String unameinput, String upassinput) throws IOException, SAXException{
//	    System.out.println(casename);
//		WebConversation  	web = new WebConversation();
//		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=login&uname="+unameinput+"&upass="+upassinput;
//        System.out.println("testLogin: " + urlString);
//		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
//		WebResponse response = web.getResponse(get);
//        System.out.println("testLogin: " + response.getText());
//		return response.getText();
//	}
	//login接口的构造方法
	public static String testLogin(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=login"+urlparam.trim();
        System.out.println("testLogin: " + urlString);
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println("testLogin: " + response.getText());
	    return response.getText();
	}
	
//	//4.logout接口的构造方法
//	public static String testLogout(String casename, String unameinput) throws IOException,SAXException{
//		    System.out.println(casename);
//			WebConversation  	web = new WebConversation();
//			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=logout&uname="+unameinput+"";
//            System.out.println("testLogout: " + urlString);
//			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
//			WebResponse response = web.getResponse(get);
//            System.out.println("testLogout: " + response.getText());
//			return response.getText();
//	}
	
	//logout接口的构造方法
	public static String testLogout(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=logout"+urlparam.trim();
        System.out.println("testLogout: " + urlString);
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println("testLogout: " + response.getText());
	    return response.getText();
	}

    //userbind接口的构造方法
    public static String testUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=userkeybind"+urlparam.trim();
        System.out.println("testUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        System.out.println("testUserbind: " + response.getText());
        return response.getText();
    }

    //getuserbind接口的构造方法
    public static String testGetUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=getuserbind"+urlparam.trim();
        System.out.println("testGetUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        System.out.println("testGetUserbind: " + response.getText());
        return response.getText();
    }

    //getuserbind接口的构造方法
    public static String testDelUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=deluserbind"+urlparam.trim();
        System.out.println("testDelUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        System.out.println("testDelUserbind: " + response.getText());
        return response.getText();
    }


    public static String testFindUnamebyKey(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=findunamebykey"+urlparam.trim();
        System.out.println("findunamebykey: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        System.out.println("findunamebykey: " + response.getText());
        return response.getText();
    }


	//5.updpass接口的构造方法
	public static String Updpass(String casename, String unameinput, String upassinput) throws IOException,SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updpass&uname="+unameinput+"&upass"+upassinput+"";
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);  
		System.out.println("\n");
		return response.getText();
    }	
	
	//Updpass接口的构造方法
	public static String testUpdpass(String params) throws IOException,SAXException{
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		WebConversation web = new WebConversation();
		log.info("Request:" + urlparam.trim());
	//	System.out.println("Request:"+urlparam.trim());
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updpass"+urlparam.trim();  
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
	//	System.out.println("Response:" +response.getText());
		log.info("Response:" + response.getText());
	    return response.getText();
	}

/*	
	
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
*/	
	//6.updbase接口的构造方法
	public static String testUpdbase(String casename, String unameinput) throws IOException,SAXException{
	    System.out.println(casename);
		WebConversation  	web = new WebConversation(); 
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updbase&uname="+unameinput+"";
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
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updbase"+urlparam.trim();
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}
	
	//7.updextra接口的构造方法
		public static String testUpextra(String casename, String unameinput, String uMaketinput, String extrabufinput) throws IOException,SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updextra&uname="+unameinput+"&uMaket="+uMaketinput+"&extrabuf="+extrabufinput+"";
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
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=updextra"+urlparam.trim();
			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
			WebResponse response = web.getResponse(get);
			System.out.println(response.getText());
		    return response.getText();
		}	
	
    //8.deluser接口的构造方法
	public static String testDeluser(String casename, String unameinput) throws IOException,SAXException{
		    System.out.println(casename);
			WebConversation  	web = new WebConversation(); 
			String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=deluser&uname="+unameinput+"&upass="+unameinput+"";
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
		String urlString = "http://10.15.201.105/AccService/AccServlet.do?method=deluser"+urlparam.trim();
		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
		WebResponse response = web.getResponse(get);
		System.out.println(response.getText());
	    return response.getText();
	}	
	
	
}


