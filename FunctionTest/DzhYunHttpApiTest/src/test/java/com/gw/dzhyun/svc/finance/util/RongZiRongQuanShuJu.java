/**
 * @classnmae RongZiRongQuanShuJu.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.util;

import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;


/**
 * @author Litaojun
 * @date   2016年1月5日
 */
public class RongZiRongQuanShuJu {
	private String obj                   ;    
	private String jiaoyiriqi            ;    
	private double rongziyue             ;    
	private double rongzimairue          ;    
	private double rongzichanghuane      ;    
	private double rongquanyuliang       ;   
	private double rongquanyue           ;    
	private double rongquanmaichuliang   ;    
	private double rongquanchanghuanliang;   
	private double rongzirongquanyue     ;    
	private double rongquanmaichue       ;    
	private double rongquanchanghuane    ;   
	private double rongzirongquanliang   ;  
	
	public boolean equals(Object a)
	{
		boolean sign = false;
		
		    if (a instanceof RongZiRongQuanShuJu) {
		    	RongZiRongQuanShuJu o= (RongZiRongQuanShuJu)a;
		    	
				//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				 sign=this.obj.equals(o.getObj())  && 
				this.jiaoyiriqi.equals(o.getJiaoYiRiQi()) && 
				this.rongziyue==o.getRongZiYuE()  && 
				this.rongzimairue==o.getRongZiMaiRuE()  && 
				this.rongzichanghuane==o.getRongZiChangHuanE()  && 
				this.rongquanyuliang==o.getRongQuanYuLiang()  && 
				this.rongquanyue==o.getRongQuanYuE()  && 
				this.rongquanmaichuliang==o.getRongQuanMaiChuLiang()  && 
				this.rongquanchanghuanliang==o.getRongQuanChangHuanLiang()  && 
				this.rongzirongquanyue==o.getRongZiRongQuanYuE()  && 
				this.rongquanmaichue==o.getRongQuanMaiChuE()  && 
				this.rongquanchanghuane==o.getRongQuanChangHuanE()  && 
				this.rongzirongquanliang==o.getRongZiRongQuanLiang()  ;
		//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

		    }

		return sign;
	}
	
	public void parseFromDzhsvcfinanceRongZiRongQuanShuJu(Dzhsvcfinance.RongZiRongQuanShuJu o)
	{
		this.setObj(o.getObj());
		this.setJiaoYiRiQi(o.getJiaoYiRiQi());
		this.setRongZiYuE(o.getRongZiYuE());
		this.setRongZiMaiRuE(o.getRongZiMaiRuE());
		this.setRongZiChangHuanE(o.getRongZiChangHuanE());
		this.setRongQuanYuLiang(o.getRongQuanYuLiang());
		this.setRongQuanYuE(o.getRongQuanYuE());
		this.setRongQuanMaiChuLiang(o.getRongQuanMaiChuLiang());
		this.setRongQuanChangHuanLiang(o.getRongQuanChangHuanLiang());
		this.setRongZiRongQuanYuE(o.getRongZiRongQuanYuE());
		this.setRongQuanMaiChuE(o.getRongQuanMaiChuE());
		this.setRongQuanChangHuanE(o.getRongQuanChangHuanE());
		this.setRongZiRongQuanLiang(o.getRongZiRongQuanLiang());
	}
	public void print()
	{
		String retstr = this.obj                   +  
				this.jiaoyiriqi            +  
				this.rongziyue             +  
				this.rongzimairue          +  
				this.rongzichanghuane      +  
				this.rongquanyuliang       +  
				this.rongquanyue           +  
				this.rongquanmaichuliang   +  
				this.rongquanchanghuanliang+  
				this.rongzirongquanyue     +  
				this.rongquanmaichue       +  
				this.rongquanchanghuane    +  
				this.rongzirongquanliang   ;  
		System.out.println(retstr);
	}
	public String getObj()
	{
		return this.obj;
	}
	public String getJiaoYiRiQi()
	{
		return this.jiaoyiriqi;
	}
	public double getRongZiYuE()
	{
		return this.rongziyue;
	}
	public double getRongZiMaiRuE()
	{
		return this.rongzimairue;
	}
	public double getRongZiChangHuanE()
	{
		return this.rongzichanghuane;
	}
	public double getRongQuanYuLiang()
	{
		return this.rongquanyuliang;
	}
	public double getRongQuanYuE()
	{
		return this.rongquanyue;
	}
	public double getRongQuanMaiChuLiang()
	{
		return this.rongquanmaichuliang;
		
	}
	public double getRongQuanChangHuanLiang()
	{
		return this.rongquanchanghuanliang;
	}
	public double getRongZiRongQuanYuE()
	{
		return this.rongzirongquanyue;
	}
	public double getRongQuanMaiChuE()
	{
		return this.rongquanmaichue;
		
	}
	public double getRongQuanChangHuanE()
	{
		return this.rongquanchanghuane;
	}
	public double getRongZiRongQuanLiang()
	{
		return this.rongzirongquanliang;
	}
	public void setObj(String                    a)    
	{
		this.obj = a;
	}
	public void setJiaoYiRiQi(String             a)
	{
		this.jiaoyiriqi = a;
	}
	public void setRongZiYuE(double              a)
	{
		this.rongziyue = a;
	}
	public void setRongZiMaiRuE(double           a)
	{
		this.rongzimairue = a;
	}
	public void setRongZiChangHuanE(double       a)
	{
	   this.rongzichanghuane = a;	
	}
	public void setRongQuanYuLiang(double        a)
	{
		this.rongquanyuliang =a ;
	}
	public void setRongQuanYuE(double            a)
	{
		this.rongquanyue = a;
	}
	public void setRongQuanMaiChuLiang(double    a)
	{
		this.rongquanmaichuliang = a;
	}
	public void setRongQuanChangHuanLiang(double a)
	{
		this.rongquanchanghuanliang  = a;
	}
	public void setRongZiRongQuanYuE(double      a)
	{
		this.rongzirongquanyue = a;
	}
	public void setRongQuanMaiChuE(double        a)
	{
		this.rongquanmaichue =a ;
	}
	public void setRongQuanChangHuanE(double     a)
	{
		this.rongquanchanghuane =a ;
	}
	public void setRongZiRongQuanLiang(double    a)
	{
		this.rongzirongquanliang =a;
	}
}
