package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dzhyun.Dzhoutput.QuoteDyna;

public class MyQuoteDynaUtil {
	
	/**
	 * 根据{@code objcode}（股票代码）取得行情(QuoteDyna)。
	 * @param jsonstring 
	 * @param objcode
	 * @return JSONObject
	 */
	public static JSONObject getQuoteDynaByObjCode(String jsonstring,String objcode)
	{
		/*
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":20,"RepDataQuoteDynaSingle":[{"Obj":"SH600000","Data":{"Time":1423807969,"LastClose":1435,"High":1467,"Open":1450,"Low":1435,"New":1456,"Volume":51472,"Amount":2.4968515e+11}}]}}
		*/
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataQuoteDynaSingle");  //QuoteDynaSingle数组
		
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
