package com.gw.dzhyun.app.topicinvest;

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
import com.gw.dzhyun.util.GetTestJson;
import com.gw.dzhyun.util.TranYfloatMain;

public class TopicInvestTest extends GetTestJson {

	@Test
	public void testTopicInvestBasic() throws SAXException, Exception {
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/topicinvest?topicid=5100&token=8de307401c8f448990a5ac8d30e28059";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		// TranYfloatMain tym = new TranYfloatMain(data,"RepDataTopicInvest");
		// JSONObject tranjson = tym.dealJsonArray();
		// System.out.println(tranjson+"\n");
	}

	@Test
	public void testTopicInvestHistory() throws SAXException, Exception {
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/topicinvest/history?topicid=52&token=8de307401c8f448990a5ac8d30e28059";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		// TranYfloatMain tym = new
		// TranYfloatMain(data,"RepDataTopicInvestHistory");
		// JSONObject tranjson = tym.dealJsonArray();
		// System.out.println(tranjson+"\n");
	}

	@Test
	public void testKXianData() throws SAXException, Exception {
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/quote/kline?obj=SZ300298&period=1day&begin_time=20150703-000000&end_time=20150703-173502&token=8de307401c8f448990a5ac8d30e28059";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
//		JSONObject data = JSON.parseObject(retstr);
//		 TranYfloatMain tym = new TranYfloatMain(data,"RepDataQuoteKlineSingle");
//		 JSONObject tranjson = tym.dealJsonArray();
//		 System.out.println(tranjson+"\n");
	}

	public JSONObject getQuoteDynaData(String urlstr) {
		JSONObject tranjson = null;
		try {
			String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
			 tranjson = JSON.parseObject(retstr);
//			TranYfloatMain tym = new TranYfloatMain(data,
//					"RepDataQuoteDynaSingle");
//			tranjson = tym.dealJsonArray();
			// System.out.println(tranjson+"\n");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tranjson;
	}

	public TopicInvestInfo jsnObjToTopinInfo(JSONObject jsobj) {
		JSONArray JSONArray = (JSONArray) jsobj.getJSONObject("Data").get(
				"RepDataQuoteDynaSingle");
		JSONObject tstjs = JSONArray.getJSONObject(0).getJSONObject("Data");
		System.out.println("tstjs=" + tstjs.toJSONString());
		TopicInvestInfo topic = new TopicInvestInfo();
		topic.setHuanShou(tstjs.getFloatValue("HuanShou"));
		topic.setKaiPanJia(tstjs.getFloatValue("KaiPanJia"));
		topic.setLiangBi(tstjs.getFloatValue("LiangBi"));
		topic.setObj(tstjs.getString("Obj"));
		topic.setShiJian(tstjs.getLongValue("ShiJian"));
		topic.setZuiXinJia(tstjs.getFloatValue("ZuiXinJia"));
		topic.setZhangFu(tstjs.getFloatValue("ZhangFu"));
		// System.out.println(" x.getZhangFu()="+
		// topic.getZhangFu()+"xx=="+tstjs.getFloatValue("ZhangFu"));
		return topic;
	}

	public TopicInvestInfo getAvgData(ArrayList<TopicInvestInfo> topiclist) {
		TopicInvestInfo tll = new TopicInvestInfo();
		float zhangfu = 0;
		float liangbi = 0;
		float huanshou = 0;
		int sznum = 0, xdnum = 0, ppnum = 0;
		for (TopicInvestInfo x : topiclist) {
			zhangfu += x.getZhangFu();
			liangbi += x.getLiangBi();
			huanshou += x.getHuanShou();
			// System.out.println("ZhangFu"+ x.getZhangFu());
			// System.out.println("  x.getLiangBi()="+ x.getLiangBi());
			System.out.println(" x.getHuanShou=" + x.getHuanShou());
			if (x.getZhangFu() > 0)
				sznum++;
			else if (x.getZhangFu() == 0)
				ppnum++;
			else
				xdnum++;
		}
		System.out.println("huanshou总换手=" + huanshou + "--topiclist.size()="
				+ topiclist.size());
		tll.setLiangBi(liangbi / topiclist.size());
		tll.setZhangFu(zhangfu / topiclist.size());
		tll.setHuanShou(huanshou / topiclist.size());
		tll.ppnum = ppnum;
		tll.sznum = sznum;
		tll.xdnum = xdnum;
		return tll;
	}

	public static ArrayList<TopicInvestInfo> filterList(
			ArrayList<TopicInvestInfo> tllist) {
		// System.out.println("tllist.size="+tllist.size());
		ArrayList<TopicInvestInfo> tll = new ArrayList<TopicInvestInfo>();

		for (TopicInvestInfo tm : tllist) {
			if (tm.getZuiXinJia() > 0)
				tll.add(tm);
		}
		// System.out.println("tllist.size="+tllist.size());
		return tll;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("xx1");
		TopicInvestTest topicin = new TopicInvestTest();
		TopicInvestDao tid = new TopicInvestDao();
		ArrayList<String> alist = (ArrayList<String>) tid.getListByUId();
		ArrayList<TopicInvestInfo> topiclist = new ArrayList<TopicInvestInfo>();
		for (String obj : alist) {

			String urlstr = "http://10.15.144.80/quote/dyna?obj=" + obj
					+ "&token=4eb56b67f37f4fd39ede904d361aa625";
			System.out.println("obj=" + obj + ",urlstr=" + urlstr);
			JSONObject jsnobj = topicin.getQuoteDynaData(urlstr);
			// System.out.println(jsnobj.toJSONString());
			topiclist.add(topicin.jsnObjToTopinInfo(jsnobj));
		}
		// System.out.println("xx2");
		topiclist = TopicInvestTest.filterList(topiclist);
		Collections.sort(topiclist, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				if (null != o1 && null != o2) {
					TopicInvestInfo menu1 = (TopicInvestInfo) o1;
					TopicInvestInfo menu2 = (TopicInvestInfo) o2;
					if (menu1.getZhangFu() < menu2.getZhangFu()) {
						return 1;
					} else {
						return 0;
					}
				}
				return 0;
			}
		});
		// System.out.println("xx3");
		TopicInvestInfo rst = topicin.getAvgData(topiclist);
		rst.printAvgData();

	}

}
