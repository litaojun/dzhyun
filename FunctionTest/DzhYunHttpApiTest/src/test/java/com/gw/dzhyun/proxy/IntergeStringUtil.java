package com.gw.dzhyun.proxy;

import java.io.UnsupportedEncodingException;

public class IntergeStringUtil
{
	public IntergeStringUtil()
	{
		
	}
	//字符串反转
	public static String reverse4(String s) {
		  return new StringBuffer(s).reverse().toString();
		 }
	//16进制转10进制
	public int hexStringToInt(String hexstr)
	{
		int ret = 0;
		 ret = Integer.valueOf(hexstr,16);
		 System.out.println(ret);
		return ret;
	}
	
	//10进制转16进制
	public String intToHex(int num)
	{
		String ret = Integer.toHexString(num);
		return ret;
	}
	
	//根据编码将字符串转换为字节数组
	public byte[]  strTobyte(String curstr,String charset)
	{
		byte[] sendBytes = null;
		try {
			sendBytes = curstr .getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sendBytes;
	}
	
	//根据编码转换字节数组为字符串
	public String byteToString(byte[] a,String charset)
	{
		String recString = null;
		try {
			recString = new String( a ,charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return recString;
	}
	
	
	public String intTOHexString(int num)
	{
		String hexstr = Integer.toHexString(num) ;
		hexstr = this.resvlseHexString(hexstr);
	    hexstr = this.strAddXiehao(hexstr);
		return hexstr;
	}
	public String resvlseHexString(String curstr)
	{
		int i = 6 - curstr.length();
		StringBuffer strbuf = new StringBuffer();
		
		while(i-- != 0)
			strbuf.append("0");
		strbuf.append(curstr);
		System.out.println(strbuf.toString());
		String rs =strbuf.reverse().toString();
		System.out.println(rs);
		return rs;
	}
	 /**
	  * 字节数组转换成十六进制字符串
	  * @param byte[]
	  * @return HexString
	  */
	 public static final String bytesToHexString(byte[] bArray) {
	  StringBuffer sb = new StringBuffer(bArray.length);
	  String sTemp;
	  for (int i = 0; i < bArray.length; i++) {
	   sTemp = Integer.toHexString(0xFF & bArray[i]);
	   if (sTemp.length() < 2)
	    sb.append(0);
	   sb.append(sTemp.toUpperCase());
	  }
	  return sb.toString();
	 }
	 
	 /**
	  * 十六进制字符串转换成字符串
	  * @param hexString
	  * @return String
	  */
	    public static String hexStr2Str(String hexStr) { 

	        String str = "0123456789ABCDEF"; 
	        char[] hexs = hexStr.toCharArray(); 
	        byte[] bytes = new byte[hexStr.length() / 2]; 
	        int n; 
	        for (int i = 0; i < bytes.length; i++) { 
	            n = str.indexOf(hexs[2 * i]) * 16; 
	            n += str.indexOf(hexs[2 * i + 1]); 
	            bytes[i] = (byte) (n & 0xff); 
	        } 
	        return new String(bytes); 
	    } 
	    
	    /**
	     * 字符串转换成十六进制字符串
	     */ 
	    public static String str2HexStr(String str) { 
	        char[] chars = "0123456789ABCDEF".toCharArray(); 
	        StringBuilder sb = new StringBuilder("");
	        byte[] bs = str.getBytes(); 
	        int bit; 
	        for (int i = 0; i < bs.length; i++) { 
	            bit = (bs[i] & 0x0f0) >> 4; 
	            sb.append(chars[bit]); 
	            bit = bs[i] & 0x0f; 
	            sb.append(chars[bit]); 
	        } 
	        return sb.toString(); 
	    }  
	    
	public String strAddXiehao(String curstr)
	{
		String retstr = "\\x" + curstr.substring(0, 2) + "\\x" + curstr.substring(2, 4) + "\\x" +  curstr.substring(4, 6); 
		System.out.println(retstr);
		return retstr;
	}
	public static void main(String[] args)
	{
		IntergeStringUtil x = new IntergeStringUtil();
       byte[] a = x.strTobyte("litaojun陶俊", "UTF8");
       byte[] b = x.strTobyte("litaojun", "ISO-8859-1");
		System.out.println(bytesToHexString(a));
		System.out.println(bytesToHexString(b));
		String c = x.byteToString(a, "UTF8");
		String d = x.byteToString(b,  "ISO-8859-1");
		System.out.println(c);
		System.out.println(d);
	}

}
