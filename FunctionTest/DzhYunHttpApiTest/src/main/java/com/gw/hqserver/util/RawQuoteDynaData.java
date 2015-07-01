package com.gw.hqserver.util;

/**
 * 从QTRS（王建平）得到原始动态行情数据。
 * @author Administrator
 *
 */
public class RawQuoteDynaData {
	//1，得到的数据如下：
	//SH601519	1425884400	0.000000	24.200001	23.700001	25.350000	23.010000	24.830000	

	//1237583.000000	3033839616.000000	0.000000	

	//24.780001	24.770000	24.760000	24.750000	24.740000	
	//340.000000	225.000000	2112.000000	4626.000000	3885.000000	

	//24.799999	24.809999	24.820000	24.830000	24.840000	
	//247.000000	254.000000	187.000000	623.000000	10.000000	

	//73713	26.620001	21.780001	511024.000000	726559.000000	0	0	
						
	//2，对应数据格式(与上面1的数据一一对应)：
	/*
	对应数据结构说明：
	obj 16c 股票代码，如 SH60000.stk
	time i 时间
	avgvolume f 5 日均量  （目前不计算,也就是0）
	lastclose f 昨收
	open f 今开
	high f 最高
	low f 最低
	new f 最新

	volume f 成交量
	amount f 成交额
	nowvol f 现手

	buyprice 5f 委买价
	buyvolume 5f 委买量

	sellprice 5f 委卖价
	sellvolume 5f 委卖量

	tickcount i 成交笔数
	advstop f 涨停
	decstop f 跌停
	inner f 内盘
	outter f 外盘
	openinterest 持仓量（期货）
	settleprice 结算价（期货）
	*/
						
	//3，对应的数据类型
	/*
	c 1 字节有符号整数 
	h 2 字节有符号整数 
	i 4 字节有符号整数 
	q 8 字节有符号整数 
	f 4 字节浮点类型 
	d 8 字节浮点类型 
	s 8 字节指向字符串的指针 
	t 8 字节指向 table_t 的指针 
	C 1 字节无符号整数 
	H 2 字节无符号整数 
	I 4 字节无符号整数 
	Q 8 字节无符号整数 
	b 8 字节指向变长二进制的指针 
	T 8 字节时间类型数 
	p 8 字节定点浮点数 
	v 8 字节定点浮点数
	 */
	//下述字段全部存到字符串中
	public String obj;// 16c 股票代码，如 SH60000.stk
	public String time;// i 时间
	public String avgvolume ;//f 5 日均量  （目前不计算,也就是0）
	public String lastclose;// f 昨收
	public String open;// f 今开
	public String high;// f 最高
	public String low;// f 最低
	public String newPrice;// f 最新

	public String volume;// f 成交量
	public String amount;// f 成交额
	public String nowvol;// f 现手

	public String[] buyprice ;//5f 委买价，5组浮点数
	public String[] buyvolume;// 5f 委买量

	public String[] sellprice;// 5f 委卖价
	public String[] sellvolume;// 5f 委卖量

	public String tickcount;// i 成交笔数
	public String advstop;// f 涨停
	public String decstop;// f 跌停
	public String inner;// f 内盘
	public String outter;// f 外盘
	public String openinterest;// 持仓量（期货），不用
	public String settleprice;// 结算价（期货），不用
	
	public RawQuoteDynaData()
	{
		buyprice = new String[5] ;//5f 委买价，5组浮点数
		buyvolume= new String[5];// 5f 委买量

		sellprice= new String[5];// 5f 委卖价
		sellvolume= new String[5];// 5f 委卖量
	}
	
	/**
	 * 从字符串（以\t分割）中得到各个字段值
	 * @param table
	 * @return
	 */
	public boolean getFiledsFromString(String table)
	{
		String[] allfileds = table.split("\t");
		String field  = "";
		for(int i=0;i<allfileds.length;i++)
		{
			field = allfileds[i].trim();
			if(field != "" || field != null)
			{
				switch (i)
				{
				case 0:  
					obj = field;// 16c 股票代码，如 SH600000.stk。目前是没有带.stk后缀的。
					break;
				case 1:
					time = field;// i 时间
					break; 
				case 2:
					avgvolume = field ;//f 5 日均量  （目前不计算,也就是0）
					break;
				case 3:
					lastclose = field ;// f 昨收
					break;
				case 4:
					open=field ;// f 今开
					break;
				case 5:
					high=field ;// f 最高
					break;
				case 6:
					low=field ;// f 最低
					break;
				case 7:
					newPrice=field ;// f 最新
					break;
				case 8:
					volume=field;// f 成交量
					break;
				case 9:
					amount=field;// f 成交额
					break;
				case 10:
					nowvol=field;// f 现手
					break;
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
					buyprice[i -11] = field;
					break ;//5f 委买价，5组浮点数
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
					buyvolume[i -16] = field;// 5f 委买量
					break;
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
					sellprice[i -21] = field;// 5f 委卖价
					break;
				case 26:
				case 27:
				case 28:
				case 29:
				case 30:
					sellvolume[i -26] = field;// 5f 委卖量
					break;
				case 31:
					tickcount = field;// i 成交笔数
					break;
				case 32:
					advstop = field;// f 涨停
					break;
				case 33:
					decstop = field;// f 跌停
					break;
				case 34:
					inner = field;// f 内盘
					break;
				case 35:
					outter = field;// f 外盘
					break;
				case 36:
					openinterest = field;// 持仓量（期货），不用
					break;
				case 37:
					settleprice= field;// 结算价（期货），不用
					break;
				default:
					break;
				}
			}


		}
		return true;
	}
	
	public String toString()
	{
		String ret;
		ret = "RawQuoteDynaData have:"
				+ "\nobj=" + obj
				+"\ntime="+time
				+"\navgvolume=" + avgvolume
				+"\nlastclose="+lastclose
				+"\nopen="+open
				+"\nhigh="+high
				+"\nlow="+low
				+"\nnewPrice="+newPrice
				+"\nvolume="+volume
				+"\namount="+amount
				+"\nnowvol="+nowvol
				+"\nbuyprice[]=" + buyprice[0] +"," +  buyprice[1] +"," +   buyprice[2]   +"," +  buyprice[3]  + "," +  buyprice[4] 
				+"\nbuyvolume[]=" + buyvolume[0] +"," +   buyvolume[1] +"," +   buyvolume[2]   +"," +  buyvolume[3]  +"," +   buyvolume[4] 
				+"\nsellprice[]=" + sellprice[0] +"," +   sellprice[1] +"," +   sellprice[2]   +"," +  sellprice[3]  + "," +  sellprice[4] 
				+"\nsellvolume[]=" + sellvolume[0] +"," +   sellvolume[1] +"," +   sellvolume[2]   +"," +  sellvolume[3]  + "," +  sellvolume[4] 
				+"\ntickcount=" + tickcount
				+"\nadvstop="+advstop
				+"\ndecstop="+decstop
				+"\ninner="+inner
				+"\noutter="+outter
				+"\nopeninterest="+openinterest
				+"\nsettleprice="+settleprice;
		return ret;
	}
}
