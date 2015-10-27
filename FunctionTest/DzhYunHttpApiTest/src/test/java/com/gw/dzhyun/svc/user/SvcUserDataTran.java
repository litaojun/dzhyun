package com.gw.dzhyun.svc.user;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyHttpUtil;

public class SvcUserDataTran 
{
	private String propUrl;
	private String groupUrl;
	private String svcDataFilePath;
	private String rstDataFilePath;
	int[][] gidToPid;
	int[]   pidToValue;
	HashMap propToId;
	public SvcUserDataTran(String propUrl,String groupUrl,String svcDataFilePath,String rstDataFilePath)
	{
	    this.propUrl = propUrl;
	    this.groupUrl = groupUrl;
	    this.svcDataFilePath = svcDataFilePath;
	    this.rstDataFilePath = rstDataFilePath;
	    initData();
	}
	public void initData()
	{
		
	}
	public void intiPropUrl() throws SAXException, Exception
	{
		String retstr = MyHttpUtil. getQuoteDyna(this.propUrl,"json");
		JSONObject data = JSON.parseObject(retstr);
		Object arr = ((JSONObject)data.get("Data")).get("RepDataUserPropsMessage");
		int[]   tmpPidToValue = new int[200];
		int num = 0;
		if(arr  instanceof JSONArray)
		{
			JSONArray jsa = (JSONArray) arr;
			for(Object a :jsa)
			{
				if(a instanceof JSONObject)
				{
					JSONObject curjs = (JSONObject)a;
					int id = curjs.getIntValue("Id");
					int value = Integer.parseInt(curjs.getString("Value"));
					tmpPidToValue[id] = value;
					num++;
				}
			}
		}
		else
			System.out.println("intiPropUrl()调用错误 ");
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
