package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MyBlockUtil {
	
	/**
	 * 根据{@code RepDataBlockPropOutput} 取得板块结果。
	 * @param jsonstring 
	 * @return JSONArray
	 */
	public static JSONArray getBlockProp(String jsonstring)
	{
		
		/*{"Qid":"","Err":0,"Counter":1,"Data":{"Id":34,"RepDataBlockPropOutput":[{"name":["股票\\\\指数成份股\\\\巨潮指数\\\\巨潮行业指数\\\\1000可选",
		*/
		
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataPaiXu部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataBlockPropOutput");  //RepDataBlockPropOutput数组	
			
			if(jsonQDSArray!=null)
			{
				return jsonQDSArray;
			}
	/*if(jsonMSG=="Response fail! result = 10202");  
	 {"Qid":"","Err":-1,"Counter":1,"Data":"Response fail! result = 10202"}
	 */
			return null;	
	}
	public static JSONArray getBlockObj(String jsonstring)
	{
		
		/*{"Qid":"","Err":0,"Counter":1,"Data":{"Id":34,"RepDataBlockPropOutput":[{"name":["股票\\\\指数成份股\\\\巨潮指数\\\\巨潮行业指数\\\\1000可选",
		*/
		
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataPaiXu部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataBlockObjOutput");  //RepDataBlockPropOutput数组	
			
			if(jsonQDSArray!=null)
			{
				return jsonQDSArray;
			}
	/*if(jsonMSG=="Response fail! result = 10202");  
	 {"Qid":"","Err":-1,"Counter":1,"Data":"Response fail! result = 10202"}
	 */
			return null;	
	}
}