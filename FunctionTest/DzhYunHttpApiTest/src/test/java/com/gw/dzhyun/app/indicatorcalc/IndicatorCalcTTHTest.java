/**
 * @classnmae IndicatorCalcTTHTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.indicatorcalc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;

/**
 * @author Litaojun
 * @date   2015年12月31日
 */
public class IndicatorCalcTTHTest {
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testIndicatorTTH20dayKxianSH
	 * @Description 天天惠测试用例类，计算个股20日K线数据，上证市场
	 * @return void
	 *
	 */
	@Test
	public void testIndicatorTTH20dayKxianSH() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/indicator/calc?obj=SH600128&name=MA&period=1day&start=-1";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstrlitaojun=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		IndicatorCalcTTHBase ictth = new IndicatorCalcTTHBase(data,1457625600,"SH600128");
		float jiekou = (float) (Math.round(ictth.getKxian20ByIndicatorIntface() * 100) * 0.01d);
		float jsdata = (float) (Math.round(ictth.jsKxian20Pinjun() * 100) * 0.01d);
		System.out.println(String.format("jiekou=%s,jsdata=%s",new String[]{String.valueOf(jiekou),String.valueOf(jsdata)}));
		assertTrue(jiekou==jsdata);
		
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testIndicatorTTH20dayKxianSZ
	 * @Description 深证市场
	 * @return void
	 *
	 */
	@Test
	public void testIndicatorTTH20dayKxianSZ() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/indicator/calc?obj=SZ000150&name=MA&period=1day&start=-1";
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		//System.out.println("retstrlitaojun=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		IndicatorCalcTTHBase ictth = new IndicatorCalcTTHBase(data,1457625600,"SZ000150");
		float jiekou = (float) (Math.round(ictth.getKxian20ByIndicatorIntface() * 100) * 0.01d);
		float jsdata = (float) (Math.round(ictth.jsKxian20Pinjun() * 100) * 0.01d);
		System.out.println(String.format("jiekou=%s,jsdata=%s",new String[]{String.valueOf(jiekou),String.valueOf(jsdata)}));
		assertTrue(jiekou==jsdata);
		
		
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
