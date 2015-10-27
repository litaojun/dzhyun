package com.gw.dzhyun.app.topicinvest.f10;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.util.TranYfloatMain;

public class F10CwtsZycwzb {
	@ Test
	public void testF10CwtsZycwzbObja() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/zycwzb?obj=SH600128&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsZycwzbOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10CwtsZycwzbObjb() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/zycwzb?obj=SH600128&field=gdqy,mgwfplr,mggjj,xsmll,ldbl&start=-3&count=2&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsZycwzbOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	@ Test
	public void testF10CwtsZycwzbObjc() throws SAXException, Exception
	{
		String urlstr = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/f10/cwts/zycwzb?obj=SH600128&field=gdqy,mgwfplr,mggjj&start=0&count=5&token=44a92b0851fb47c8841602984efbfff7";
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		//System.out.println("retstr="+retstr+"\n");
		JSONObject data = JSON.parseObject(retstr);
		TranYfloatMain tym = new TranYfloatMain(data,"RepDataF10CwtsZycwzbOutput");
		JSONObject tranjson = tym.dealJsonArray();
		System.out.println(tranjson+"\n");
	}
	public static void printHexString( byte[] b) {     
		StringBuffer strbf = new StringBuffer();
		   for (int i = 0; i < b.length; i++) {    
		     String hex = Integer.toHexString(b[i] & 0xFF);    
		     if (hex.length() == 1) {    
		       hex = "0" + hex;    
		     }    
		     strbf.append("\\x"+hex);
		    // System.out.print(hex );    
		   }    
		   System.out.print(strbf.toString() );    
		}  
	/**  
	    * 将byte[]转换成string    
	    * @param butBuffer  
	    */  
	    public static String byteToString(byte [] b)   
	    {   
	           StringBuffer stringBuffer = new StringBuffer();   
	           for (int i = 0; i < b.length; i++)   
	           {   
	               stringBuffer.append((char) b [i]);   
	           }   
	           return stringBuffer.toString();   
	    }   
	    public static String bytesToHexString(byte[] src){  
	        StringBuilder stringBuilder = new StringBuilder("");  
	        if (src == null || src.length <= 0) {  
	            return null;  
	        }  
	        for (int i = 0; i < src.length; i++) {  
	            int v = src[i] & 0xFF;  
	            String hv = Integer.toHexString(v);  
	            if (hv.length() < 2) {  
	                //stringBuilder.append("0");  
	                hv = "0" + hv;
	            }  
	            stringBuilder.append("\\x"+hv);  
	        }  
	        return stringBuilder.toString();  
	    }  
	    public static String bytesToHexStringRsers(byte[] src){  
	        StringBuilder stringBuilder = new StringBuilder("");  
	        if (src == null || src.length <= 0) {  
	            return null;  
	        }  
	        for (int i = src.length-1; i >= 0; i--) {  
	            int v = src[i] & 0xFF;  
	            String hv = Integer.toHexString(v);  
	            if (hv.length() < 2) {  
	                //stringBuilder.append("0");  
	                hv = "0" + hv;
	            }  
	            stringBuilder.append("\\x"+hv);  
	        }  
	        return stringBuilder.toString();  
	    }  
	      
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisOperator jsop = new JedisOperator();
		 IoBuffer ioBuffer = IoBuffer.allocate(1024);   
		 ioBuffer.put("B00".getBytes());
         ioBuffer.putInt(10172);
         ioBuffer.flip();
         byte[] a = new byte[ioBuffer.limit()];
         ioBuffer.get(a);
         System.out.println(a.length);
         System.out.println("litaojun="+jsop.getByte(a));
         //printHexString(a);
         System.out.println(bytesToHexString(a));
         System.out.println("'B00"+bytesToHexString(a)+"'");
         String keyname = "B00"+bytesToHexString(a);
         
         System.out.println("keyname="+keyname);
         String keyvalue = jsop.getValueByKey(keyname);
         System.out.println(keyvalue);
	}

}
