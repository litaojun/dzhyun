package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.TcpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Hihiri on 2015/6/2.
 */
public class AccInterfaceTcp {
	private static final Log LOG = LogFactory.getLog(AccInterfaceTcp.class);
	private static String serverIP = "10.15.201.106";
	private static int serverPort = 32226;

	public static String testAdduserexTcp(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("AdduserexTCP: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.adduserex(urlparam);
		client.close();
		LOG.debug("AdduserexTCP: " + response);
		return response;
	}

	public static String testUpdpassTcp(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("updpassTCP: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.updpass(urlparam);
		client.close();
		LOG.debug("updpassTCP: " + response);
		return response;
	}

	public static String testUserBindTcp(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("UserBindTcp: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.UserBind(urlparam);
		client.close();
		LOG.debug("UserBindTcp: " + response);
		return response;
	}

	public static String testDelUserBindTcp(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("DelUserBindTcp: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.DelUserBind(urlparam);
		client.close();
		LOG.debug("DelUserBindTcp: " + response);
		return response;
	}

	public static String testUserGetTcp(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("UserGetTcp: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.userget(urlparam);
		client.close();
		LOG.debug("UserGetTcp: " + response);
		return response;
	}

	public static String testServLogin(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("ServLoginTcp: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.ServLogin(urlparam);
		client.close();
		LOG.debug("ServLoginTcp: " + response);
		return response;
	}

	public static String testLogin(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("LoginTcp: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response = client.userlogin(urlparam);
		client.close();
		LOG.debug("LoginTcp: " + response);
		return response;
	}

	public static void testLogOut(String params) throws IOException,
			SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(params);
		String urlparam = sb.toString();
		LOG.debug("LogOut: " + urlparam);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		client.logout(urlparam);
		//client.close();
	}

	public static String testLoginAndServLogin(String params1, String params2)
			throws IOException, SAXException, InterruptedException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append(params1);
		String urlparam1 = sb1.toString();
		LOG.debug("ServLoginTcp: " + urlparam1);
		StringBuffer sb2 = new StringBuffer();
		sb2.append(params2);
		String urlparam2 = sb2.toString();
		LOG.debug("LoginTcp: " + urlparam2);
		TcpClient client = new TcpClient();
		client.open(serverIP, serverPort);
		String response1 = client.ServLogin(urlparam1);
		Thread.sleep(1000);
		String response2 = client.userlogin(urlparam2);
		client.close();
		LOG.debug("ServLoginTcp: " + response1);
		LOG.debug("LoginTcp: " + response2);
		return response2;
	}

}
