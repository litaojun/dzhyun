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
	private BinaryJedis bjess = new BinaryJedis("10.15.144.81", 10012) ;
	private Jedis  jedis = null;
	public JedisOperator()
	{
		
	}
	public void init(String redisip,int port)
	{
		if(jedis!=null && jedis.isConnected())
			return;
		 //jedis = new Jedis("10.15.144.81", 10012);
		jedis = new Jedis(redisip, port);
	}
	public void closeJedis()
	{
		
		this.jedis.close();
	}
	
	public void setHashValue(String key,String field,String value)
	{
		this.jedis.hset(key, field, value);
	}
	public ArrayList<String> getJredisList(String keystr,int start,int end)
	{
		this.init("10.15.144.81", 10012);
		ArrayList<String> als = (ArrayList<String>) jedis.lrange(keystr,start,end);
		for(String tmstr:als)
		{
			System.out.println(tmstr);
		}
		return als;
	}
	public ArrayList<byte[]> getJredisList(byte[] keystr,int start,int end)
	{
		this.init("10.15.144.81", 10012);
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
		this.init("10.15.144.81", 10012);
		String a = this.jedis.get(key);
		String b = this.jedis.type(key);
		System.out.println(String.format("a=%s,key=%s,b=%s", new String[]{a,key,b}));
		return a;
	}
	public byte[] getByte(byte[] keys)
	{
		byte[] rsby = this.bjess.get(keys);
		//System.out.println("keytype="+this.bjess.type(keys));
		//System.out.println("rsby len="+rsby.length);
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
		this.init("10.15.144.81", 10012);
		byte[] a=null;
		a = this.bjess.get(key.getBytes());
		//this.bjess.
		
		//a = this.jedis.get(key.getBytes(Charset.forName("UTF-8")));
		 //System.out.println("a="+a[100]+"a.len="+a.length);
		return a;
	}
	

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
		this.init("10.15.144.81", 10012);
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
		this.init("10.15.144.81", 10012);
		num = this.jedis.del(keystr);
		return num;
	}
	public static void main(String[] args)
	{
		
	}
}
