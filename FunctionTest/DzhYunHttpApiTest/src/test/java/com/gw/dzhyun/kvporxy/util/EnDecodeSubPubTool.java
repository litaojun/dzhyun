/**
 * @classnmae EnDecodeSubPubTool.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.util;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * @author Litaojun
 * @date   2015年12月16日
 */
public class EnDecodeSubPubTool {

	/**
	 * 
	 * @param @param key    订阅的KEY
	 * @param @param keyjz  hash表健值，如果非空，则为hash表的KEY-fild的订阅
	 *                               如果空，则为KEY的订阅
	 * @param @return
	 * @Title enCodeSubMessage
	 * @Description 将key和fild字段编码，得到订阅消息的字节流
	 * @return byte[]
	 *
	 */
	
	public static byte[] enCodeSubMessage(String key,String keyjz)
	{
		byte[] encode= null;
	    if(keyjz!=null && !keyjz.equals(""))
	    {
	    	 IoBuffer ioBuffer = IoBuffer.allocate(1024);
			 ioBuffer.put((byte)'+');
			 ioBuffer.put((byte)key.length());
			 ioBuffer.put(key.getBytes());
			 ioBuffer.put((byte)keyjz.length());
			 ioBuffer.put(keyjz.getBytes());
			 ioBuffer.flip();
			 encode = new byte[ioBuffer.limit()];
			 ioBuffer.get(encode);
	    }
	    else
	    {
	    	encode = key.getBytes();
	    }
		return encode;
	}
	
	/**
	 * 
	 * @param @param message
	 * @param @param keyjz  hash表健值，如果非空，则为hash表的KEY-fild的订阅
	 *                               如果空，则为KEY的订阅
	 * @param @return
	 * @Title deCodePubMessage
	 * @Description 将字节解码，得到对应的KEY和键值
	 * @return String[]
	 *
	 */
	public static String[] deCodePubMessage(byte[] message,String keyjz)
	{
		String[] retarr = new String[2];
		 if(keyjz!=null && !keyjz.equals(""))
		 {
			 IoBuffer ioBuffer = IoBuffer.allocate(message.length);   
			 ioBuffer.put(message);
			 ioBuffer.flip();
			 byte a = ioBuffer.get();
			 //System.out.println("a= " + (char)a);
			 int keylen = (int)ioBuffer.get();
			// System.out.println("keylen= " + keylen);
			 byte[] sub = new byte[keylen];
			 ioBuffer.get(sub);
			 //System.out.println("receive key: " + new String(sub)); 
			 retarr[0] = new String(sub);
			 int fildlen = ioBuffer.get();
			//System.out.println("fildlen= " + keylen);
			 byte[] fild = new byte[fildlen];
			 ioBuffer.get(fild);
			 //System.out.println("receive fild: " + new String(fild)); 
			 retarr[1] = new String(fild);
		 }
		 else
		 {
			 retarr[0] = new String(message);
			 retarr[1] = null;
		 }
		return retarr;
	}
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Subscriber a = new Subscriber("tcp://10.15.107.151:10202",null);
		Object obj = a;
		System.out.println(obj.getClass().getName());
	}

}
