/**
 * @classnmae IndicatorCalcTTHBase.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.indicatorcalc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.DateFormatTran;

/**
 * @author Litaojun
 * @date   2015年12月31日
 */
public class IndicatorCalcTTHBase 
{
	private JSONObject indcalc = null;
	private ArrayList<IndicatorInfo> kxian20 = new ArrayList<IndicatorInfo>();
	private String obj;
	private long[] kxian20date = new long[20];
    
	/**
	 * 
	 * @param jsdata 主题投资历史接口获取的JSON数据
	 * @param tm   最大时间戳
	 * @param obj  股票代码
	 * @throws SAXException
	 * @throws Exception
	 */
	public IndicatorCalcTTHBase(JSONObject jsdata,long tm,String obj) throws SAXException, Exception
	{
		this.indcalc = jsdata;
		this.obj = obj;
		initKxian20date(tm);
		//System.out.println(String.format("%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l%l",this.kxian20date));
		initKxian20Day(tm);
	
	}
	
	/**
	 * 
	 * @param @param tm
	 * @param @throws ParseException
	 * @Title initKxian20date
	 * @Description 构造20日K线的时间数组
	 * @return void
	 *
	 */
	public void initKxian20date(long tm) throws ParseException
	{
		this.kxian20date = DateFormatTran.createWeekDayDatelong(tm, 20);
		System.out.println("kxian20date 20 ="+this.kxian20date[19]);
	}
	
	/**
	 * 
	 * @param @param tm
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title initKxian20Day
	 * @Description 为kxian20赋值，通过获取K线接口数据，初始化该数组列表的数据
	 * @return void
	 *
	 */
	public void initKxian20Day(long tm) throws SAXException, Exception
	{
		String  startdate = DateFormatTran.liunxDateTranDateStr("yyyyMMdd", tm-31*86400) + "-000000";
		String enddate = DateFormatTran.liunxDateTranDateStr("yyyyMMdd", tm) + "-153000";
		String curstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/quote/kline?obj=%s&period=1day&begin_time=%s&end_time=%s&token=0e86eccefeeb42f6add81e881dcba673";
		System.out.println(String.format("curstr%s:%s:%s",new String[]{this.obj,startdate,enddate}));
		curstr = String.format(curstr, new String[]{this.obj,startdate,enddate});
		System.out.println("curstr="+curstr);
		String retstr = MyHttpUtil.getQuoteDyna(curstr, "json");
		JSONObject data = JSON.parseObject(retstr);
		System.out.println("data="+data.toJSONString());
		JSONArray jsarr = data.getJSONObject("Data").getJSONObject("JsonTbl").getJSONArray("data").getJSONArray(0).getJSONObject(0).getJSONArray("data").getJSONArray(0).getJSONObject(1).getJSONArray("data");
		HashMap<String,IndicatorInfo> thm = new HashMap<String,IndicatorInfo>();
		for(int i=0;i<jsarr.size();i++)
		{
			JSONArray js = jsarr.getJSONArray(i);
			long tmmap = js.getLongValue(0);
			float shoupaijia = js.getFloatValue(4);
			System.out.println("tmmap="+tmmap+",shoupaijia="+shoupaijia);
			thm.put(String.valueOf(tmmap), new IndicatorInfo(tmmap,shoupaijia));
		}
		for(int j = 0;j<this.kxian20date.length;j++)
		{
			IndicatorInfo tii = thm.get(String.valueOf(this.kxian20date[j]));
			
			this.kxian20.add(tii);
		}
	}
	
	/**
	 * 
	 * @param @return
	 * @Title jsKxian20Pinjun
	 * @Description 计算20日K线的平均值
	 * @return float
	 *
	 */
	public float jsKxian20Pinjun()
	{
		float s = 0;
		int num = 0 ;
		for(IndicatorInfo iif:this.kxian20)
		{
			if(iif!=null)
			{
			s += iif.getShoupaijia();
			num ++;
			}
			else
				System.out.println("this.kxian20数据错误");
		}
		return s/num;
	}
	
	
	public float getKxian20ByIndicatorIntface()
	{
		return this.indcalc.getJSONObject("Data").getJSONArray("RepDataZhiBiaoShuChu").getJSONObject(0).getJSONArray("ShuJu").getJSONObject(0).getJSONArray("JieGuo").getFloat(2);
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
