package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class JedisOperator 
{
	private Jedis  jedis = null;
	public void init()
	{
		if(jedis!=null && jedis.isConnected())
			return;
		 jedis = new Jedis("10.15.107.183", 10001);
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
	public long getJredisLLen(String keystr)
	{
		this.init();
		long num = 0;
		num = jedis.llen(keystr);
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
		String[] paraStr = {"FenshiLishi","FenshiDangri",              
                "KXianDangri1min","KXianDangri5min","KXianDangri1day",
                "KXianLishi1min","KXianLishi5min","KXianLishi1day",   
                "KXianDangri1min","KXianDangri5min","KXianDangri1day",
                "KXianLishi1min","KXianLishi5min","KXianLishi1day",
                "FenshiLishi","FenshiDangri"};  
		//System.out.println(al.toString());
		for(int i = 0;i<paraStr.length;i++)
		{
			if(i<8)
				paraStr[i]+=shgpCode;
			else
				paraStr[i]+=szgpCode;
		}
		//System.out.println(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", paraStr));
		for(String keystr:paraStr)
		{
				long a = jed.getJredisLLen(keystr);
				System.out.println(keystr+".size="+a);
		}
		jed.closeJedis();
	}
}
