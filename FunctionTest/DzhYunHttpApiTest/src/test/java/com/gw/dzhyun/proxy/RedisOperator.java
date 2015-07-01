package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RList;
import org.redisson.core.RQueue;
import org.redisson.core.RSet;

import redis.clients.jedis.Jedis;

import com.atopcloud.util.MyConfigUtil;

public class RedisOperator {
	Redisson redisson=null;
	public RedisOperator()
	{
		Config config = new Config(); 
		String proxyConnStr = MyConfigUtil.getConfig("proxyConnStr");
        //config.useSingleServer().setConnectionPoolSize(10);
        config.useSingleServer().setAddress("10.15.107.183:10001");  
        redisson = Redisson.create(config);  
        System.out.println("reids连接成功..."); 
	}
	public List<String> getJredisList(String keystr,int start,int end)
	{
		Jedis  jedis = new Jedis("10.15.107.183", 10001);
		List<String> als = jedis.lrange(keystr,start,end);
		jedis.close();
		for(String tmstr:als)
		{
			System.out.println(tmstr);
		}
		return als;
	}
	public List<String> getQueueByKey(String keystr)
	{
		List<String> rst = new ArrayList<String>();
		rst = this.redisson.getList(keystr);

//		Queue myQueue = this.redisson.getQueue(keystr);  
//		//this.redisson.getList(arg0)
//	    for(int i=0;i<myQueue.size();i++)
//	    {
//	    	rst.add(myQueue.poll().toString());
//	    	//rst.add(myQueue.iterator());
//	    }
		return rst;
	}
	public List<String> getList(String keystr)
	{
		RSet<String> mySet = redisson.getSet("xfpTestKey1-0");
		 RQueue<String> myQueue = redisson.getQueue("xfpTestKey1-0");
		RList<String> myListCache = redisson.getList("xfpTestKey1-0");
		if (myListCache != null) {
			System.out.println("xxx"+mySet.size()+"ss"+myQueue.size()+"ss"+myListCache.size());
			myListCache.clear();
			myListCache.add("ssssssssssssss");
			System.out.println("yyyyyyyyyyyyyyy");
        }    
		System.out.println(myListCache.size());
       for (String bean : myListCache) 
       {
		             System.out.println(bean);
		}
       System.out.println("---------------");
       myListCache.clear();
       myListCache.add("xxxx");
       myListCache.add("xxxx");
       myListCache.add("xxxx");
       myListCache.add("xxxx");
       for (String bean : myListCache) 
       {
		             System.out.println(bean);
		}
       redisson.shutdown();
       return myListCache;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		//RedisOperator rop = new RedisOperator();
		RedisOperator rop = new RedisOperator();
		rop.getJredisList("xfpTestKey1-0",0,100);
		//List<String> ls = rop.getList("xfpTestKey1-0");
		//System.out.println(ls.size());

	}

}
