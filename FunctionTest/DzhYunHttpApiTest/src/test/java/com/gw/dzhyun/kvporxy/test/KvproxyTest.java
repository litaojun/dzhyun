/**
 * @classnmae KvproxyTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.zeromq.ZMQ;

import com.gw.dzhyun.kvporxy.util.EnDecodeSubPubTool;
import com.gw.dzhyun.kvporxy.util.Subscriber;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.util.ReadFileUtil;


/**
 * @author Litaojun
 * @date   2016年3月9日
 */
public class KvproxyTest {
	public static int sign = 0;
	private ZMQ.Context context = null;
	private ZMQ.Socket subscriber = null; 
	private JedisOperator jedis = new JedisOperator();
	
	public KvproxyTest()
	{
		//this.init("tcp://10.15.107.151:10202");
		this.init("tcp://10.15.144.72:10202");
	}
	
	
	public static String getSystemTime(){ 
		Date date=new Date(); 
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(date); 
		} 
	/**
	 * 
	 * @param @param hubsysip
	 * @Title init
	 * @Description 建立订阅连接
	 * @return void
	 *
	 */
	public void init(String hubsysip)
	{
		if(Subscriber.sign == 0)
		{

			this.context = ZMQ.context(100);
			this.subscriber = this.context.socket(ZMQ.SUB);
			this.subscriber.connect(hubsysip);
			Subscriber.sign = 1;
			//this.jedis.init("10.15.144.80",10012);
			this.jedis.init("10.15.144.74",10001);
		}
	}
	
	//@Test
	public void subscribeKeyJzTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String[] src = new String[]{"litaojun","f1","value"};
		byte[] sub = EnDecodeSubPubTool.enCodeSubMessage(src[0], src[1]);
		subscriber.subscribe(sub);
		this.jedis.setHashValue(src[0], src[1], src[2]);
		byte[] message = subscriber.recv(); 
		String[] result = EnDecodeSubPubTool.deCodePubMessage(message, src[1]);
		boolean sign = this.checkKeyFeild(result,src);
		assertTrue(sign);
	}
	
	@Test
	public void testkv() throws FileNotFoundException, IOException, InterruptedException
	{
		
		ArrayList<String>  a = ReadFileUtil.readFileToArrayListByFilePath("E:\\app.log\\test0.dat");
		final ArrayList<String>  b = ReadFileUtil.readFileToArrayListByFilePath("E:\\app.log\\test1.dat");
		
		for(String tmp :a)
		{
			String[] rst = tmp.split(",");
			byte[] sub = EnDecodeSubPubTool.enCodeSubMessage(rst[0], rst[1]);
			System.out.println(String.format("subscribe %s-%s", rst));
			subscriber.subscribe(sub);
		}
		new Thread()
		{
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
		}.start();
		Thread.sleep(100);
		System.out.println("get data start"+KvproxyTest.getSystemTime());
		while(true)
		{
			//System.out.println("xxxxxxxxx");
			System.out.println("get data end"+KvproxyTest.getSystemTime());
		    byte[] message = subscriber.recv(); 
		    String[] result = EnDecodeSubPubTool.deCodePubMessage(message, "sss");
		    System.out.println(String.format("recv data %s-%s", result));
		}
		
	}
	
	 public boolean checkKeyFeild(String[] dest,String[] src)
     {
  	   boolean sign = false;
  	   if(dest!=null && dest.length==2)
  	   {
  		   if(dest[0].equals(src[0]) && dest[1].equals(src[1]))
  		   {
  			   sign = true;
  		   }
  			   
  	   }
  	   if(sign)
  	      System.out.println("测试成功");
  	   else
  		   System.out.println("测试失败");
  	   
  	   return sign;
     }

}
