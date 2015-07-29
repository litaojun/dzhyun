package com.gw.dzhyun.util;

import java.math.BigDecimal;

import com.sun.corba.se.spi.ior.MakeImmutable;

/**
 * float64 <=> int64，服务端做此转换后，经过protobuf压缩传输，以节省带宽
 * 见：http://dms.gw.com.cn/pages/viewpage.action?pageId=131367243
 * @author Administrator
 *
 */
public class Yfloat {
	//定义float64的特征值及特殊数值  
	final int VT_null = 2; 
	int[] VT_dp= {10, 1, 0, 3, 4, 5, 6, 7, 8, 9};
	final int VT_max  = 11;
	final int VT_min  =12;
	final int VT_e    =13;
	//特征值
	final long ValNull        = (long)(2 << 16);  //B[0..7]=[0,0,2,0...],空值
	final long MaxInfinite = (long)(11 << 16 + 0);//最大无穷数
	final long MaxInt32    = (long)(11 << 16 + 1);//最大32位
	final long MaxInt64    = (long)(11 << 16 + 2);//最大64位
	final long MaxFloat32  = (long)(11 << 16 + 3);//最大浮点32位
	final long MaxFloat64  = (long)(11 << 16 + 4);//最大浮点64位
	final long MaxUInt32   = (long)(11 << 16 + 5);//最大无符号32位
	final long MaxUInt64   = (long)(11 << 16 + 6);//最大无符号64位
	final long MinInfinite = (long)(12 << 16 + 0);//最小无穷数
	final long MinInt32    = (long)(12 << 16 + 1);//最小32位整数
	final long MinInt64    = (long)(12 << 16 + 2);//最小64位整数
	final long MinFloat32  = (long)(12 << 16 + 3);//最小浮点32位
	final long MinFloat64  = (long)(12 << 16 + 4);//最小64位浮点数
	public Yfloat()
	{
		
	}
	//放大倍数(浮点型原值 => 转换后整数值)，例如10.80 => 1080，目的是去掉小数点
	final int[] Multiples =
		{
			1,
			10,
			100,
			1000,
			10000,
			100000,
			1000000,
			10000000,
			100000000,
			1000000000
		};
	
	//取得数值类型
	long ValueType(long value){
        long B = (value >> 16) & 0xFF; //B[2]表示数据类型
        return B & 0x0F;    //低4位表示小数位是几位或特殊值
	}
	
	//浮点数转换成整数
	public long MakeValue(double value,  int dp) {
		long H = (long)(0);
		long L = (long)(0);
		if (value < 0) {
			H = 1;
			value = -value;
		}

		long temp = (long)(0);

		if(dp >= 0 && dp <=9) {
				L= VT_dp[dp];
				//temp= (long)(value * Multiples[dp] + 0.5);
				temp= (long)(value * Multiples[dp]);
		}
		else
				System.out.println("MakeValue: invalid decimal places " + dp);
		long longdp= (long)(((H << 4) + L) << 16);  //注意java中移位优先级很低，所有H<<4要括起来。不过go语言中<<高于+。
		//数据存在B[0，1，3，...,7]这7个字节中，其中B[2]存放数据格式和符号等特征值
		//return ((temp >> 16) << 24) + ((H << 4 + L) << 16) + (temp & 0xFFFF);
		return ((temp >> 16) << 24) + longdp + (temp & 0xFFFF);
	}
	
	//还原数值
	public YfloatObj UnmakeValue(long value) {
		YfloatObj floatobj=new YfloatObj();
		if (value < 0) {
			floatobj.set(0, 0, "UnmakeValue: invalid value");
			return floatobj;
		}

		long B = (int) ((value >> 16) & 0xFF);
		int L = (int) (B & 0x0F);
		int H = (int) ((B >> 4) & 0x0F);

		long Bx = ((value >> 24) << 16) + (value & 0xFFFF);
		double temp = 0;
		int dp = 0;
		String msg=null;
		
		switch (L) {
			case 10:
				temp =(double)Bx;
				dp=0;
				break;
				//dp, temp = 0, float64(Bx)		
			case 1:
				temp=(double)Bx / 10;
				dp=1;
				break;
				//dp, temp = 1, float64(Bx) / 10
			case 0:
				temp=(double)Bx / 100;
				dp=2;
				break;
				//dp, temp = 2, float64(Bx) / 100
			case 3:
				temp=(double)Bx / 1000;
				dp=3;
				break;
				//dp, temp = 3, float64(Bx) / 1000
			case 4:
				temp=(double)Bx / 10000;
				dp=4;
				break;
				//dp, temp = 4, float64(Bx) / 10000
			case 5:
				temp=(double)Bx / 100000;
				dp=5;
				break;
				//dp, temp = 5, float64(Bx) / 100000
			case 6:
				temp=(double)Bx / 1000000;
				dp=6;
				break;
				//dp, temp = 6, float64(Bx) / 1000000
			case 7:
				temp=(double)Bx / 10000000;
				dp=7;
				break;
				//dp, temp = 7, float64(Bx) / 10000000
			case 8:
				temp=(double)Bx / 100000000;
				dp=8;
				break;
				//dp, temp = 8, float64(Bx) / 100000000
			case 9:
				temp=(double)Bx / 1000000000;
				dp=9;				
				break;
				//dp, temp = 9, float64(Bx) / 1000000000
			default:
				temp=0;
				dp=0;
				msg = "UnmakeValue: unknown L " + L;
				//return 0, 0, errors.New(fmt.Sprintf("UnmakeValue: unknown L %v", L))
		}

		if( H != 0) {
			temp = -temp;
		}
		floatobj.set(temp, dp, msg);
		return floatobj;
	}

	
	//浮点数转换成整数(bigdecimal)
//	public BigDecimal MakeValueBig(BigDecimal value,  int dp) {
//		long H = (long)(0);
//		long L = (long)(0);
//		if (value.compareTo(BigDecimal.ZERO) == -1) {  //-1表示小于，0位等于，1为大于
//			H = 1;
//			//value = -value;
//			value=value.negate();//value = -value;
//		}
//
//		//long temp = (long)(0);
//		BigDecimal temp = new BigDecimal(0);
//		
//		if(dp >= 0 && dp <=9) {
//				L= VT_dp[dp];
//				//temp= (long)(value * Multiples[dp] + 0.5);
//				//temp= (long)(value * Multiples[dp]);
//				BigDecimal multiply=new BigDecimal(Multiples[dp]);
//				temp=value.multiply(multiply);
//		}
//		else
//				System.out.println("MakeValue: invalid decimal places " + dp);
//		long longdp= (long)(((H << 4) + L) << 16);  //注意java中移位优先级很低，所有H<<4要括起来。不过go语言中<<高于+。
//		//数据存在B[0，1，3，...,7]这7个字节中，其中B[2]存放数据格式和符号等特征值
//		//return ((temp >> 16) << 24) + ((H << 4 + L) << 16) + (temp & 0xFFFF);
//		//return ((temp >> 16) << 24) + longdp + (temp & 0xFFFF);
//		BigDecimal b1= temp.divide(new BigDecimal(Math.pow(2,16))).multiply(new BigDecimal(Math.pow(2,24)) );
//		BigDecimal b2=new BigDecimal(longdp);
//		BigDecimal b3=temp.
//		temp=temp
//		//return 
//	}	
	
	public int testPassByReference(int[] a,String c)
	{
		a[0] = 100;
		a[1]=200;
		c="hello";
		return 1000;
	}
	
	
	//test
	public static void main(String[] args)
	{
//		Yfloat yf=new Yfloat();
//		System.out.println("valnull="+yf.ValNull);
//		System.out.println("valnull_cast="+(yf.ValNull >>16));
//
//		//Integer a= new Integer(0);
//		int[] a={0,0};
//		String c="";
//		int val = yf.testPassByReference(a,c);
//		
//		System.out.println("a=" + a[0] + "," + a[1] + ",c="+c);
		//9万亿点01可以正确转换，即9000000000000.01
		//9876.11111111带8位小数可正常转换，如果是9位小数则出错。
		Yfloat yf=new Yfloat();
		//long val = yf.MakeValue(9000000000000.01, 2);
		//long val = yf.MakeValue(9876.11111111, 8);

		long val =213874L;
		//val = 367069404046L;
		val = 655364L;
		System.out.println("after cast, val=" + val);
		System.out.println("" +Long.toBinaryString(val));
		YfloatObj yfobj=yf.UnmakeValue(val);
		System.out.println("origin value=" + yfobj.getValue() + ",dp=" + yfobj.getdp() + ",msg=" + yfobj.geterror());
	 	//String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date((long) yfobj.getValue() ));
	 	//System.out.println(date);
		
	}

}
