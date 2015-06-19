package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MykbspiritUtil {
	
	/**
	 * 根据{@code guanJianZiCode}（股票代码）取得K线。
	 * @param jsonstring 
	 * @param guanJianZiCode
	 * @return JSONObject
	 */
	public static JSONArray getkbspiritByGuanJianZi(String jsonstring,String guanJianZiCode)
	{
		/*
{"Qid":"","Err":0,"Counter":1,"Data":{"Id":29,"RepDataJianPanBaoShuChu":[{"GuanJianZi":"p","JieGuo":[{"LeiXing":0,"ShuJu":[{"DaiMa":"SH600000","MingCheng":"浦发银行","ShuXing":"上证A股"}]}]}]}}		
		*/
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataJianPanBaoShuChu部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataJianPanBaoShuChu");  //RepDataJianPanBaoShuChu数组
		
		for(int i=0;i<jsonQDSArray.size();i++)
		{
			JSONObject jsonQDS =  jsonQDSArray.getJSONObject(i);
			String input =jsonQDS.getString("GuanJianZi");
			if(input.equalsIgnoreCase(guanJianZiCode))
			{
				//GET QuoteDyna
				JSONArray jsonQD = jsonQDS.getJSONArray("JieGuo");//QuoteDyna
				//System.out.println(jsonQD.toJSONString());
				return jsonQD;
			}
		}
		return null;
	}
}
