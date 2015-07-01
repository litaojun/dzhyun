package com.gw.hqserver.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 存放异步请求的到的行情数据，最后比较数据是否一致。
 * @author Administrator
 *
 */
public class HQDataQueues {
	public static Queue<RawQuoteDynaData> HQQTRS = new LinkedList<RawQuoteDynaData>();
	/*
	 * "Obj":"SH600000","Data":{"Time":1426130050,"LastClose":1425,"High":1509,"Open":1465,"Low":1458,"New":1505,"Volume":104319,"Amount":5.0985707e+11
	 *	对应QTRS得到数据的如下字段：
	obj 16c 股票代码，如 SH600000
	time i 时间
	lastclose f 昨收
	open f 今开
	high f 最高
	low f 最低
	new f 最新
	volume f 成交量
	amount f 成交额
	 */

	public static Queue<RawQuoteDynaData> HQDzhYun = new LinkedList<RawQuoteDynaData>();
	
	public static boolean compareHQ()
	{
		boolean value =false;
		
		if(HQQTRS.size() != HQDzhYun.size())
		{
			value =false;
		}
		else
		{
			for(int i=0;i<HQQTRS.size();i++)
			{
				
			}
		}
		
		return value;
	}
}
