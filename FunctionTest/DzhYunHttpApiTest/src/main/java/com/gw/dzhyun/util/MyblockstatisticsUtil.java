package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MyblockstatisticsUtil {
	
	/**
	 * 根据{@code RepDataBlockPropOutput} 取得板块统计结果。
	 * @param jsonstring 
	 * @return JSONArray
	 */
	public static JSONArray getblockstatisticsProp(String jsonstring)
	{
		
		/*{"Qid":"","Err":0,"Counter":1,"Data":{"Id":73,"RepDataTongJiApp":[{"ChengJiaoE":214827745111,"LiuTongShiZhi":2217044978,"ZongShiZhi":3036493242,"ZhangDiePing":{"ShangZhangJiaShu":867,"XiaDieJiaShu":114,"PingPanJiaShu":91},"LingZhangGu":{"Obj":"SH600808","ZhongWenJianCheng":"马钢股份","ZuiXinJia":3.37,"ZhangFu":10.13},"TingPaiJiaShu":84,"ZhangTingDieTing":{"ZhangTingJiaShu":38,"DieTingJiaShu":2},"GuPiaoGeShu":1072,"PingJunJingTaiShiYingLv":154.5342}]}}
		 * */
		
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataTongJiApp部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataTongJiApp");  //RepDataTongJiApp数组	
			
			if(jsonQDSArray!=null)
			{
				return jsonQDSArray;
			}
	
			return null;	
	}
	public static JSONArray getblockstatisticsObj(String jsonstring)
	{
		
		
		
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataTongJiApp部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataTongJiApp");  //RepDataTongJiApp数组	
			
			if(jsonQDSArray!=null)
			{
				return jsonQDSArray;
			}

			return null;	
	}
}