/**
 * @classnmae SinglePropertyUtilTool.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.singleProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.gw.dzhyun.svc.singleProperty.BankuaiShuInfo;
import com.gw.dzhyun.svc.singleProperty.DanShangPinShuXingInfo;

/**
 * @author Litaojun
 * @date   2015年12月3日
 */
public class SinglePropertyUtilTool 
{
	public static boolean hashmapDanShangPinShuXingInfoComare(HashMap<String,DanShangPinShuXingInfo> srcdsp,HashMap<String,DanShangPinShuXingInfo> dstdsp)
	{
		boolean sign = true;
		Set<String> srcset = srcdsp.keySet();
		Set<String> dstset = dstdsp.keySet();
		if(srcset.size() != dstdsp.size())
		{
			sign = false;
			System.out.println("两个hashMap数量不相等，"+String.format("srcset.size=%d,dstset.size=%d", new Object[]{srcset.size(),dstset.size()}));
		}
		else
			if(!srcset.containsAll(dstset))
			{
				System.out.println("两个hashMap的key不一致");
				System.out.println("srcdsp="+srcdsp.toString());
				System.out.println("dstset="+dstset.toString());
				sign = false;
			}
		if(!sign)
			return sign;
		for(String curkeystr : srcset)
		{
			DanShangPinShuXingInfo src = srcdsp.get(curkeystr);
			DanShangPinShuXingInfo dst = dstdsp.get(curkeystr);
			if(src==null||dst==null || !src.equals(dst))
			{
				sign =false;
				break;
			}
		}
		
		return sign;
	}
    public static boolean listStrCompare(ArrayList<String> srclist,ArrayList<String> dstlist)
    {
    	boolean sign = true;
    	boolean a,b,c;
    	a = srclist.containsAll(dstlist);
    	b = dstlist.containsAll(srclist);
    	c = (dstlist.size()==srclist.size());
    	System.out.println("dstlist.size="+dstlist.size());
    	System.out.println("srclist.size="+srclist.size());
    	System.out.println(srclist.toString());
    	System.out.println(dstlist.toString());
    	sign = a&b&c;
    	return sign;
    }
    public static ArrayList<String> jsnArrToArrList(JSONArray jsarr)
    {
    	ArrayList<String> alst = new ArrayList<String>();
    	if(jsarr != null)
    	{
    		for(int i=0;i<jsarr.size();i++)
    		{
    			alst.add(jsarr.getString(i));
    		}
    	}
    	return alst;
    }
    
    public static void printHashMapBankuaiShuInfo(HashMap<String,BankuaiShuInfo> hmapObj)
	{
		Iterator itobj =  hmapObj.keySet().iterator();
		while(itobj.hasNext())
		{
			String a = (String) itobj.next();
			System.out.println("key="+a);
			Object o = hmapObj.get(a);
			if(o instanceof BankuaiShuInfo)
			{
				System.out.println("obj=");
				BankuaiShuInfo tmp = (BankuaiShuInfo) o;
				tmp.printToString();
			}
		}
		
	}
   
	
	
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      String a = String.format("srcset.size=%d,dstset.size=%d", new Object[]{1,2});
      System.out.println(a);
	}

}
