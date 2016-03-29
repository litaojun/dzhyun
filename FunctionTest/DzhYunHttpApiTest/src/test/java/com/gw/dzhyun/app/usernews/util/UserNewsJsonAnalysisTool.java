/**
 * @classnmae UserNewsJsonAnalysisTool.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.usernews.util;

import java.util.ArrayList;




import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gw.dzhyun.app.usernews.base.UserNewsBody;

/**
 * @author Litaojun
 * @date   2016年2月24日
 */
public class UserNewsJsonAnalysisTool 
{
	public static ArrayList<UserNewsBody> tranJsonToUserNewsBodyArrByType(JSONObject json,String newstype)
	{
		ArrayList<UserNewsBody> retnews = new ArrayList<UserNewsBody>();
		JSONArray news = json.getJSONObject("Data").getJSONArray("RepDataUserNews").getJSONObject(0).getJSONArray(newstype);
		if(news != null)
		{
			for(int i=0;i<news.size();i++)
			{
				UserNewsBody userbody = new UserNewsBody();
				JSONObject jsn = news.getJSONObject(i);
				userbody.paserFromJsonObj(jsn);
				retnews.add(userbody);
			}
		}
		return retnews;
	}
	public static ArrayList<UserNewsBody> tranJsonToBigImageNewsBodyArr(JSONObject json)
	{
		ArrayList<UserNewsBody> retnews = new ArrayList<UserNewsBody>();
		JSONArray news = json.getJSONObject("Data").getJSONArray("RepDataUserNews").getJSONObject(0).getJSONObject("header").getJSONArray("bigImgNews");
		if(news != null)
		{
			for(int i=0;i<news.size();i++)
			{
				UserNewsBody userbody = new UserNewsBody();
				JSONObject jsn = news.getJSONObject(i);
				userbody.paserFromJsonObj(jsn);
				retnews.add(userbody);
			}
		}
		return retnews;
	}
	public static HashMap<String,ArrayList<UserNewsBody>> tranJsonToUserNewsBodyArrMap(JSONObject json)
	{
		HashMap<String,ArrayList<UserNewsBody>> rethash = new HashMap<String,ArrayList<UserNewsBody>>();
		String[] typearr = new String[]{"news","bigImgNews","","","",""};
		for(String typestr : typearr)
		{
			ArrayList<UserNewsBody> a = tranJsonToUserNewsBodyArrByType(json,typestr);
			rethash.put(typestr, a);
		}
		return rethash;
	}
	public static ArrayList<UserNewsBody> tranJsonToUserNewsBodyArr(JSONObject json)
	{
		ArrayList<UserNewsBody> retlist = new ArrayList<UserNewsBody>();
		String[] typearr = new String[]{"news"};
		for(String typestr : typearr)
		{
			ArrayList<UserNewsBody> a = tranJsonToUserNewsBodyArrByType(json,typestr);
			retlist.addAll(a);
		}
		return retlist;
	}
	
	public static ArrayList<UserNewsBody> sortUserNewsBodyListById(ArrayList<UserNewsBody> usernewList)
	{
		Collections.sort(usernewList ,new Comparator(){
			public int compare(Object o1, Object o2) 
			{  
		        if(null!=o1&&null!=o2)  
		        {  
		        	UserNewsBody menu1=(UserNewsBody)o1;  
		        	UserNewsBody menu2=(UserNewsBody)o2;  
		        	int a = Integer.parseInt(menu1.getId());
		        	int b = Integer.parseInt(menu2.getId());
		            if(a<b){  
		                return 1;  
		            }
		            else 
		             if(a>b) 
			            {  
			                return -1;  
			            }  
		        }  
		        return 0;  
		    }  
		});
		return usernewList;
	}

}
