package com.gw.dzhyun.svc.topicinvest;

import java.util.ArrayList;

public class BanKuaiChengFenGuInfo {

	private long banKuaiId;
	//private ArrayList<String> chengFenGuObj;
	private String chengFenGuObj;
	public void setBanKuaiId(long l)
	{
		this.banKuaiId = l;
	}
//	public void setChengFenGuObj(ArrayList<String> chengFenGuObj)
//	{
//		this.chengFenGuObj=chengFenGuObj;
//	}
	public void setChengFenGuObj(String chengFenGuObj)
	{
		this.chengFenGuObj=chengFenGuObj;
	}
	public long getBanKuaiId()
	{
		return this.banKuaiId;
	}
//	public ArrayList<String> getChengFenGuObj()
//	{
//		return this.chengFenGuObj;
//	}
	public String getChengFenGuObj()
	{
		return this.chengFenGuObj;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
