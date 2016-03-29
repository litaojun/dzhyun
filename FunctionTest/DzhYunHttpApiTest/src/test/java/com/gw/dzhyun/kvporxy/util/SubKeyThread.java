/**
 * @classnmae SubKeyThread.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Litaojun
 * @date   2015年12月23日
 */
public class SubKeyThread extends Thread {
	private String key;
	private String keyjz;
	private Object o;
	private String hubsysip;
	
	public SubKeyThread(String hubsysip,String key,String keyjz,Object o)
	{
		this.hubsysip = hubsysip;
		this.key = key;
		this.keyjz = keyjz;
		this.o = o;
	}
	public void run() 
	{  
		Subscriber sub = new Subscriber(this.hubsysip,this.o);
		try {
			sub.subscribeKeyJz(this.key, this.keyjz);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	}

}
