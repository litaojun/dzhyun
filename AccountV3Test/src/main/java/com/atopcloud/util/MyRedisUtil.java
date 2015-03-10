package com.atopcloud.util;
/**
 * 处理redis查询操作
 * @author Administrator
 *
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

public class MyRedisUtil {
    private  Jedis jedis;//非切片额客户端连接
    
    public MyRedisUtil()
    {   
    	
    	jedis = new Jedis("10.15.108.3", 6379);
    	
    }
    
    
    public  String getValue(String key)
    {   
    	
    	String value = null;
    	if(jedis.exists(key))
    		value= jedis.get(key);
        return value;
    }

    public Jedis getJedis()
    {
    	return this.jedis;
    }
}
