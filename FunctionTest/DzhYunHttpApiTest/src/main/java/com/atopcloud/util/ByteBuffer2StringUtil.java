package com.atopcloud.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ByteBuffer2StringUtil {
	/**
	 * 转换ByteBuffer为String类型
	 * @param buffer
	 * @param charsetName
	 * @return
	 */
	public static String byteBuffer2String(ByteBuffer buffer,String charsetName)
	{
		Charset charset  =   null ;
		 CharsetDecoder decoder  =   null ;
		 CharBuffer charBuffer  =   null ;
		  try 
		 {
		  charset  =  Charset.forName( charsetName );//"gb2312"
		  decoder  =  charset.newDecoder();
		  charBuffer  =  decoder.decode(buffer);
		  //System.out.println( " charBuffer= "   +  charBuffer);
		  //System.out.println(charBuffer.toString());
		   } 
		    catch  (Exception ex)
		{
		       ex.printStackTrace();
		        return null;
		   }  
		  return charBuffer.toString();
	}
}
