package com.gw.dzhyun.proxy;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;

public class JedisOperator 
{  
	private BinaryJedis bjess = new BinaryJedis("10.15.144.81", 10001) ;
	private Jedis  jedis = null;
	public JedisOperator()
	{
		
	}
	public void init()
	{
		if(jedis!=null && jedis.isConnected())
			return;
		 jedis = new Jedis("10.15.144.81", 10001);
	}
	public void closeJedis()
	{
		this.jedis.close();
	}
	public ArrayList<String> getJredisList(String keystr,int start,int end)
	{
		this.init();
		ArrayList<String> als = (ArrayList<String>) jedis.lrange(keystr,start,end);
		for(String tmstr:als)
		{
			System.out.println(tmstr);
		}
		return als;
	}
	public ArrayList<byte[]> getJredisList(byte[] keystr,int start,int end)
	{
		this.init();
		ArrayList<byte[]> als = (ArrayList<byte[]>) jedis.lrange(keystr,start,end);
//		for(byte[] tmstr:als)
//		{
//			System.out.println(tmstr+"--len="+tmstr.length);
//			for(byte a:tmstr)
//				System.out.println(a);
//				
//		}
		return als;
	}
	public String getValueByKey(String key)
	{
		this.init();
		String a = this.jedis.get(key);
		String b = this.jedis.type(key);
		System.out.println(String.format("a=%s,key=%s,b=%s", new String[]{a,key,b}));
		return a;
	}
	public byte[] getByte(byte[] keys)
	{
		byte[] rsby = this.bjess.get(keys);
		System.out.println("keytype="+this.bjess.type(keys));
		System.out.println("rsby len="+rsby.length);
		return this.bjess.get(keys);
	}
	public Map<byte[],byte[]> getHashByteArr(byte[] keys)
	{
		long num = this.bjess.hlen(keys);
		//this.jedis.hg
		//ArrayList<byte[]> bytels = (ArrayList<byte[]>) this.bjess.hgetAll(keys);
		Map<byte[],byte[]> hashMap= this.bjess.hgetAll(keys);
		return hashMap;
	}
	
	public byte[] getByteByKey(String key)
	{
		this.init();
		byte[] a=null;
		a = this.bjess.get(key.getBytes());
		//this.bjess.
		
		//a = this.jedis.get(key.getBytes(Charset.forName("UTF-8")));
		 //System.out.println("a="+a[100]+"a.len="+a.length);
		return a;
	}
	
	//通过新KEY规则中开头的12，10等过滤KEY
	public ArrayList<String> filterKeys(String[] filter,ArrayList<String> als)
	{
		for(String curstr : als)
		{
			int sign = 0;
			for(String rs : filter)
			{
				if(curstr.startsWith(rs))
				{
					sign = 1;
					break;
				}
			}
			if(sign == 0)
			{
				als.remove(curstr);
			}
		}
		return als;
	}
	public ArrayList<String> getKeys(String keyrule)
	{
		System.out.println("keyrule="+keyrule);
		ArrayList<String> als = new ArrayList<String>();
		Set<String> set = this.jedis.keys(keyrule);
		System.out.println("set.size()="+set.size());
		Iterator<String> iterator = set.iterator();
	    while (iterator.hasNext()){
	    	  als.add(iterator.next());
	      }
	      return als;
	}
	public long getJredisLLen(String keystr)
	{
		this.init();
		long num = 0;
		//System.out.println("keystr="+keystr);
		//num = jedis.llen(keystr);

		try {
			num =jedis.llen(keystr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
		
	}
	
	public Long delKey(String keystr)
	{
		Long num ;
		this.init();
		num = this.jedis.del(keystr);
		return num;
	}
	public static void main(String[] args)
	{
		JedisOperator jed = new JedisOperator();
		//ArrayList  al = jed.getJredisList("KXianDangri1minSZ399965",1,9);
		String shgpCode = "SH600128";
		String szgpCode = "SZ002323";
		String[] ssparaStr = {"FenshiLishi","FenshiDangri",              
                "KXianDangri1min","KXianDangri5min","KXianDangri1day",
                "KXianLishi1min","KXianLishi5min","KXianLishi1day",   
                "KXianDangri1min","KXianDangri5min","KXianDangri1day",
                "KXianLishi1min","KXianLishi5min","KXianLishi1day",
                "FenshiLishi","FenshiDangri"};  
		String[]  paraStr = {"210SH@(\t","200SH@(\t","120SH@(\t","140SH@(\t","160SH@(\t","130SH@(\t","150SH@(\t",
				         "170SH@(\t","\"120SZ\\x13\\t\\x00\"","\"140SZ\\x13\\t\\x00\"","\"160SZ\\x13\\t\\x00\"","\"130SZ\\x13\\t\\x00\"",
				         "\"150SZ\\x13\\t\\x00\"","\"170SZ\\x13\\t\\x00\"","\"210SZ\\x13\\t\\x00\"","\"200SZ\\x13\\t\\x00\""};
		//System.out.println(al.toString());
//		for(int i = 0;i<paraStr.length;i++)
//		{
//			if(i<8)
//				paraStr[i]+=shgpCode;
//			else
//				paraStr[i]+=szgpCode;
//		}
		//System.out.println(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", paraStr));
		for(String keystr:paraStr)
		{
				long a = jed.getJredisLLen(keystr);
				System.out.println(keystr+".size="+a);
		}
		IntergeStringUtil a = new IntergeStringUtil();
		String ts = a.intTOHexString(12537);
		System.out.println("ts="+ts);
		ArrayList ls = jed.getKeys("\"150SZ\\xb5\\x02\\x00\"");
		ls = jed.getJredisList("\"150SZ\\xb5\\x02\\x00\"", 0, 5);
		System.out.println(ls.size());
		ls = jed.filterKeys(new String[]{"12","13","14","15","16","17","20","21"}, ls);
		System.out.println(ls.size());
		System.out.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", ls.toArray()));
		jed.closeJedis();
	}
}
