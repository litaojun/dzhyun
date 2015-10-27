package com.gw.dzhyun.util;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
import com.gw.dzhyun.util.Yfloat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyHttpUtil;

public class TranYfloatMain {
	public  Yfloat yf = new Yfloat();
	private JSONArray delJsonArr = null;
	JSONObject  delJson = null;
	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	/*
	 * 构造函数，使用者传入json对象和对应的KEY，其中json为调用接口返回
	 * ，根据接口不同，对应的key=RepDataZhiBiaoShuChu（指标计算），RepDataNewsInfoValue（新闻服务）等
	 */
	public TranYfloatMain(JSONObject jsonobj,String key)
	{
		this.delJson = jsonobj;
		if(jsonobj.get("Data") instanceof JSONObject && jsonobj.getJSONObject("Data").containsKey(key))
		   this.delJsonArr = jsonobj.getJSONObject("Data").getJSONArray(key);

	}
	/*
	 * 构造函数，使用者传入字符串jsonstr对象和对应的KEY，其中jsonstr为调用接口返回字符串
	 * ，根据接口不同，对应的key=RepDataZhiBiaoShuChu（指标计算），RepDataNewsInfoValue（新闻服务）等
	 */
	public TranYfloatMain(String jsonstr,String key)
	{
		this.delJson = JSON.parseObject(jsonstr);
		this.delJsonArr = delJson.getJSONObject("Data").getJSONArray(key);
	}
	
	public TranYfloatMain()
	{
	}
	public void InitJsonArrByStr(String jsonstr,String key)
	{
		this.delJson = JSON.parseObject(jsonstr);
		this.delJsonArr = delJson.getJSONObject("Data").getJSONArray(key);
	}
	public void InitJsonArrByJson(JSONObject jsonobj,String key)
	{
		this.delJson = jsonobj;
		this.delJsonArr = jsonobj.getJSONObject("Data").getJSONArray(key);
	}
	
	/*
	 * 递归调用中，根据KEYS获取的值类型为string,long，调用该函数处理
	 * Object：key对应的值
	 * key：递归中处理的KEY
	 * JSONObject:对应KEY直接父JSON
	 */
	public TranYfloatMain(String jsonstr)
	{
		this.delJson = JSON.parseObject(jsonstr);
		
		String key = this.getKeyByLength(this.delJson);
		this.delJsonArr = delJson.getJSONObject("Data").getJSONArray(key);
	}
	public String getKeyByLength(JSONObject jsonObj)
	{
		Object[] arr =delJson.getJSONObject("Data").keySet().toArray();
		int num = 0;
		int maxlen = 0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].toString().length()>maxlen)
			{
				num =i;
				maxlen = arr[i].toString().length();
			}
		}
		return arr[num].toString();
	}
	public void dealNormalJsonObj(Object obj,String key,JSONObject jsb)
	{
		//System.out.println("处理JSON对象中的普通变量--dealNormalJsonObj");
		if(obj instanceof String)
		{
			return ;
			//System.out.println(String.format("key=%s,and value = %s 为字符类型，无需转换",new String[]{key,obj.toString()}));
		}
		else
		{
			long a = Long.parseLong(obj.toString());
			YfloatObj yoj = this.yf.UnmakeValue(a);
			if(yoj.getdp() == 0)
			{
				//System.out.println(String.format("key=%s,and value = %s 为long类型，需转换，转换后为%s",new String[]{key,obj.toString(),String.valueOf((long)yoj.getValue())}));
				if(!key.equals("ShiJian"))
				   jsb.put(key, (long)yoj.getValue());
				else
				{
					//System.out.println(yoj.getValue());
					jsb.put(key, format.format((long)yoj.getValue()*1000));
				}
			}
			else
			{
				//System.out.println(String.format("key=%s,and value = %s 为double类型，需转换，转换后为%s",new String[]{key,obj.toString(),String.valueOf(yoj.getValue())}));
				jsb.put(key, yoj.getValue());
			}
		}
	}
	/*
	 * 递归调用中，根据KEYS获取的值类型为JSONArray，调用该函数处理
	 * Object：key对应的值
	 * num:值在JSONArray的位置，如0，1，2等
	 * key：递归中处理的KEY
	 * JSONArray:处理的JSON数组
	 */
	public void dealNormalJsonArrObj(Object obj,int num,JSONArray jsbArr,String key)
	{
		//System.out.println("处理JSONARR数组中的普通变量--dealNormalJsonArrObj");
		if(obj instanceof String)
			{
		      return ;
			  //System.out.println(String.format("key=%s,and value = %s 为字符类型，无需转换",new String[]{key,obj.toString()}));
			}
			else
			{
				long a = Long.parseLong(obj.toString());
				YfloatObj yoj = this.yf.UnmakeValue(a);
				//jsbArr.remove(num);
				if(yoj.getdp() == 0)
				{
					//System.out.println(String.format("key=%s,and value = %s 为long类型，需转换，转换后为%s",new String[]{key,obj.toString(),String.valueOf((long)yoj.getValue())}));
					//System.out.println("key="+key);
					if(!key.equals("ShiJian"))
					   jsbArr.set(num, (long )yoj.getValue());
					else
					{
						//System.out.println("key="+key+ format.format((long)yoj.getValue()*1000));
						jsbArr.set(num, format.format((long)yoj.getValue()));
					}
				}
				else
				{
					//System.out.println(String.format("key=%s,and value = %s 为double类型，需转换，转换后为%s",new String[]{key,obj.toString(),String.valueOf(yoj.getValue())}));
					jsbArr.set(num, yoj.getValue());
				}
			}
	}
   /*
    * 处理json数组
    */
	public JSONObject dealJsonArray()
	{
		if(this.delJsonArr==null)
			return null;
		for(int i=0;i<this.delJsonArr.size();i++)
		{
			JSONObject temp = this.delJsonArr.getJSONObject(i);
			this.tranStartJson(temp);
		}
		return this.delJson;
	}
	/*
	 * 递归函数，根据传入的json递归遍历所有key
	 * JSONObject jsb:需要处理的JSONObject
	 */
	public void tranStartJson(JSONObject jsb)
	{
		Iterator<String> keys=jsb.keySet().iterator();
		while(keys.hasNext())
		{
			String temp = keys.next();
			Object o = jsb.get(temp);
			if(o instanceof JSONObject)
			{
				tranStartJson((JSONObject)o);
			}
			else
				if(o instanceof JSONArray)
				{
					JSONArray oo = (JSONArray)o;
					for(int i =0 ;i<oo.size();i++)
					{
						Object tm = oo.get(i);
						if(tm instanceof JSONObject)
						{
							tranStartJson((JSONObject)tm);
						}
						else
							dealNormalJsonArrObj(tm,i,oo,temp);
					}
				}
				else
				{
					dealNormalJsonObj(o,temp,jsb);
				}
		}
		

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JSONObject jsonUAResponse = JSON.parseObject("{\"Qid\":\"\",\"Err\":0,\"Counter\":1,\"Data\":{\"Id\":20,\"RepDataQuoteDynaSingle\":[{\"Obj\":\"SH600128\",\"Data\":{\"Id\":67807630,\"ShiJian\":367723682798,\"ZuiXinJia\":1098,\"KaiPanJia\":898,\"ZuiGaoJia\":1098,\"ZuiDiJia\":898,\"ZuoShou\":998,\"JunJia\":1009,\"ZhangDie\":100,\"ZhangFu\":1002,\"ZhenFu\":2004,\"ChengJiaoLiang\":5470078440,\"XianShou\":655360,\"ChengJiaoE\":5531984989760,\"ZongChengJiaoBiShu\":662359,\"NeiPan\":2283462416,\"WaiPan\":3171608280}}]}}");
//		System.out.println(jsonUAResponse);
//		TranYfloatMain a = new TranYfloatMain(jsonUAResponse,"RepDataQuoteDynaSingle");
//		jsonUAResponse = a.dealJsonArray();
//		System.out.println(jsonUAResponse);
		String[][] a = new String[][]{//{"RepDataNewsInfoValue","http://10.15.144.80/news/get?obj=SH600000&type=1"},
				                                  {"RepDataQuoteDynaSingle","http://10.15.144.80/quote/dyna?obj=SH600128"},
				                                  {"RepDataZhiBiaoShuChu","http://10.15.144.80/indicator/calc?obj=SH600000&name=MA&period=5min&start=0&count=100"},
				                                  {"RepDataJianPanBaoShuChu","http://10.15.144.80/kbspirit?input=MA&type=1"}};
		try {
			for(int i =0;i<a.length;i++)
			{
			    String ret =MyHttpUtil.getQuoteDyna(a[i][1],"json");
			   // System.out.println("转换前"+ret);
			    TranYfloatMain aa = new TranYfloatMain(ret);
			    JSONObject  jsonUAResponse = aa.dealJsonArray();
			   // System.out.println("转换后"+jsonUAResponse);
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
