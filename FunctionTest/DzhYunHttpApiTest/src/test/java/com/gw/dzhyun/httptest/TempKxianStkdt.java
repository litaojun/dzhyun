package com.gw.dzhyun.httptest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.MyQuoteKlineUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class TempKxianStkdt {
	@Test
	public void testStkdata() throws SAXException, Exception
	{
		JSONObject jsb = this.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=PinZhongObj,BaoGaoQi,ShangShiRiQi,MeiGuShouYi,MeiGuJingZiChan,JingZiChanShouYiLv,MeiGuJingYingXianJin,MeiGuGongJiJin,MeiGuWeiFenPei,GuDongQuanYiBi,JingLiRunTongBi,ZhuYingShouRuTongBi,XiaoShouMaoLiLv,TiaoZhengMeiGuJingZi,ZongZiChan,LiuDongZiChan,GuDingZiChan,WuXingZiChan,LiuDongFuZhai,ChangQiFuZhai,ZongFuZhai,GuDongQuanYi,ZiBenGongJiJin,JingYingXianJinLiuLiang,TouZiXianJinLiuLiang,ChouZiXianJinLiuLiang,XianJinZengJiaE,ZhuYingShouRu,ZhuYingLiRun,YingYeLiRun,TouZiShouYi,YingYeWaiShouZhi,LiRunZongE,JingLiRun,WeiFenPeiLiRun,ZongGuBen,WuXianShouGuHeJi,LiuTongAGu,LiuTongBGu,JingWaiShangShiGu,QiTaLiuTongGu,XianShouGuHeJi,GuoJiaChiGu,GuoYouFaRenGu,JingNeiFaRenGu,JingNeiZiRanRenGu,QiTaFaQiRenGu,MuJiFaRenGu,JingWaiFaRenGu,JingWaiZiRanRenGu,YouXianGuHuoQiTa,HuanShou,ShiYingLv,ShiJingLv,ZongShiZhi,LiuTongShiZhi");
		System.out.println("大行情DZHYUN-447财务相关的数据返回数据为："+jsb);
		String[] zbstr = {"PinZhongObj","BaoGaoQi", "ShangShiRiQi", "MeiGuShouYi",
				"MeiGuJingZiChan", "JingZiChanShouYiLv","MeiGuJingYingXianJin",
				"MeiGuGongJiJin","MeiGuWeiFenPei","GuDongQuanYiBi","JingLiRunTongBi",
				"ZhuYingShouRuTongBi","XiaoShouMaoLiLv","TiaoZhengMeiGuJingZi",
				"ZongZiChan","LiuDongZiChan","GuDingZiChan","WuXingZiChan",
				"LiuDongFuZhai","ChangQiFuZhai","ZongFuZhai","GuDongQuanYi",
				"ZiBenGongJiJin","JingYingXianJinLiuLiang",
				"TouZiXianJinLiuLiang","ChouZiXianJinLiuLiang",
				"XianJinZengJiaE","ZhuYingShouRu","ZhuYingLiRun",
				"YingYeLiRun","TouZiShouYi","YingYeWaiShouZhi",
				"LiRunZongE","JingLiRun","WeiFenPeiLiRun",
				"ZongGuBen","WuXianShouGuHeJi","LiuTongAGu",
				"LiuTongBGu","JingWaiShangShiGu","QiTaLiuTongGu",
				"XianShouGuHeJi","GuoJiaChiGu","GuoYouFaRenGu",
				"JingNeiFaRenGu","JingNeiZiRanRenGu","QiTaFaQiRenGu",
				"MuJiFaRenGu","JingWaiFaRenGu","JingWaiZiRanRenGu",
				"YouXianGuHuoQiTa",
				"HuanShou","ShiYingLv","ShiJingLv","ZongShiZhi","LiuTongShiZhi"};
		int sign = this.compareStkdataCounlt(jsb, zbstr);
		System.out.println("testStkdata-sign="+sign);
		assertSame("testStkdata",sign,1);
	}
	
	@Test	
	public void testStkdataOrZD() throws SAXException, Exception
	{
		JSONObject jsb = this.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=PinZhongObj,BaoGaoQi,ShangShiRiQi,MeiGuShouYi,MeiGuJingZiChan,JingZiChanShouYiLv,MeiGuJingYingXianJin,MeiGuGongJiJin,MeiGuWeiFenPei,GuDongQuanYiBi,JingLiRunTongBi,ZhuYingShouRuTongBi,XiaoShouMaoLiLv,TiaoZhengMeiGuJingZi,ZongZiChan,LiuDongZiChan,GuDingZiChan,WuXingZiChan,LiuDongFuZhai,ChangQiFuZhai,ZongFuZhai,GuDongQuanYi,ZiBenGongJiJin,JingYingXianJinLiuLiang,TouZiXianJinLiuLiang,ChouZiXianJinLiuLiang,XianJinZengJiaE,ZhuYingShouRu,ZhuYingLiRun,YingYeLiRun,TouZiShouYi,YingYeWaiShouZhi,LiRunZongE,JingLiRun,WeiFenPeiLiRun,ZongGuBen,WuXianShouGuHeJi,LiuTongAGu,LiuTongBGu,JingWaiShangShiGu,QiTaLiuTongGu,XianShouGuHeJi,GuoJiaChiGu,GuoYouFaRenGu,JingNeiFaRenGu,JingNeiZiRanRenGu,QiTaFaQiRenGu,MuJiFaRenGu,JingWaiFaRenGu,JingWaiZiRanRenGu,YouXianGuHuoQiTa,HuanShou,ShiYingLv,ShiJingLv,ZongShiZhi,LiuTongShiZhi,TJZhangDiePing,TJChengJiaoE");
		System.out.println("大行情DZHYUN-447财务相关的数据返回数据为(加上TJZhangDiePing TJChengJiao)："+jsb);
		String[] zbstr = {"PinZhongObj","BaoGaoQi", "ShangShiRiQi", "MeiGuShouYi",
				"MeiGuJingZiChan", "JingZiChanShouYiLv","MeiGuJingYingXianJin",
				"MeiGuGongJiJin","MeiGuWeiFenPei","GuDongQuanYiBi","JingLiRunTongBi",
				"ZhuYingShouRuTongBi","XiaoShouMaoLiLv","TiaoZhengMeiGuJingZi",
				"ZongZiChan","LiuDongZiChan","GuDingZiChan","WuXingZiChan",
				"LiuDongFuZhai","ChangQiFuZhai","ZongFuZhai","GuDongQuanYi",
				"ZiBenGongJiJin","JingYingXianJinLiuLiang",
				"TouZiXianJinLiuLiang","ChouZiXianJinLiuLiang",
				"XianJinZengJiaE","ZhuYingShouRu","ZhuYingLiRun",
				"YingYeLiRun","TouZiShouYi","YingYeWaiShouZhi",
				"LiRunZongE","JingLiRun","WeiFenPeiLiRun",
				"ZongGuBen","WuXianShouGuHeJi","LiuTongAGu",
				"LiuTongBGu","JingWaiShangShiGu","QiTaLiuTongGu",
				"XianShouGuHeJi","GuoJiaChiGu","GuoYouFaRenGu",
				"JingNeiFaRenGu","JingNeiZiRanRenGu","QiTaFaQiRenGu",
				"MuJiFaRenGu","JingWaiFaRenGu","JingWaiZiRanRenGu",
				"YouXianGuHuoQiTa",
				"HuanShou","ShiYingLv","ShiJingLv","ZongShiZhi","LiuTongShiZhi","ShangZhangJiaShu","XiaDieJiaShu","PingPanJiaShu","AGuShangZhangJiaShu","AGuXiaDieJiaShu","AGuPingPanJiaShu","BGuShangZhangJiaShu","BGuXiaDieJiaShu","BGuPingPanJiaShu","QiTaShangZhangJiaShu","QiTaXiaDieJiaShu","QiTaPingPanJiaShu","AGuChengJiaoE","BGuChengJiaoE","JiJinChengJiaoE","QiTaChengJiaoE"};
		int sign = this.compareStkdataCounlt(jsb, zbstr);
		System.out.println("testStkdataOrZD-sign="+sign);
		assertSame("testStkdataOrZD",sign,1);
	}
	
	@Test	
	public void testStkZDdata() throws SAXException, Exception
	{
		JSONObject jsb = this.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJZhangDiePing,TJChengJiaoE");
		System.out.println("大行情DZHYUN-449(指标TJZhangDiePing,TJChengJiaoE)："+jsb);
		String[] zbstr = {"ShangZhangJiaShu","XiaDieJiaShu","PingPanJiaShu","AGuShangZhangJiaShu","AGuXiaDieJiaShu","AGuPingPanJiaShu","BGuShangZhangJiaShu","BGuXiaDieJiaShu","BGuPingPanJiaShu","QiTaShangZhangJiaShu","QiTaXiaDieJiaShu","QiTaPingPanJiaShu","AGuChengJiaoE","BGuChengJiaoE","JiJinChengJiaoE","QiTaChengJiaoE"};
		int sign = this.compareStkdataCounlt(jsb, zbstr);
		System.out.println("testStkZDdata--sign="+sign);
		assertSame("testStkZDdata",sign,1);
		
	}
	@Test	
	public void testStkTJChengJiaoEdata() throws SAXException, Exception
	{
		JSONObject jsb = this.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJChengJiaoE");
		System.out.println("大行情DZHYUN-449(指标TJChengJiaoE)"+jsb);
		String[] zbstr = {"AGuChengJiaoE","BGuChengJiaoE","JiJinChengJiaoE","QiTaChengJiaoE"};
		int sign = this.compareStkdataCounlt(jsb, zbstr);
		System.out.println("testStkTJChengJiaoEdata--sign="+sign);
		assertSame("testStkTJChengJiaoEdata",sign,1);
		
	}
	@Test	
	public void testStkTJZhangDiePingdata() throws SAXException, Exception
	{
		JSONObject jsb = this.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJZhangDiePing");
		System.out.println("大行情DZHYUN-449(指标TJZhangDiePing)"+jsb);
		String[] zbstr = {"ShangZhangJiaShu","XiaDieJiaShu","PingPanJiaShu","AGuShangZhangJiaShu","AGuXiaDieJiaShu","AGuPingPanJiaShu","BGuShangZhangJiaShu","BGuXiaDieJiaShu","BGuPingPanJiaShu","QiTaShangZhangJiaShu","QiTaXiaDieJiaShu","QiTaPingPanJiaShu"};
		int sign = this.compareStkdataCounlt(jsb, zbstr);
		System.out.println("testStkTJZhangDiePingdata--sign="+sign);
		assertSame("testStkTJZhangDiePingdata",sign,1);
	}
	@Test	
	public void testWeekKxian() throws SAXException, Exception
	{
		JSONObject jsb = this.getKxianJsonByurl(""+"http://10.15.144.80/quote/kline?obj=SH600000&period=week&start=-11&count=5", "SH600000");
		System.out.println("周K线数据DZHYUN-446="+jsb);
	}
	@Test	
	public void testMonthKxian() throws SAXException, Exception
	{
		JSONObject jsb = this.getKxianJsonByurl("http://10.15.144.80/quote/kline?obj=SH600000&period=month&start=1&count=10", "SH600000");
		System.out.println("月K线数据DZHYUN-446="+jsb);
	}
	public JSONObject getKxianJsonByurl(String url,String code) throws SAXException, Exception
	{
		String ret =MyHttpUtil.getData(url,"json");
		assertNotNull("错误：行情返回null",ret);
		JSONArray data = MyQuoteKlineUtil.getQuoteKlineByObjCode(ret, code);
		assertNotNull("错误：股票k线为null",data);
		//System.out.println(data);
		JSONObject jsndata = JSON.parseObject(ret);
		TranYfloatMain tym = new TranYfloatMain(jsndata,"RepDataQuoteKlineSingle");
		JSONObject tranjson = tym.dealJsonArray();
		//System.out.println(tranjson+"\n");
		return tranjson;
	}
	
	public JSONObject getStkdataJsonByurl(String url) throws SAXException, Exception
	{
		String ret =MyHttpUtil.getData(url,"json");
		assertNotNull("错误：行情返回null",ret);
		JSONObject jsndata = JSON.parseObject(ret);
		TranYfloatMain tym = new TranYfloatMain(jsndata,"RepDataStkData");
		JSONObject tranjson = tym.dealJsonArray();
		//System.out.println(tranjson+"\n");
		return tranjson;
	}
	public int compareStkdataCounlt(JSONObject jsb,String[] zbstr)
	{
		int ret = 1;
		JSONObject a = jsb.getJSONObject("Data").getJSONArray("RepDataStkData").getJSONObject(0);
		ArrayList<String> retls =new ArrayList<String>();
		for(int i=0;i<zbstr.length;i++)
		{
			//System.out.println("zbstr[i]="+zbstr[i]);
			if(!a.containsKey(zbstr[i]))
			{
				ret =0;
				retls.add(zbstr[i]);
			}
		}
		System.out.println(retls.toString()+"ret="+ret);
		return ret;
	}

	public static void main(String[] args) throws SAXException, Exception 
	{
		// TODO Auto-generated method stub
		TempKxianStkdt tsk = new TempKxianStkdt();
		JSONObject jsb = tsk.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=PinZhongObj,BaoGaoQi,ShangShiRiQi,MeiGuShouYi,MeiGuJingZiChan,JingZiChanShouYiLv,MeiGuJingYingXianJin,MeiGuGongJiJin,MeiGuWeiFenPei,GuDongQuanYiBi,JingLiRunTongBi,ZhuYingShouRuTongBi,XiaoShouMaoLiLv,TiaoZhengMeiGuJingZi,ZongZiChan,LiuDongZiChan,GuDingZiChan,WuXingZiChan,LiuDongFuZhai,ChangQiFuZhai,ZongFuZhai,GuDongQuanYi,ZiBenGongJiJin,JingYingXianJinLiuLiang,TouZiXianJinLiuLiang,ChouZiXianJinLiuLiang,XianJinZengJiaE,ZhuYingShouRu,ZhuYingLiRun,YingYeLiRun,TouZiShouYi,YingYeWaiShouZhi,LiRunZongE,JingLiRun,WeiFenPeiLiRun,ZongGuBen,WuXianShouGuHeJi,LiuTongAGu,LiuTongBGu,JingWaiShangShiGu,QiTaLiuTongGu,XianShouGuHeJi,GuoJiaChiGu,GuoYouFaRenGu,JingNeiFaRenGu,JingNeiZiRanRenGu,QiTaFaQiRenGu,MuJiFaRenGu,JingWaiFaRenGu,JingWaiZiRanRenGu,YouXianGuHuoQiTaHuanShou,ShiYingLv,ShiJingLv,ZongShiZhi,LiuTongShiZhi");
		System.out.println("大行情DZHYUN-446财务相关的数据返回数据为："+jsb);
		jsb = tsk.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=PinZhongObj,BaoGaoQi,ShangShiRiQi,MeiGuShouYi,MeiGuJingZiChan,JingZiChanShouYiLv,MeiGuJingYingXianJin,MeiGuGongJiJin,MeiGuWeiFenPei,GuDongQuanYiBi,JingLiRunTongBi,ZhuYingShouRuTongBi,XiaoShouMaoLiLv,TiaoZhengMeiGuJingZi,ZongZiChan,LiuDongZiChan,GuDingZiChan,WuXingZiChan,LiuDongFuZhai,ChangQiFuZhai,ZongFuZhai,GuDongQuanYi,ZiBenGongJiJin,JingYingXianJinLiuLiang,TouZiXianJinLiuLiang,ChouZiXianJinLiuLiang,XianJinZengJiaE,ZhuYingShouRu,ZhuYingLiRun,YingYeLiRun,TouZiShouYi,YingYeWaiShouZhi,LiRunZongE,JingLiRun,WeiFenPeiLiRun,ZongGuBen,WuXianShouGuHeJi,LiuTongAGu,LiuTongBGu,JingWaiShangShiGu,QiTaLiuTongGu,XianShouGuHeJi,GuoJiaChiGu,GuoYouFaRenGu,JingNeiFaRenGu,JingNeiZiRanRenGu,QiTaFaQiRenGu,MuJiFaRenGu,JingWaiFaRenGu,JingWaiZiRanRenGu,YouXianGuHuoQiTaHuanShou,ShiYingLv,ShiJingLv,ZongShiZhi,LiuTongShiZhi,TJZhangDiePing,TJChengJiao");
		System.out.println("大行情DZHYUN-446(加上TJZhangDiePing TJChengJiao)财务相关的数据返回数据为：(加上TJZhangDiePing TJChengJiao)"+jsb);
		jsb = tsk.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJZhangDiePing");
		System.out.println("大行情DZHYUN-449需求TJZhangDiePing返回数据为："+jsb);
		jsb = tsk.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJChengJiaoE");
		System.out.println("大行情DZHYUN-449需求TJChengJiaoE返回数据为："+jsb);
		jsb = tsk.getStkdataJsonByurl("http://10.15.144.80/stkdata?obj=SH600128&token=00248bb9555f430e8854069274f548ba&field=TJZhangDiePing,TJChengJiaoE");
		System.out.println("大行情DZHYUN-449需求TJZhangDiePing TJChengJiaoE返回数据为："+jsb);
		jsb = tsk.getKxianJsonByurl(""+"http://10.15.144.80/quote/kline?obj=SH600000&period=week&start=-11&count=5", "SH600000");
		System.out.println("周K线数据DZHYUN-446="+jsb);
		jsb = tsk.getKxianJsonByurl("http://10.15.144.80/quote/kline?obj=SH600000&period=month&start=1&count=10", "SH600000");
		System.out.println("月K线数据DZHYUN-446="+jsb);

	}

}
