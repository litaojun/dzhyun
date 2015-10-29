package com.gw.dzhyun.app.topicinvest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class TopicInvestHistory {
	private JSONObject objKxianData =  new JSONObject();
	private JSONObject objBsPrice = new JSONObject();
	private JSONObject zfresult =  new JSONObject();
	private ArrayList<Object[]> itfData = new  ArrayList<Object[]>();
	private ArrayList<Object[]> selfData = new  ArrayList<Object[]>();
	public static boolean compareArrayList(ArrayList<Object[]> interfData,ArrayList<Object[]> selfData)
	{
		boolean sign = true;
		HashMap<String,Object> interfDataHash = new HashMap<String,Object>();
		HashMap<String,Object> selfDataHash = new HashMap<String,Object>();
		for(Object[] tmo : interfData)
		{
			interfDataHash.put((String) tmo[0], tmo[1]);
		}
		for(Object[] tmo : selfData)
		{
			selfDataHash.put((String) tmo[0], tmo[1]);
		}
		Set<String> interSet = interfDataHash.keySet();
		Iterator<String> itor = interSet.iterator();
		while(itor.hasNext())
		{
			String itkey = itor.next();
			if(selfDataHash.containsKey(itkey))
			{
				float a = Float.valueOf(String.valueOf(selfDataHash.get(itkey)));
				float b = Float.valueOf(String.valueOf(interfDataHash.get(itkey)));
				float comr = a -b/100;
				if(comr>0.01 || comr<-0.01)
				{
					 sign = false;
					System.out.println(String.format("日期%s涨幅数据不一致，其中通过接口获取的涨幅为%f,自己计算的涨幅为%f",new Object[]{itkey,b,a}));
				}
			}
			else
			{
				sign = false;
				System.out.println(String.format("接口中存在日期%s,但我自己计算的数据中不存在该日期 ",new String[]{itkey}));
			}
		}
		
		interSet = selfDataHash.keySet();
		itor = interSet.iterator();
		while(itor.hasNext())
		{
			String skey = itor.next();
			if(!interfDataHash.containsKey(skey))
			{
			    sign = false;
				System.out.println(String.format("我计算的涨幅数据存在日期%s,但接口中的数据中不存在该日期 ",new String[]{skey}));
			}
		}
		
		return sign;
	}
	public static void printArrayList(ArrayList<Object[]> selfData)
	{
		for(int i=0;i<selfData.size();i++)
		{
			System.out.println(selfData.get(i)[0]+"   ##########   "+selfData.get(i)[1]);
		}
	}
	public  ArrayList<Object[]> getItfData()
	{
		return this.itfData;
	}
	public ArrayList<Object[]> tranInterfData(JSONObject tranjson)
	{
		JSONArray a = tranjson.getJSONObject("Data").getJSONArray("RepDataTopicInvestHistory").getJSONObject(0).getJSONObject("LiShi").getJSONArray("HangQing");
		ArrayList<Object[]> tmp = new ArrayList<Object[]>();
		for(int i=0;i<a.size();i++)
		{
			tmp.add(new Object[]{a.getJSONObject(i).getString("ShiJian").substring(0, 10),a.getJSONObject(i).getFloat("ZhangFu")});
		}
		Collections.sort(tmp,new Comparator(){
			public int compare(Object o1, Object o2) {  
		        if(null!=o1&&null!=o2)  
		        {  
		        	Object[] menu1=(Object[])o1;  
		        	Object[] menu2=(Object[])o2;  
		            if(((String)menu1[0]).compareTo(((String)menu2[0]))>0)
		            {  
		            	//System.out.println((String)menu1[0]+"-------"+menu2[0]+"1");
		                return 1;  
		            }else {  
		            	//System.out.println((String)menu1[0]+"-------"+menu2[0]+"0");
		                return -1;  
		            }  
		        }  
		        return 0;  
		    }  
		});
		return tmp;
	}
	
	/*
	 * 通过/topicinvest/history获取数据保存到itfData
	 */
	public void initInterfaceKXianData() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/topicinvest/history?topicid=52&token=ae8003a973aa43b5a00fd617275badde";
		//System.out.println("urlstr="+urlstr+"\n");
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		JSONObject data = JSON.parseObject(retstr);
//		TranYfloatMain tym = new TranYfloatMain(data,"RepDataTopicInvestHistory");
//		JSONObject tranjson = tym.dealJsonArray();
		//System.out.println("initInterfaceKXianData="+tranjson+"\n");
		this.itfData = this.tranInterfData(data);
	}
	public JSONObject getZfresult()
	{
		return this.zfresult;
	}
	
	/*
	 * 通过k线的URL请求，获取k线数据
	 */
	public JSONObject getKxianDataByUrl(String urlstr) throws SAXException, Exception
	{
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("urlstr="+urlstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataQuoteKlineSingle");
		return tym.dealJsonArray();
	}
	/*
	 * 将k线 http请求返回的json数据提取部分数据组成新的json数据，只提取其中的时间和收盘价，组成{时间：开盘价}格式的json返回
	 */
	public JSONObject tranJsonData(JSONObject srcjson)
	{
		if(srcjson==null)
			return null;
		JSONObject destjson = new JSONObject();
		JSONArray curarr = srcjson.getJSONObject("Data").getJSONArray("RepDataQuoteKlineSingle").getJSONObject(0).getJSONArray("Data");
		if(curarr==null)
			return null;
		for(int i = 0;i<curarr.size();i++)
		{
			JSONObject ts = curarr.getJSONObject(i);
			String datestr = ts.getString("ShiJian");
			float spj = ts.getFloat("ShouPanJia");
			destjson.put(datestr.substring(0, 10), spj);
		}
		return destjson;
	}
	/*
	 * 初始化3个月的k线数据，保存到objKxianData对象中
	 */
	public void initKxianJsonData(String startdate,String enddate) throws SAXException, Exception
	{
		TopicInvestDao tistdao = new TopicInvestDao();
		ArrayList<String> objlist = (ArrayList<String>) tistdao.getListByUId();
		for(String objstr:objlist)
		{
			String curstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/quote/kline?obj=%s&period=1day&begin_time=%s&end_time=%s&token=ae8003a973aa43b5a00fd617275badde";
			curstr = String.format(curstr, new String[]{objstr,startdate,enddate});
			JSONObject tranjson = this.getKxianDataByUrl(curstr);
			//System.out.println(objstr + "股票代码从"+startdate+"到"+enddate+"http请求URL==="+curstr+"\n");
			//System.out.println(objstr + "股票代码从"+startdate+"到"+enddate+"K线数据为：\n"+tranjson+"\n");
			if(tranjson !=null)
			{
			     JSONObject zhjson = this.tranJsonData(tranjson);
			     objKxianData.put(objstr, zhjson);
			     //System.out.println(zhjson+"\n");
			}
			else
				System.out.println(objstr + "股票代码从"+startdate+"到"+enddate+"无k线数据\n");
		}
		
	}
	
	/*
	 * 获取各个股票的基准价格，保存到objBsPrice对象
	 */
	public void initObjBsPrice(String startdate) throws SAXException, Exception
	{
		Set<String> kxset = this.objKxianData.keySet();
		Iterator<String> allkey = kxset.iterator();
		System.out.println("开始获取基准价格.......，基准价格时间取"+startdate);
		while(allkey.hasNext())
		{
			String tmpobj = allkey.next();
			JSONObject curjson = this.objKxianData.getJSONObject(tmpobj);
			//System.out.println("curjson="+curjson);
			float a=0;
			if(curjson.containsKey(startdate))
			{
				a = curjson.getFloatValue(startdate);
				System.out.println(startdate+"日期股票代码"+tmpobj+"基准价格="+a);
			    if(a==0)
			    {
			    	System.out.println(startdate+"日期股票代码"+tmpobj+"停牌，基准价格往前取");
					a = this.findBasePrice(startdate,tmpobj);
			    }
				this.objBsPrice.put(tmpobj, a);
			}
			else
			{
				a = this.findBasePrice(startdate,tmpobj);
				if(a==0)
				  System.out.println("股票代码"+tmpobj+"属于3个月内发行的新股票，无基准价格，不参与计算");
				else
				{
				   this.objBsPrice.put(tmpobj, a);
				}
			}
			
		}
		System.out.println("获取基准价格结束");
		
	}
	public float findBasePrice(String startdate,String obj) throws SAXException, Exception
	{
		//System.out.println("startdate=="+startdate);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		Date stdt = df.parse(startdate);
		df = new SimpleDateFormat("yyyyMMdd"); 
		String startstrdate = df.format(stdt);
		Calendar cal=Calendar.getInstance(); 
		cal.clear();
		cal.setTime(stdt);
		cal.add(Calendar.DATE, -90);
		Date enddate = cal.getTime(); 
		String endstrdate = df.format(enddate);
		String curstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/quote/kline?obj=%s&period=1day&begin_time=%s&end_time=%s&token=ae8003a973aa43b5a00fd617275badde";
		curstr = String.format(curstr, new String[]{obj,endstrdate+"-000000",startstrdate+"-173502"});
		//System.out.println("ltjurl="+curstr);
		JSONObject alldata = this.getKxianDataByUrl(curstr);
		System.out.println("curstr="+curstr);
		System.out.println("alldata="+alldata);
		JSONObject zhformatedate = this.tranJsonData(alldata);
		if(zhformatedate == null)
			return (float) 0;
		Set<String> kxset = zhformatedate.keySet();
		Iterator<String> allkey = kxset.iterator();
		String upkey="0";
		while(allkey.hasNext())
		{
			String tmpkey = allkey.next();
			float a = zhformatedate.getFloatValue(tmpkey);
			if(a>0 && tmpkey.compareTo(upkey)>0)
				upkey=tmpkey;
				
		}
		if(!upkey.equals("0"))
		    return zhformatedate.getFloatValue(upkey);
		return (float) 0;
	}
	
	/*
	 * 计算板块的涨幅，按时间取出各个股票并计算涨幅，最后取平均值
	 */
	public void jsObjZhangfu()
	{
		System.out.println("基准价格json数据"+this.objBsPrice);
		System.out.println("所有股票转换格式前的JSON数据"+this.objKxianData);
		JSONObject datejs = this.testJson(this.objKxianData);
		System.out.println("所有股票转换格式后的JSON数据"+datejs);
		Set<String> kxset = datejs.keySet();
		Iterator<String> allkey = kxset.iterator();
		while(allkey.hasNext())
		{
			String datestr = allkey.next();
			JSONObject jsonf = datejs.getJSONObject(datestr);
			Set<String> objset = jsonf.keySet();
			Iterator<String> key = objset.iterator();
			float numzf = 0;
			int num = 0;
			while(key.hasNext())
			{
				String obj = key.next();
				if(this.objBsPrice.containsKey(obj))
				{
					float baseprice = this.objBsPrice.getFloat(obj);
					float spprice = jsonf.getFloat(obj) ;
					if(spprice>0)
					{
						num++;
						float tm = (spprice-baseprice)/baseprice;
						System.out.println("日期"+datestr+"股票代码"+obj+"收盘价"+spprice+"基准价"+baseprice+"涨幅 "+tm);
						numzf += tm;
					}
					else
					{
						System.out.println("日期"+datestr+"股票代码"+obj+"未取到收盘价，停牌了，不参与计算");
					}
				}
				else
				{
					System.out.println("日期"+datestr+"股票代码"+obj+"未取到基准价格，属于3月内发行 的新股票，不参与计算");
				}
			}
			this.zfresult.put(datestr, numzf/num);
			
		}
	}
	
	
	/*
	 * //转换json格式，将{"obj":{"date":shoupaiprice}}转换为 {"date":{"obj":shoupaiprice}}
	 */
	public JSONObject testJson(JSONObject jsb)
	{
		JSONObject retjsob = new JSONObject();
		Set<String> kxset = jsb.keySet();
		Iterator<String> allkey = kxset.iterator();
		while(allkey.hasNext())
		{
			String keyobj = allkey.next();
			JSONObject datejsb = jsb.getJSONObject(keyobj);
			Set<String> kxsetxx = datejsb.keySet();
			Iterator<String> allkeyxx = kxsetxx.iterator();
			String dk=null,oj=null;
			while(allkeyxx.hasNext())
			{
				String datekey = allkeyxx.next();
				float tm = datejsb.getFloatValue(datekey);

				if(retjsob.containsKey(datekey))
				{
					JSONObject tmpjs = retjsob.getJSONObject(datekey);
					tmpjs.put(keyobj, tm);
					retjsob.put(datekey, tmpjs);
				}
				else
				{
				   JSONObject tmpjson = new JSONObject();
				   tmpjson.put(keyobj, tm);
				   retjsob.put(datekey, tmpjson);
				}
			}
			
		}
		return retjsob;
		
	}
	
	
	public ArrayList<Object[]> tranSelfData(JSONObject tranjson)
	{
		ArrayList<Object[]> tmp = new ArrayList<Object[]>();
		Iterator<Entry<String, Object>> a = tranjson.entrySet().iterator();
		while(a.hasNext())
		{
			Entry<String, Object> tnt = a.next();
			tmp.add(new Object[]{tnt.getKey(),tnt.getValue()});
		}
		Collections.sort(tmp,new Comparator(){
			public int compare(Object o1, Object o2) {  
		        if(null!=o1&&null!=o2)  
		        {  
		        	Object[] menu1=(Object[])o1;  
		        	Object[] menu2=(Object[])o2;  
		            if(((String)menu1[0]).compareTo(((String)menu2[0]))>0)
		            {  
		            	//System.out.println((String)menu1[0]+"-------"+menu2[0]+"111");
		                return 1;  
		            }else {  
		            	//System.out.println((String)menu1[0]+"-------"+menu2[0]+"000");
		                return -1;  
		            }  
		        }  
		        return 0;  
		    }  
		});
		return tmp;
	}

	public static void main(String[] args) throws SAXException, Exception 
	{
		// TODO Auto-generated method stub
		TopicInvestHistory tphistory = new TopicInvestHistory();
		tphistory.initKxianJsonData("20150727-000000", "20151028-173502");
		tphistory.initObjBsPrice("2015-07-28");
		tphistory.jsObjZhangfu();
		JSONObject rs = tphistory.getZfresult();
		System.out.println("自己计算ID52的主题投资20150728~20151028时间的json数据="+rs);
		ArrayList<Object[]> a = tphistory.tranSelfData(rs);
		//通过接口获取涨幅/topicinvest/history
		tphistory.initInterfaceKXianData();
		ArrayList<Object[]> b = tphistory.getItfData();
		TopicInvestHistory.printArrayList(a);
		TopicInvestHistory.printArrayList(b);
		boolean rssign = TopicInvestHistory.compareArrayList(b, a);
		if(!rssign)
		{
			System.out.println("测试失败");
		}
	}

}
