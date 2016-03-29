/**
 * @classnmae Subscriber.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.mina.core.buffer.IoBuffer;
/**
 * @author Litaojun
 * @date   2015年12月11日
 * @http://blog.csdn.net/kobejayandy/article/details/20163345
 */
import org.zeromq.ZMQ;  

import com.gw.dzhyun.util.BytesHexStringTran;

public class Subscriber {  
	private HashMap<String,Method> methodHash = CallBackTool.getMethodByAnnotation(KvproxyKeySubPub.class, CallBackFunction.class);
	public static int sign = 0;
	private ZMQ.Context context = null;
	private ZMQ.Socket subscriber = null; 
	private String[] result = null;
	private Object o;
	public Subscriber(String hubsysip,Object o)
	{
		this.o=o;
		this.init(hubsysip);
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
			this.context = ZMQ.context(1);
			this.subscriber = this.context.socket(ZMQ.SUB);
			this.subscriber.connect(hubsysip);
			Subscriber.sign = 1;
		}
	}
	/**
	 * 
	 * @param @param key   redis中KEY
	 * @param @param keyjz HASH表中的键值
	 * @Title subscribeKeyJz
	 * @Description 根据KEY和键值订阅，如果keyjz为空则表示订阅的key，非空则表示订阅的为key+filed
	 * @return void
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 *
	 */
	public void subscribeKeyJz(String key ,String keyjz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		byte[] sub = EnDecodeSubPubTool.enCodeSubMessage(key, keyjz);
		subscriber.subscribe(sub);
		byte[] message = subscriber.recv(); 
		this.result = EnDecodeSubPubTool.deCodePubMessage(message, keyjz);
		System.out.println("receive key: " + result[0]); 
		if(result[1] != null)
		{
			System.out.println("receive fild: " + result[1]); 
		}
		//回调KvproxyKeySubPub函数
		Method callfun = this.methodHash.get("checkKeyFeild");
		CallBackTool.reflectMethonCallBack(this.o, callfun, new Object[]{});
	}
	
	
	public String[] subscribeKeyJzTest(String key ,String keyjz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		byte[] sub = EnDecodeSubPubTool.enCodeSubMessage(key, keyjz);
		subscriber.subscribe(sub);
		byte[] message = subscriber.recv(); 
		this.result = EnDecodeSubPubTool.deCodePubMessage(message, keyjz);
		System.out.println("receive key: " + result[0]); 
		if(result[1] != null)
		{
			System.out.println("receive fild: " + result[1]); 
		}
        return result;
	}
	
	public void testSub(String key ,String keyjz)
	{
		byte[] sub = EnDecodeSubPubTool.enCodeSubMessage(key, keyjz);
		byte[]  xx = EnDecodeSubPubTool.enCodeSubMessage("xing", "f1");
		byte[]  yy = EnDecodeSubPubTool.enCodeSubMessage("test", "f1");
		subscriber.subscribe(sub);
		System.out.println("xxxx");
		subscriber.subscribe(xx);
		System.out.println("yyyy");
		subscriber.subscribe(yy);
		System.out.println("zzzz");
		while(true)
		{
			byte[] message = subscriber.recv(); 
			this.result = EnDecodeSubPubTool.deCodePubMessage(message, keyjz);
			System.out.println("receive key: " + result[0]); 
			if(result[1] != null)
			{
				System.out.println("receive fild: " + result[1]); 
			}
		}
	}
	
	/**
	 * 
	 * @param 
	 * @Title closeSub
	 * @Description 关闭订阅相关连接
	 * @return void
	 *
	 */
	public void closeSub()
	{
		if(Subscriber.sign == 1)
		{
			 subscriber.close();  
             context.term();
             Subscriber.sign = 0;
		}
		else
		{
			System.out.println("未订阅或已退订");
		}
	}

	public void testOneKey() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String key= "aa";
		String keytype = "hash";
		this.subscribeKeyJz(key, keytype);
	}
    public static void main(String args[]) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {  
    	Subscriber cursub = new Subscriber("tcp://10.15.107.151:10202",null);
    	String key= "litaojun";
		String keytype = "f1";
		//cursub.subscribeKeyJz(key, keytype);
		cursub.testSub(key, keytype);
		cursub.closeSub();
//    	ZMQ.Context context = ZMQ.context(1);  //创建1个I/O线程的上下文  
//        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);     //创建一个sub类型，也就是subscriber类型的socket  
//        System.out.println("receive aaa");  
//        subscriber.connect("tcp://10.15.107.151:10202");    //与在5555端口监听的publisher建立连接  
//        System.out.println("receive bbb");  
//        subscriber.subscribe("aa".getBytes());     //订阅fjs这个channel  
//        System.out.println("receive ccc");  
//          
//        for (int i = 0; i < 100; i++) {  
//            byte[] message = subscriber.recv();  //接收publisher发送过来的消息  
//            System.out.println("receive : " + new String(message)+",message.len="+message.length);  
//        }  
//        subscriber.close();  
//        context.term();
       }  
          

}  
