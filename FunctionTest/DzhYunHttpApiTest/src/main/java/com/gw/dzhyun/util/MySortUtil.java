package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MySortUtil {
	
	/**
	 * 根据{@code RepDataPaiXu} 取得排序结果。
	 * @param jsonstring 
	 * @return JSONArray
	 */
	public static JSONArray getsortByObj(String jsonstring)
	{
		
		/*{"Qid":"","Err":0,"Counter":1,"Data":{"Id":28,"RepDataPaiXu":[{"Obj":"SH000978","Value":385922520},{"Obj":"SH000851","Value":335584738}]}}
		  {"Qid":"","Err":-1,"Counter":1,"Data":"Response fail! result = 10202"}
		*/
		
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataPaiXu部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataPaiXu");  //RepDataPaiXu数组	
			
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