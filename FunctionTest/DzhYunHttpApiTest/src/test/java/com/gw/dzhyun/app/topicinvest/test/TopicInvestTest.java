package com.gw.dzhyun.app.topicinvest.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.app.topicinvest.base.TopicInvestInfo;

import com.gw.dzhyun.app.topicinvest.calc.TopicInvestHistoryCalc;
import com.gw.dzhyun.app.topicinvest.calc.TopicInvestInterfaceCalc;
import com.gw.dzhyun.app.topicinvest.dao.TopicInvestDao;
import com.gw.dzhyun.util.GetTestJson;
import com.gw.dzhyun.util.TranYfloatMain;

public class TopicInvestTest extends GetTestJson {
	private TopicInvestDao tid = new TopicInvestDao();
	private String token="00000001:1540968527:84208df1f5a1aab6b629e70d4a8b5e882f1b7514";
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testTopicInvestBasic
	 * @Description /topicinvest 接口测试
	 * @return void
	 *
	 */
	@Test
	public void testTopicInvestBasic() throws SAXException, Exception 
	{
		TopicInvestInterfaceCalc tifcalc = new TopicInvestInterfaceCalc();
		TopicInvestInfo tiif = tifcalc.getSXPjiashuByTopicId(52);
		//接口/topicinvest数据
		TopicInvestInfo jkdata = tifcalc.getTopicInvestInfoByTopicInvestIntfce(52);
		//自算数据与接口数据比较
		assertTrue(jkdata.equals(tiif));
	}

	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testTopicInvestHistory
	 * @Description /topicinvest/history接口测试
	 * @return void
	 *
	 */
	@Test
	public void testTopicInvestHistory() throws SAXException, Exception {
		TopicInvestHistoryCalc tphistory = new TopicInvestHistoryCalc();
		tphistory.initKxianJsonData("20150925-000000", "20151225-173502",52);
		tphistory.initObjBsPrice("2015-09-25");
		tphistory.jsObjZhangfu();
		JSONObject rs = tphistory.getZfresult();
		System.out.println("自己计算ID52的主题投资20150728~20151028时间的json数据="+rs);
		ArrayList<Object[]> a = tphistory.tranSelfData(rs);
		//通过接口获取涨幅/topicinvest/history
		tphistory.initInterfaceKXianData();
		ArrayList<Object[]> b = tphistory.getItfData();
		TopicInvestHistoryCalc.printArrayList(a);
		TopicInvestHistoryCalc.printArrayList(b);
		boolean rssign = TopicInvestHistoryCalc.compareArrayList(b, a);
		assertTrue(rssign);
	}


 
	/**
	 * 
	 * @param 
	 * @Title testMysqlAndInfo
	 * @Description 比较mysql数据与/topicinvest/info接口数据是否一致
	 * @return void
	 * @throws Exception 
	 * @throws SAXException 
	 *
	 */
	@Test
	public void testMysqlAndInfo() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/topicinvest/info?topicid=52&token="+token;
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		ArrayList<String> alist = (ArrayList<String>) tid.getObjListByTopicid(52);
		int sign = 1;
		if(data !=null)
		{
			JSONArray jsarr = data.getJSONObject("Data").getJSONArray("RepDataTopicInvestInfo").getJSONObject(0).getJSONArray("ChengFenGu");
			//System.out.println("jsarr="+jsarr);
			for(int i=0;i<jsarr.size();i++)
			{
				if(!alist.contains(jsarr.get(i).toString()))
			    {
				   sign = 0;
				   System.out.println(jsarr.get(i).toString() + "该成分股不存在数据库中" + "\n");
				}					
			}
			if(alist!=null && jsarr.size()!=alist.size())
			{
				 sign = 0;
				 System.out.println("数据库和接口中成分股数量不一致，接口成分股数量＝" + jsarr.size() +",数据库成分股数量 =" +alist.size()+"\n");
			}
			if(alist == null)
			{
				 sign = 0;
				 System.out.println("数据库查询失败" + "\n");
			}
			assertEquals(sign, 1);
		    
		}
		else
		{
			 System.out.println("接口无数据" + "\n");
			 
		}

	}
	
	/**
	 * 
	 * @param 
	 * @Title testTThuiTopicInvest
	 * @Description 测试天天惠需求
	 *              详见http://dms.gw.com.cn/pages/viewpage.action?pageId=135956387中的4
	 *              ShiFouReMenZhuTi 是否热门主题（0：代表否，1：代表是）  是否热门主题是指昨日是否进入所有主题涨幅排名前3位
	 *               RiPingJunZhangFuPaiMing14 主题14日平均涨幅排名
	 *                RiPingJunZhangFuPaiMing30 主题30日平均涨幅排名
	 *                RiReDuZhi14 主题14日热度值  热度值是14进入所有主题涨幅排名前3位的次数
	 *                RiReDuZhi30 主题30日热度值   热度值是30日进入所有主题涨幅排名前3位的次数
	 *              
	 * @return void
	 *
	 */
	public void testTThuiTopicInvest()
	{
		
	}

	

	/**
	 * 
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
