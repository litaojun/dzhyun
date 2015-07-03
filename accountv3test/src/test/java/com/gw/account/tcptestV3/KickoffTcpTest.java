package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by zhangchaoxu on 2015/6/8.
 */
public class KickoffTcpTest {
	private User user = new User();

	@BeforeClass
	public static void globalInit() {
		MyCheckUtil.initialize();
	}

	@Before
	public void setUp() throws IOException, SAXException, InterruptedException {
		user.createUser();
	}

	// =================================正常测试=======================================
    /**
     * 验证用户第一次登录不被踢出，市场位存在库中
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
	@Test
	public void testFNormal() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		String request1= JSON.toJSONString(ImmutableMap.of(
				
					"netid" ,(int)(Math.random()*10000000),
					"pid" ,(int)(Math.random()*10000),
					"tid" , 9001,
					"lastMsgID" , (int)(Math.random()*10000)
				));
		String request2 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,"1",
				"appid","0.0-1"
				));
		String response=AccInterfaceTcp.testLoginAndServLogin(request1,request2);
		String uMarket = MyCheckUtil.getValueFromJsonResponse(request2,
				"uMarket");
		boolean checkKickoffexists=MyCheckUtil.checkKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		StringBuilder rspuMarket=MyCheckUtil.getKeyexists("s:u:list:0.0-1_"+user.getUsertid());
    	boolean checkuMarket=rspuMarket.toString().contains(uMarket);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		assertTrue("验证新用户登录", result&&checkuMarket&&checkKickoffexists);
	}
    /**
     * 验证用户第二次登录,Umarkt不重复,两个市场位都存在
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
	@Test
	public void testSNormal() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		String request1= JSON.toJSONString(ImmutableMap.of(
				"netid" ,(int)(Math.random()*10000000),
				"pid" ,(int)(Math.random()*10000),
				"tid" , 9001,
				"lastMsgID" , (int)(Math.random()*10000)
				));
		String request2 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,"1",
				"appid","0.0-1"
				));
		String request3 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,"8",
				"appid","0.0-1"
				));
		@SuppressWarnings("unused")
		String response12=AccInterfaceTcp.testLoginAndServLogin(request1,request2);
		String response13=AccInterfaceTcp.testLoginAndServLogin(request1,request3);
		String uMarket2 = MyCheckUtil.getValueFromJsonResponse(request2,
				"uMarket");
		String uMarket3 = MyCheckUtil.getValueFromJsonResponse(request3,
				"uMarket");	
		//检查2个请求市场位存在
		boolean checkKickoffexists=MyCheckUtil.checkKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		StringBuilder rspuMarket=MyCheckUtil.getKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		boolean checkuMarket=rspuMarket.toString().contains(uMarket3)&&rspuMarket.toString().contains(uMarket2);
		
		boolean result = MyCheckUtil.checkJsonResponseSolo(response13, "result",
				"0");
		assertTrue("验证用户第二次登录,Umarkt不重复", result&&checkuMarket&&checkKickoffexists);
	}
	 /**
     * 验证Umarkt重复登录，登录成功，删除以前的市场位，只保存当前市场位及相关信息
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
	@Test
	public void testKickNormal() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		String[] array = new String[]{"12", "14","20"};
		String request1= JSON.toJSONString(ImmutableMap.of(
				"netid" ,(int)(Math.random()*10000000),
				"pid" ,(int)(Math.random()*10000),
				"tid" , 9001,
				"lastMsgID" , (int)(Math.random()*10000)
				));
		String request2 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,"4",
				"appid","0.0-1"
				));
		String request3 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,"8",
				"appid","0.0-1"
				));
		String request4 = JSON.toJSONString(ImmutableMap.of(
				"uname" , user.getUname(),
				"upass" , user.getUpass(),
				"uMarket" ,array[MyCheckUtil.GetRandomNum(0, 4)],
				"appid","0.0-1"
				));
		@SuppressWarnings("unused")
		String response12=AccInterfaceTcp.testLoginAndServLogin(request1,request2);
		@SuppressWarnings("unused")
		String response13=AccInterfaceTcp.testLoginAndServLogin(request1,request3);
		String uMarket2 = MyCheckUtil.getValueFromJsonResponse(request2,
				"uMarket");
		String uMarket3 = MyCheckUtil.getValueFromJsonResponse(request3,
				"uMarket");	
		String uMarket4 = MyCheckUtil.getValueFromJsonResponse(request4,
				"uMarket");	
		//检查市场位存在
		boolean checkKickoffexists=MyCheckUtil.checkKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		StringBuilder rspuMarket=MyCheckUtil.getKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		boolean checkuMarket=rspuMarket.toString().contains(uMarket3)&&rspuMarket.toString().contains(uMarket3);
		//重新发送请求，执行踢人操作
		String response14=AccInterfaceTcp.testLoginAndServLogin(request1,request4);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response14, "result",
				"0");
		//检查踢人后，原市场位不存在，新请求的市场位存在
		boolean checkKickoffexists1=MyCheckUtil.checkKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		StringBuilder rspuMarket1=MyCheckUtil.getKeyexists("s:u:list:0.0-1_"+user.getUsertid());
		boolean checkuMarketExsits=rspuMarket1.toString().contains(uMarket4);
		boolean checkuMarketNexsits=!rspuMarket1.toString().contains(uMarket3)&&!rspuMarket1.toString().contains(uMarket2);
		assertTrue("验证用户第二次登录,Umarkt不重复",result&&checkuMarket&&checkKickoffexists&&checkKickoffexists1&&checkuMarketExsits&&checkuMarketNexsits);
	}
	
}
