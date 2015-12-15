package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MyQuoteKlineUtil {
	
	/**
	 * 根据{@code objcode}（股票代码）取得K线。
	 * @param jsonstring 
	 * @param objcode
	 * @return JSONObject
	 */
	public static JSONArray getQuoteKlineByObjCode(String jsonstring,String objcode)
	{
		/*以前json格式
{"Qid":"","Err":0,"Counter":1,"Data":{"Id":21,"RepDataQuoteKlineSingle":[{"Obj":"SH600000","Data":[{"shiJian":365525873920,"kaiPanJia":1579,"zuiGaoJia":1592,"zuiDiJia":1575,"shouPanJia":1582,"chengJiaoLiang":24428302140,"chengJiaoE":38639438412800,"chengJiaoBiShu":680743}]}]}}
		*/
		/*现在使用json table格式
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":4,"JsonTbl":{"head":["RepDataQuoteKlineSingle"],"data":[[{"head":["Obj","Data"],"data":[["SH600008",{"head":["ShiJian","KaiPanJia","ZuiGaoJia","ZuiDiJia","ShouPanJia","ChengJiaoLiang","ChengJiaoE","ChengJiaoBiShu"],"data":[[1448467200,11.42,11.58,11.26,11.34,36234900,4.13318592e+08,21630]]}],["SZ000777",{"head":["ShiJian","KaiPanJia","ZuiGaoJia","ZuiDiJia","ShouPanJia","ChengJiaoLiang","ChengJiaoE","ChengJiaoBiShu"],"data":[[1448467200,36.2,36.4,35.68,35.94,6160600,2.21734896e+08,9086]]}]]}]]}}}
		 */
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//将url请求获取的内容解析为object
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取object的Data(object)部分
		JSONObject jsonQDSObject1 = jsonMSG.getJSONObject("JsonTbl");
		//获取Data中的JsonTbl(object)
		JSONArray jsonQDSArray1 = jsonQDSObject1.getJSONArray("data");
		//获取JsonTbl中的data(array)
		JSONArray jsonQDSArray2 =  jsonQDSArray1.getJSONArray(0);
		//data数组的第一个元素(array)
		
		for(int i=0;i<jsonQDSArray2.size();i++)
		{
			JSONObject jsonQDS =  jsonQDSArray2.getJSONObject(i);
			JSONArray jsonQD = jsonQDS.getJSONArray("data");
			/*String obj ="SH600008";
			return jsonQD;*/
			for(int j=0;j<jsonQD.size();j++)
			{
				JSONArray jsonQD1 = jsonQD.getJSONArray(j);//["SH600008",{"head":["ShiJian","KaiPanJia","ZuiGaoJia","ZuiDiJia","ShouPanJia","ChengJiaoLiang","ChengJiaoE","ChengJiaoBiShu"],"data":[[1448467200,11.42,11.58,11.26,11.34,36234900,4.13318592e+08,21630]]}]
				String obj =jsonQD1.getString(0);//将Array的第一个元素转换为sring，即"SH600008"
				String obj1[] =objcode.split(",");//把objcode以,拆分，获取每一个代码，存到数组
			    if(obj.equalsIgnoreCase(obj1[j]))
			   {
				System.out.println(jsonQD1);
			   }
			}
			
		}
		return null;
	}
	
	}
	
