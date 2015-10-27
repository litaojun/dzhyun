package com.gw.dzhyun.svc.block;

import com.gw.dzhyun.proxy.JedisOperator;

public class BlockRedisData 
{
	private JedisOperator jed = new JedisOperator();
	private BlockStruct bstruct = null;
	public BlockRedisData()
	{
		String a  = jed.getValueByKey("A70\\x00\\x00\\x00\\x00\\x00\\x01\\xb6\\x1f");
		//System.out.println(a.length());
		byte[] b = {};
		String retstr = testByte("A1",b,'0',"SH000000");
		System.out.print(retstr); 
	}
	//将指定byte数组以16进制的形式打印到控制台   
	public static String printHexString( byte[] b) 
	{
		String retstr="";
	   for (int i = 0; i < b.length; i++) {    
	     String hex = Integer.toHexString(b[i] & 0xFF);    
	     if (hex.length() == 1) {    
	       hex = "\\x0" + hex;    
	     }    
	     else
	    	 hex = "\\x" + hex;    
	     retstr = retstr + hex;
	    // System.out.print(hex.toUpperCase() ); 
	   }   
	  
	     return retstr;
	}
	public static void main(String[] args)
	{
		BlockRedisData bdd = new BlockRedisData();
	}
	/*
	 * prefix	2	title group
	 * tag	1	mode + encode(len(var))
	 * var	n	user defined n=0|1|2|4|8|16|32|64
	 * suffix	n	target item n=0~64
	 * mode
	 *	     取值                                           含义
	 *	'\x00'	                            保留
	 *	'0'	            default
	 *	'A'	            ssdb
	 *	'K'	                                         保留
	 *	'a'	                                         保留
     *  'k'	                                         保留
	 */
	public static String testByte(String prefix,byte[] var,char mode,String suffix)
	{
		String ret="";
		if(var!=null && var.length!=0)
		{
		      char s = (char)(mode + var.length);
		      ret = ret + prefix + s + printHexString(var)+printHexString(suffix.getBytes());
		}
		else
		{
			ret = ret + prefix + mode + printHexString(suffix.getBytes());
		}
		return ret;
	}

}
