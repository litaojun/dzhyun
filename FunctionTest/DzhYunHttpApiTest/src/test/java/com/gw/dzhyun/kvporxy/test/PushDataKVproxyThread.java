/**
 * @classnmae PushDataKVproxyThread.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.util.ReadFileUtil;

/**
 * @author Litaojun
 * @date   2016年3月14日
 */
public class PushDataKVproxyThread extends Thread {
	 ArrayList<String>  b = null;
	public PushDataKVproxyThread(int i) throws FileNotFoundException, IOException
	{
		String filepath = "E:\\app.log\\test"+i+".dat";
		b = ReadFileUtil.readFileToArrayListByFilePath(filepath);
	}
	public void run()
	{
		JedisOperator jedis = new JedisOperator();
		//jedis.init("10.15.144.80",10012);
		jedis.init("10.15.144.74",10001);
		System.out.println("push data start"+KvproxyTest.getSystemTime());
		for(String tmp :b)
		{
			String[] rst = tmp.split(",");
			 System.out.println(String.format("push data %s-%s-%s", rst));
			jedis.setHashValue(rst[0], rst[1],rst[2]);
		}
		System.out.println("push data end"+KvproxyTest.getSystemTime());
    }

}
