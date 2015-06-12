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
		/*
{"Qid":"","Err":0,"Counter":1,"Data":{"Id":21,"RepDataQuoteKlineSingle":[{"Obj":"SH600000","Data":[{"shiJian":365525873920,"kaiPanJia":1579,"zuiGaoJia":1592,"zuiDiJia":1575,"shouPanJia":1582,"chengJiaoLiang":24428302140,"chengJiaoE":38639438412800,"chengJiaoBiShu":680743}]}]}}
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
				JSONArray jsonQD = jsonQDS.getJSONArray("Data");//QuoteDyna
				//System.out.println(jsonQD.toJSONString());
				return jsonQD;
			}
		}
		return null;
	}
}
