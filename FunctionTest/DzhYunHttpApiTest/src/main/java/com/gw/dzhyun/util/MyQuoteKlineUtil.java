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
	public static JSONObject getQuoteKlineByObjCode(String jsonstring,String objcode)
	{
		/*
{"Qid":"","Err":0,"Counter":1,"Data":{"Id":21,"RepDataQuoteKlineSingle":[{"Obj":"SH600000","Data":[{"Time":1423013460,"High":1450,"Open":1450,"Low":1447,"Close":1449,"Volume":6.2936e+06,"Amount":9.12377e+09,"TickCount":1487,"Advance":0,"Decline":0}]},{"Obj":"SZ000001","Data":[{"Time":1423013460,"High":1403,"Open":1400,"Low":1400,"Close":1400,"Volume":2.6772e+06,"Amount":3.7507812e+09,"TickCount":805,"Advance":0,"Decline":0}]}]}}
		*/
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataQuoteKlineSingle");  //QuoteDynaSingle数组
		
		for(int i=0;i<jsonQDSArray.size();i++)
		{
			JSONObject jsonQDS =  jsonQDSArray.getJSONObject(i);
			String obj =jsonQDS.getString("Obj");
			if(obj.equalsIgnoreCase(objcode))
			{
				//GET QuoteDyna
				JSONObject jsonQD = jsonQDS.getJSONObject("Data");//QuoteDyna
				System.out.println(jsonQD.toJSONString());
				return jsonQD;
			}
		}
		return null;
	}
}
