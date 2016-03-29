/**
 * @classnmae BanKuaiChengFenGuInfo.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.singleProperty;

import java.util.ArrayList;

/**
 * @author Litaojun
 * @date   2015年12月2日
 */
/* 板块数据成份股数据 
message BanKuaiChengFenGu{
	required int64 BanKuaiId = 1;        	//板块ID
	repeated string ChengFenGuObj = 2;		//板块成份股
}*/
public class BanKuaiChengFenGuInfo
{
	private long BanKuaiId;
	private ArrayList<String> ChengFenGuObj = new ArrayList<String>();
	public ArrayList<String> getChengFenGuObjList()
	{
		return this.ChengFenGuObj;
	}
	public void parseFromBanKuaiChengFenGu(CldBanKuaiShu.BanKuaiChengFenGu bkcfg)
	{
		this.BanKuaiId = bkcfg.getBanKuaiId();
	    for(int i=0;i<bkcfg.getChengFenGuObjCount();i++)
	    {
	    	ChengFenGuObj.add(bkcfg.getChengFenGuObj(i));
	    }
	}
	public void printToString()
	{
		System.out.println("BanKuaiId="+this.BanKuaiId);
		for(String objstr:this.ChengFenGuObj)
		{
			System.out.println("Obj="+objstr);
		}
	}

}
