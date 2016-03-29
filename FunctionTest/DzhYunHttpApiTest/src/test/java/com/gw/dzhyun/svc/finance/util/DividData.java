/**
 * @classnmae DividData.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.util;

import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;


/**
 * @author Litaojun
 * @date   2016年1月5日
 */
public class DividData {
	private String obj           ;
	private String chuQuanRiQi   ;
	private String guQuanDenJiRi ;
	private double songGuShu     ;
	private double zhuanZengGuShu;
	private double paiXiShu      ;
	private double peiGuShu      ;
	private double peiGuJia      ;
	private double paiXiShuShuiHou;
	public boolean equals(Object a)
	{

		boolean sign = false;
		 if (null==a) 
		 {
		       return false;
		 }
		 if (a instanceof DividData) 
		 {
			// System.out.println("fffffffffffffffffffff");
		    	DividData o= (DividData)a;
		    	sign = this.obj.equals(o.getObj()) && 
		    			this.chuQuanRiQi.equals(o.getChuquanriqi()) && 
		    			this.guQuanDenJiRi.equals(o.getGuquandenjiri()) && 
		    			this.songGuShu==o.getSonggushu() && 
		    			this.zhuanZengGuShu==o.getZhuanzenggushu() && 
		    			this.paiXiShu==o.getPaixishu() && 
		    			this.peiGuShu==o.getPeigushu() && 
		    			this.peiGuJia==o.getPeigujia() &&
		    			this.paiXiShuShuiHou == o.getPaiXiShuShuiHou();
		 }
		return sign;
	}
	public void parseFromDzhsvcfinanceDividData(Dzhsvcfinance.DividData o)
	{
		this.setObj(o.getObj());
		this.setChuquanriqi(o.getChuQuanRiQi());
		this.setGuquandenjiri(o.getGuQuanDenJiRi());
		this.setSonggushu(o.getSongGuShu());
		this.setZhuanzenggushu(o.getZhuanZengGuShu());
		this.setPaixishu(o.getPaiXiShu());
		this.setPeigushu(o.getPeiGuShu());
		this.setPeigujia(o.getPeiGuJia());
		this.setPaiXiShuShuiHou(o.getPaiXiShuShuiHou());
	}
	public void print()
	{
		String retstr= this.obj           +
				this.chuQuanRiQi   +
				this.guQuanDenJiRi +
				this.songGuShu     +
				this.zhuanZengGuShu+
				this.paiXiShu      +
				this.peiGuShu      +
				this.peiGuJia      +
				this.paiXiShuShuiHou;
		System.out.println(retstr);
	}
	public double getPaiXiShuShuiHou()
	{
		return this.paiXiShuShuiHou;
	}
	public void setPaiXiShuShuiHou(double paiXiShuShuiHou)
	{
		this.paiXiShuShuiHou = paiXiShuShuiHou;
	}
	public String getObj()
	{
		return this.obj;
	}
	public String getChuquanriqi()
	{
		return this.chuQuanRiQi;
	}
	public String getGuquandenjiri()
	{
		return this.guQuanDenJiRi;
	}
	public double getSonggushu()
	{
		return this.songGuShu;
	}
	public double getZhuanzenggushu()
	{
		return this.zhuanZengGuShu;
	}
	public double getPaixishu()
	{
		return this.paiXiShu;
	}
	public double getPeigushu()
	{
		return this.peiGuShu;
	}
	public double getPeigujia()
	{
		return this.peiGuJia;
	}
	public void setObj(String a)
	{
		this.obj = a;
	}
	public void setChuquanriqi(String a)
	{
		this.chuQuanRiQi = a;
	}
	public void setGuquandenjiri(String a)
	{
		this.guQuanDenJiRi =a ;
	}
	public void setSonggushu(double a)
	{
		this.songGuShu = a;
		
	}
	public void setZhuanzenggushu(double a)
	{
		this.zhuanZengGuShu = a;
	}
	public void setPaixishu(double a)
	{
		this.paiXiShu =a ;
	}
	public void setPeigushu(double a)
	{
		this.peiGuJia = a;
	}
	public void setPeigujia(double a)
	{
		this.peiGuJia = a;
	}

}
