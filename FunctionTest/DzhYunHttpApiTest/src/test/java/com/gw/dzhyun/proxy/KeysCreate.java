package com.gw.dzhyun.proxy;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;



import javax.sound.sampled.AudioFormat.Encoding;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.fjjj.Dzhfenjj;

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

	public static void main(String[] args) throws InvalidProtocolBufferException
	{
		JedisOperator jed = new JedisOperator();
		byte[] b = jed.getByteByKey("A10");

		System.out.println(b.toString());
        //String a = jed.getValueByKey("A10");
       //System.out.println( a.length()); 
//        System.out.println(a.getBytes(Charset.forName("utf-16")));
//        Dzhfenjj.FenJiJiJin x = Dzhfenjj.FenJiJiJin.parseFrom(a.getBytes(Charset.forName("utf-16")));
//        System.out.println(x.getMShangZheXuZhang());
		
		//Dzhfenjj.FenJiJiJin fjj = Dzhfenjj.FenJiJiJin.parseFrom(b);
		Dzhfenjj.FenJiJiJinJingTai fjjdt = Dzhfenjj.FenJiJiJinJingTai.parseFrom(b);
		int i = fjjdt.getShuJuCount();
		 
		 double a = fjjdt.getShuJu(1).getAZuiXinJingZhi();
		 System.out.println("i="+i+"a="+a);
	}
}
