/**
 * @classnmae IndicatorInfo.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.indicatorcalc;

/**
 * @author Litaojun
 * @date   2015年12月31日
 */
public class IndicatorInfo 
{
	long timemap;
	float shoupaijia;
	public IndicatorInfo(long tm,float spj)
	{
		this.timemap = tm;
		this.shoupaijia = spj;
	}
	public long getTimemap()
	{
		return this.timemap;
	}
	public float getShoupaijia()
	{
		return  this.shoupaijia;
	}

}
