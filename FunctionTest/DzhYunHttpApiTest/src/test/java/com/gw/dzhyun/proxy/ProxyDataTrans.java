package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.Math;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gw.dzhyun.proxy.Dzhstorageproxy.ListStoreRequest;

public class ProxyDataTrans {
	public static JSONObject  getResultBDzhstorageResponse(Dzhstorageproxy.StoreResponse rst)
	{
		List<Dzhstorageproxy.CommonResponse> dcr = (List<Dzhstorageproxy.CommonResponse>)rst.getCommonList();
		int allcount = rst.getCommonCount();
		int errcount=0,succount=0;
		List ts = new ArrayList();
		HashMap hm = new HashMap();
		for(Dzhstorageproxy.CommonResponse dcrtm : dcr)
		{
			int itm = dcrtm.getResult();
			if(itm == 0)
				succount++;
			else
				errcount++;
			ts.add(dcrtm.getResult());
		}
		hm.put("errcount", errcount);
		hm.put("succount", succount);
		hm.put("rsArray", ts);
		JSONObject jsb = new JSONObject();
		jsb.putAll(hm);
		System.out.println(jsb.toJSONString());
		return jsb;
	}
	public static ArrayList<String> getListByStoreRequestOrKey(String key,Dzhstorageproxy.StoreRequest dst)
	{
	   List<String> a = null;
		for(ListStoreRequest x:dst.getListStoreList())
		{
			String tmp = x.getTitlegroup()+x.getObj();
			if(tmp.equals(key))
			{
				a = x.getDataarrayList();
			}
		}
		System.out.println("getListByStoreRequestOrKey--a="+a);
		return (ArrayList<String>)a;
	}
	public static String[] getKeysByStoreRequest(Dzhstorageproxy.StoreRequest dst)
	{
		System.out.println("dst.getListStoreList().size()="+dst.getListStoreList().size());
		String[] a = new String[dst.getListStoreList().size()];
		int i= 0;
		for(ListStoreRequest x:dst.getListStoreList())
		{
			System.out.println("xx");
			a[i] = x.getTitlegroup() + x.getObj();
		}
		return a;
		
	}
	//
	public static Dzhstorageproxy.StoreRequest crateStoreRequest(KeysCreate keyc,int lsrlen ,int listlen,boolean direction,int limit )
	{
		ArrayList<ListStoreRequest> dlslist = new ArrayList<Dzhstorageproxy.ListStoreRequest>(); 
		System.out.println("1--start--crateStoreRequest=dlslist"+dlslist);
		for(int i=0;i<lsrlen;i++)
		{
			String keys = keyc.getKey();
			ArrayList<String> als = getArrayList(30,listlen);
			System.out.println("keys="+keys+"als="+als);
			Dzhstorageproxy.ListStoreRequest dlsr = crateStoreListStoreRequest(direction,limit,keys.substring(0, 7),keys.substring(7, keys.length()),als);
			System.out.println("crateStoreListStoreRequest="+dlsr.getDataarrayList().toString());
			dlslist.add(dlsr);
		}
		System.out.println("1--end--crateStoreRequest=dlslist"+dlslist);
		 return crateStoreRequest(99,33,dlslist);
	}
	public static Dzhstorageproxy.StoreRequest crateStoreRequest(int id,int seq,List<Dzhstorageproxy.ListStoreRequest> valuess)
	{
		 Dzhstorageproxy.StoreRequest.Builder srbuilder = Dzhstorageproxy.StoreRequest.newBuilder();
		 srbuilder.setId(id);
		 srbuilder.setSeq(seq);
		 srbuilder.addAllListStore(valuess);
		 Dzhstorageproxy.StoreRequest rst = srbuilder.build();
		 return rst;
	}
	public static Dzhstorageproxy.ListStoreRequest crateStoreListStoreRequest(boolean direction, int limit,String titlegroup,String obj,List<String> ls)
	{
		 Dzhstorageproxy.ListStoreRequest dsrt = null;
		  Dzhstorageproxy.ListStoreRequest.Builder  builder =  Dzhstorageproxy.ListStoreRequest.newBuilder();
		  Dzhstorageproxy.StoreRequest.Builder srbuilder = Dzhstorageproxy.StoreRequest.newBuilder();
		  //Dzhstorageproxy.ListStoreRequest rst=null;
		  builder.setDirection(direction);
		  builder.setLimit(limit);
		  builder.setTitlegroup(titlegroup);
		  builder.setObj(obj);
		  builder.addAllDataarray(ls);
		  dsrt = builder.build();
		  System.out.println("crateStoreListStoreRequest="+dsrt.getDataarrayList().toString());
		  return dsrt;
	} 
	public static int getRandomInt(int max)
	{
		max--;
		int i = (int)Math.round(Math.random() * max);
		return i;
		
	}
	public static ArrayList<String> getArrayList(int zflen,int lslen )
	{
		int randnum = ProxyDataTrans.getRandomInt(lslen);
		ArrayList alt = new ArrayList();
		for(int i = 0;i<=randnum;i++)
		{
			String tm = ProxyDataTrans.getRandomString(zflen);
			alt.add(tm);
		}
		return alt;
	}
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisOperator jrdor = new JedisOperator();
		jrdor.getJredisList("A30ZI".getBytes(), 0, 100);
	}

}
