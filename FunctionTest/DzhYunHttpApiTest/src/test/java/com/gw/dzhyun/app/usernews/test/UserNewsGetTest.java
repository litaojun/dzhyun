/**
 * @classnmae UserNewsGetTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.usernews.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.app.usernews.base.UserNewsBody;
import com.gw.dzhyun.app.usernews.util.UserNewsJsonAnalysisTool;

import static org.junit.Assert.*;
/**
 * @author Litaojun
 * @date   2016年2月24日
 */
public class UserNewsGetTest {
	String token = "00000001:1542591436:8539965c8c649ab91b90f07ecf5b2a5f66fcf245";
	
	
	/**
	 * 
	 * @param 
	 * @Title testNewsIdOnlyOne
	 * @Description 验证新闻无重复数据，逻辑上判断ID无重复
	 * @return void
	 * @throws Exception 
	 * @throws SAXException 
	 *
	 */
	@Test
	public void testNewsIdOnlyOne() throws SAXException, Exception
	{
		String urlstr = "http://"
				//+ MyConfigUtil.getConfig("ip")
				+"10.15.144.81"
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/user/news/get?userid=test00002&page=1&channelid=test00002&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<UserNewsBody> usernewList= UserNewsJsonAnalysisTool.tranJsonToUserNewsBodyArr(data);
		UserNewsJsonAnalysisTool.sortUserNewsBodyListById(usernewList);
		System.out.println(usernewList.size());
		for(int i=1;i<usernewList.size();i++)
		{
			UserNewsBody curnew = usernewList.get(i);
			UserNewsBody prenew = usernewList.get(i-1);
			int a = Integer.parseInt(curnew.getId());
        	int b = Integer.parseInt(prenew.getId());
			if(a == b)
			{
				System.out.println("重复的ID为："+a);
				assertTrue(false);
			}
		}

	}
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testNewsSortByTime
	 * @Description 验证列表按照时间和istop排序
	 * @return void
	 *
	 */
	@Test
	public void testNewsSortByTime() throws SAXException, Exception
	{
		String urlstr = "http://"
				//+ MyConfigUtil.getConfig("ip")
				+"10.15.144.81"
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/user/news/get?userid=test00002&page=2&channelid=test00002&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<UserNewsBody> usernewList= UserNewsJsonAnalysisTool.tranJsonToUserNewsBodyArr(data);
		for(int i = 0;i<usernewList.size()-1;i++)
		{
			UserNewsBody curnew = usernewList.get(i);
			UserNewsBody prenew = usernewList.get(i+1);
			int sign = curnew.compareTo(prenew);
			if(sign<0)
			{
				System.out.println("i="+i+",ID="+curnew.getId());
				assertTrue(false);
			}
		}
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testNewsTopData
	 * @Description 验证置顶数据
	 * @return void
	 *
	 */
	@Test
	public void testNewsTopData() throws SAXException, Exception
	{
		String[] toplist = new String[]{"1745","500010","200005"};
		String urlstr = "http://"
				//+ MyConfigUtil.getConfig("ip")
				+"10.15.144.81"
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/user/news/get?userid=test00002&page=1&channelid=test00002&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<UserNewsBody> usernewList= UserNewsJsonAnalysisTool.tranJsonToUserNewsBodyArr(data);
		for(int i=0;i<=toplist.length;i++)
		{
			if(i<toplist.length)
			{
				if(!(usernewList.get(i).getId().equals(toplist[i]) && usernewList.get(i).getIsTop().equals("1")))
				{
					System.out.println("i="+i+",ID="+usernewList.get(i).getId());
					assertTrue(false);
				}
			}
			else
			{
				if(!usernewList.get(i).getIsTop().equals("0"))
				{
					System.out.println("i="+i+",ID="+usernewList.get(i).getId());
					assertTrue(false);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testBigImgNews
	 * @Description 验证大图的ID和数据正确性
	 * @return void
	 *
	 */
	@Test
	public void testBigImgNews() throws SAXException, Exception
	{
		String[] bigimge = new String[]{"1456469015596","1456468945910","600002","600001"};
		String urlstr = "http://"
				//+ MyConfigUtil.getConfig("ip")
				+"10.15.144.81"
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/user/news/get?userid=test00002&page=1&channelid=test00002&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<UserNewsBody> usernewList= UserNewsJsonAnalysisTool.tranJsonToBigImageNewsBodyArr(data);
		assertTrue(usernewList!=null&&usernewList.size()>0);
		for(int i=0;i<bigimge.length;i++)
		{
				if(!(usernewList.get(i).getId().equals(bigimge[i])))
				{
					System.out.println("i="+i+",ID="+usernewList.get(i).getId());
					assertTrue(false);
				}
			
		}
		assertTrue(bigimge.length == usernewList.size());
	}

	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testWeiShiPinData
	 * @Description 微视频数据字段比对
	 * @return void
	 *
	 */
	@Test
	public void testWeiShiPinData() throws SAXException, Exception
	{
		String jsstr = "{\"id\": \"200003\",\"summary\": \"微视频摘要3-修改\",\"title\": \"微视频标题3-修改\",\"otime\": \"2015-10-13 18:55:49\",\"source\": \"\",\"img\": \"http://luyanic.learning.sohu.com/group-714437.shtml#3-修改\",\"type\": \"2\",\"url\": \"http://luyan.sohu.com/20160221/n4380269503.shtml-xiugai\",\"countid\":20003,\"views\":\"3\",\"resType\":\"3\",\"uComments\":\"3\",\"eComments\":\"3\",\"stockName\":[{\"stockcode\":\"SH601513\",\"stockname\":\"大智慧\"},{\"stockcode\":\"SH200003\",\"stockname\":\"浦发银行-修改\"},{\"stockcode\":\"SH600009\",\"stockname\":\"测试新增\"}],\"topName\":\"右上角名称3-修改\",\"topUrl\":\"www.luyan.com3.xiugai\",\"topType\":\"2\",\"topColor\":\"#1223-xiugai\",\"opreation\":\"update\"}";
		JSONObject srcdata = JSON.parseObject(jsstr);
		UserNewsBody srcjson = new UserNewsBody();
		srcjson.paserFromJsonObj(srcdata);
		String urlstr = "http://"
				//+ MyConfigUtil.getConfig("ip")
				+"10.15.144.81"
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/user/news/get?page=1&channelid=weishipin&token="+token;
		//System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<UserNewsBody> usernewList= UserNewsJsonAnalysisTool.tranJsonToUserNewsBodyArr(data);
		int sign = 0;
		for(UserNewsBody tmp:usernewList)
		{
			//System.out.println(tmp.getId()+"------------"+srcjson.getId());
			if(tmp.getId().equals(srcjson.getId()))
			{
				sign = 1;
				assertTrue(tmp.equals(srcjson));
				break;
			}
		}
		assertTrue(sign==1);
	}
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
