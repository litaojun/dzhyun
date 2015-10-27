package com.gw.dzhyun.svc.stkinfodata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;
import com.gw.dzhyun.util.BytesHexStringTran;
import com.gw.dzhyun.util.ReadFileUtil;

public class TestCaseStkinfo 
{
	public boolean testCompareCount()
	{
		boolean ret = true;
		TestSvcStkinfoData tsd = new TestSvcStkinfoData();
		JSONObject mysqsj = tsd.getMysqlDataLen();
		System.out.println(mysqsj.toJSONString());
		JSONObject redisjs = tsd.getRedisDataLen(new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
		
		System.out.println(redisjs.toJSONString());
		Iterator<String> keys = redisjs.keySet().iterator();
		while(keys.hasNext())
		{
			String tmpkey = keys.next();
			if(redisjs.get(tmpkey) != mysqsj.get(tmpkey))
			{
				ret = false;
				break;
			}
		}
		return ret;
	}
	public void writeRedisDataToFile(String pathstr,String[] keys) throws IOException
	{
		TestSvcStkinfoData tsd = new TestSvcStkinfoData();
		tsd.writeRedisByteTofile(pathstr, keys);
	}
	public boolean testRedisMysql(String key) throws IOException
	{
		boolean ret = true;
		TestSvcStkinfoData tsd = new TestSvcStkinfoData();
		//tsd.writeRedisByteTofile("C:\\litaojun\\", new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
		ArrayList<byte[]> albQian = tsd.readFileDataToByteArr("C:\\litaojun\\A30" + key + ".dat");
		ArrayList<String>  albQianStr = tsd.byteArrTranStrArr(albQian);
		ArrayList<byte[]> albHou = tsd.getArrListByReidsKey(key);
		ArrayList<String>  albHouStr = tsd.byteArrTranStrArr(albHou);
//		ReadFileUtil.printByteArr(albQian);
//		System.out.println("\r\nlitaojun\r\n");
//		ReadFileUtil.printByteArr(albHou);
//		System.out.println("\r\nlitaojuntest\r\n");
		System.out.println("albQianStr="+albQianStr);
		System.out.println("albHouStr="+albHouStr);
		
		if(!albHouStr.containsAll(albQianStr))
			ret = false;
//		for(String tmstr  : albQianStr)
//		{
//			if(!albHouStr.contains(tmstr))
//			{
//				System.out.println(tmstr);
//				ret = false;
//				break;
//			}
//		}
		return ret;
		
	}
	public boolean executeCase() throws IOException
	{
	    //this.writeRedisDataToFile("C:\\litaojun\\", new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
	    return this.testRedisMysql("SH");
	}
	public static void main(String[] args) throws IOException
	{
		TestCaseStkinfo tf = new TestCaseStkinfo();
		if(tf.executeCase())
		{
			System.out.println("测试通过 ");
		}
		else
			System.out.println("测试失败 ");
	}

}
