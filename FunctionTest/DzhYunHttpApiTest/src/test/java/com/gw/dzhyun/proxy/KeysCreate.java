package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.List;

public class KeysCreate {
	public static List<String> grouptitle = null;
	public static List<String> obj = null;
	public static int grouptitlezf = 7;//key的前面长度 
	public static int grouptitlelen = 10;//key的前面长度对应数组
	public static int objzf = 20;
	public static int objlen = 100;
	public KeysCreate()
	{
		if(grouptitle ==null)
		{
			grouptitle =  ProxyDataTrans.getArrayList(KeysCreate.grouptitlezf,KeysCreate.grouptitlelen);
			System.out.println("grouptitle="+grouptitle.toString());
		}
		if(obj ==null)
		{
			obj = ProxyDataTrans.getArrayList(KeysCreate.objzf,KeysCreate.objlen);
			System.out.println("obj="+obj.toString());
		}
	}
	public String getKey()
	{
		String keystr = null;
		int i = ProxyDataTrans.getRandomInt(KeysCreate.grouptitle.size()-1);
		int j = ProxyDataTrans.getRandomInt(KeysCreate.obj.size()-1);
		keystr =  KeysCreate.grouptitle.get(i)+KeysCreate.obj.get(j);
		return keystr;
	}
	public ArrayList<String> getArrayList(int i,int j)
	{
		return ProxyDataTrans.getArrayList(i, j);
	}

}
