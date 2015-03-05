package com.gw.account.httptest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Jedis;

public class Deleteredis {
	 private static  Jedis jedis;//非切片额客户端连接
	

/*
	 public Deleteredis()
	    {   
		     System.out.println(1111);
	    	 jedis = new Jedis("10.15.201.107", 6379);
	    	 System.out.println(222);
	    	 jedis1 = new Jedis("10.15.201.107", 6380);
	    	 jedis2 = new Jedis("10.15.201.108", 6379);
	    	 jedis3 = new Jedis("10.15.201.108", 6380);
	    	
	    }
*/	 
	 public static void Deleteredis(String ip , int port)
	    {   
		 
		     jedis = new Jedis(ip, port);
		     jedis.flushDB();
	    	
	    }
/*	 
	 public void Deleteredis(){
		 jedis.flushDB();
		 System.out.println(jedis);
		 jedis1.flushDB();
		 System.out.println(jedis1);
		 jedis2.flushDB();
		 System.out.println(jedis2);
		 jedis3.flushDB();
		 System.out.println(jedis3);

		 
	 }
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deleteredis("10.15.201.107", 6379);
		Deleteredis("10.15.201.108", 6379);
		Deleteredis("10.15.201.107", 6380);
		Deleteredis("10.15.201.108", 6380);

	}

}
