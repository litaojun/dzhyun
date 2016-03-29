/**
 * @classnmae KvproxyKeySubPub.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.util;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Litaojun
 * @date   2015年12月17日
 */
public class KvproxyKeySubPub 
   {
	/**
	 * KVPROXY是否推送标准位
	 */
       public static int sign=0;
       

       @Test
       public void testSubKeyNormal() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
       {
    	   Subscriber a = new Subscriber("tcp://10.15.107.151:10202",null);
    	   String[] dest = a.subscribeKeyJzTest("litaojun", "f1");
    	   boolean sign = false;
    	   if(dest!=null && dest.length==2)
    	   {
    		   if(dest[0].equals("litaojun") && dest[1].equals("f1"))
    		   {
    			   sign = true;
    		   }
    	   }
    	   a.closeSub();
    	   assertTrue(sign);
       }
       
       
       @Test
       public void testSubKeyLong() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
       {
    	   Subscriber a = new Subscriber("tcp://10.15.107.151:10202",null);
    	   String[] dest=null;
    	   boolean sign = false;
    	   dest = a.subscribeKeyJzTest("litaojun", "fffffffffffffffffffffffffffffff");
           if(dest!=null && dest.length==2)
    	   {
    		   if(dest[0].equals("litaojun") && dest[1].equals("fffffffffffffffffffffffffffffff"))
    		   {
    			   sign = true;
    		   }
    	   }
    	   a.closeSub();
    	   assertTrue(sign);
    	   
       }
       
       @Test
       public void testSubKeyTeszf() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
       {
    	   Subscriber a = new Subscriber("tcp://10.15.107.151:10202",null);
    	   String[] dest = a.subscribeKeyJzTest("litaojun", "$f1");
    	   boolean sign = false;
    	   if(dest!=null && dest.length==2)
    	   {
    		   if(dest[0].equals("litaojun") && dest[1].equals("$f1"))
    		   {
    			   sign = true;
    		   }
    	   }
    	   a.closeSub();
    	   assertTrue(sign);
       }
       
       public void testSubKey()
       {
    	   SubKeyThread subthrd = new SubKeyThread("tcp://10.15.107.151:10202","litaojun","f1",this);
    	   subthrd.run();
    	   System.out.println("xxx");
    	   
    	   
       }
       
       @CallBackFunction(id="checkKeyFeild")
       public boolean checkKeyFeild(String[] dest)
       {
    	   boolean sign = false;
    	   if(dest!=null && dest.length==2)
    	   {
    		   if(dest[0].equals("litaojun") && dest[1].equals("f1"))
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
