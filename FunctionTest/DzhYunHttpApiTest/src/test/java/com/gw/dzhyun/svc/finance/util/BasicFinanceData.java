/**
 * @classnmae BasicFinanceData.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.util;

import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;


/**
 * @author Litaojun
 * @date   2016年1月5日
 */
public class BasicFinanceData {

	private String obj               ;  
	private String repdate           ;  
	private long   floatdate           ;
	private double earnps            ;  
	private double assetps           ;  
	private double rona              ;  
	private double cashps            ;  
	private double accufundps        ;  
	private double unapproprofitps   ;  
	private double rateonequity      ;  
	private double profitinc         ;  
	private double incomeinc         ;  
	private double grossprofit       ;  
	private double adjassetps        ;  
	private double asset             ;  
	private double floatasset        ;  
	private double fixedasset        ;  
	private double intasset          ;  
	private double floatdebet        ;  
	private double longdebet         ;  
	private double alldebet          ;  
	private double hoderequity       ;  
	private double capitalfund       ;  
	private double cashfloat         ;  
	private double investfloat       ;  
	private double raisefloat        ;  
	private double cashinc           ;  
	private double mainincome        ;  
	private double mainprofit        ;  
	private double tradeprofit       ;  
	private double investprofit      ;  
	private double otherbalance      ;  
	private double allprofit         ;  
	private double netprofit         ;  
	private double unapproprofit     ;  
	private double totalshare        ;  
	private double norestrictshare   ;  
	private double ashare            ;  
	private double bshare            ;  
	private double foreignshare      ;  
	private double otherfloatshare   ;  
	private double restrictshare     ;  
	private double nationshare       ;  
	private double natcorpshare      ;  
	private double domestcorpshare   ;  
	private double domestindshare    ;  
	private double otherlaunchshare  ;  
	private double raisecorpshare    ;  
	private double foreigncorpshare  ;  
	private double foreingIndshare   ;  
	private double preforothershare  ;
	public void parseFromDzhsvcfinanceBasicFinanceData(Dzhsvcfinance.BasicFinanceData o)
	{
		this.setObj(o.getObj());
		this.setRepdate(o.getRepdate());
		this.setFloatdate(o.getFloatdate());
		this.setEarnps(o.getEarnps());
		this.setAssetps(o.getAssetps());
		this.setRona(o.getRona());
		this.setCashps(o.getCashps());
		this.setAccufundps(o.getAccufundps());
		this.setUnapproprofitps(o.getUnapproprofitps());
		this.setRateonequity(o.getRateonequity());
		this.setProfitinc(o.getProfitinc());
		this.setIncomeinc(o.getIncomeinc());
		this.setGrossprofit(o.getGrossprofit());
		this.setAdjassetps(o.getAdjassetps());
		this.setAsset(o.getAsset());
		this.setFloatasset(o.getFloatasset());
		this.setFixedasset(o.getFixedasset());
		this.setIntasset(o.getIntasset());
		this.setFloatdebet(o.getFloatdebet());
		this.setLongdebet(o.getLongdebet());
		this.setAlldebet(o.getAlldebet());
		this.setHoderequity(o.getHoderequity());
		this.setCapitalfund(o.getCapitalfund());
		this.setCashfloat(o.getCashfloat());
		this.setInvestfloat(o.getInvestfloat());
		this.setRaisefloat(o.getRaisefloat());
		this.setCashinc(o.getCashinc());
		this.setMainincome(o.getMainincome());
		this.setMainprofit(o.getMainprofit());
		this.setTradeprofit(o.getTradeprofit());
		this.setInvestprofit(o.getInvestprofit());
		this.setOtherbalance(o.getOtherbalance());
		this.setAllprofit(o.getAllprofit());
		this.setNetprofit(o.getNetprofit());
		this.setUnapproprofit(o.getUnapproprofit());
		this.setTotalshare(o.getTotalshare());
		this.setNorestrictshare(o.getNorestrictshare());
		this.setAshare(o.getAshare());
		this.setBshare(o.getBshare());
		this.setForeignshare(o.getForeignshare());
		this.setOtherfloatshare(o.getOtherfloatshare());
		this.setRestrictshare(o.getRestrictshare());
		this.setNationshare(o.getNationshare());
		this.setNatcorpshare(o.getNatcorpshare());
		this.setDomestcorpshare(o.getDomestcorpshare());
		this.setDomestindshare(o.getDomestindshare());
		this.setOtherlaunchshare(o.getOtherlaunchshare());
		this.setRaisecorpshare(o.getRaisecorpshare());
		this.setForeigncorpshare(o.getForeigncorpshare());
		this.setForeingindshare(o.getForeingIndshare());
		this.setPreforothershare(o.getPreforothershare());
	}
	public void print()
	{
		String prstr = this.obj              +
				this.repdate          +
				this.floatdate        +
				this.earnps           +
				this.assetps          +
				this.rona             +
				this.cashps           +
				this.accufundps       +
				this.unapproprofitps  +
				this.rateonequity     +
				this.profitinc        +
				this.incomeinc        +
				this.grossprofit      +
				this.adjassetps       +
				this.asset            +
				this.floatasset       +
				this.fixedasset       +
				this.intasset         +
				this.floatdebet       +
				this.longdebet        +
				this.alldebet         +
				this.hoderequity      +
				this.capitalfund      +
				this.cashfloat        +
				this.investfloat      +
				this.raisefloat       +
				this.cashinc          +
				this.mainincome       +
				this.mainprofit       +
				this.tradeprofit      +
				this.investprofit     +
				this.otherbalance     +
				this.allprofit        +
				this.netprofit        +
				this.unapproprofit    +
				this.totalshare       +
				this.norestrictshare  +
				this.ashare           +
				this.bshare           +
				this.foreignshare     +
				this.otherfloatshare  +
				this.restrictshare    +
				this.nationshare      +
				this.natcorpshare     +
				this.domestcorpshare  +
				this.domestindshare   +
				this.otherlaunchshare +
				this.raisecorpshare   +
				this.foreigncorpshare +
				this.foreingIndshare  +
				this.preforothershare ;
		System.out.println(prstr);

	}
	public boolean equals(Object a)
	{
		if(a==null)
			return false;
		boolean retsign = false;
		if(a instanceof BasicFinanceData)
		{
			BasicFinanceData o=(BasicFinanceData)a;
		 retsign = (this.obj.equals(o.getObj()))&&
				(this.repdate.equals(o.getRepdate()))&&
				(this.floatdate==o.getFloatdate())&&
				(this.earnps==o.getEarnps())&&
				(this.assetps==o.getAssetps())&&
				(this.rona==o.getRona())&&
				(this.cashps==o.getCashps())&&
				(this.accufundps==o.getAccufundps())&&
				(this.unapproprofitps==o.getUnapproprofitps())&&
				(this.rateonequity==o.getRateonequity())&&
				(this.profitinc==o.getProfitinc())&&
				(this.incomeinc==o.getIncomeinc())&&
				(this.grossprofit==o.getGrossprofit())&&
				(this.adjassetps==o.getAdjassetps())&&
				(this.asset==o.getAsset())&&
				(this.floatasset==o.getFloatasset())&&
				(this.fixedasset==o.getFixedasset())&&
				(this.intasset==o.getIntasset())&&
				(this.floatdebet==o.getFloatdebet())&&
				(this.longdebet==o.getLongdebet())&&
				(this.alldebet==o.getAlldebet())&&
				(this.hoderequity==o.getHoderequity())&&
				(this.capitalfund==o.getCapitalfund())&&
				(this.cashfloat==o.getCashfloat())&&
				(this.investfloat==o.getInvestfloat())&&
				(this.raisefloat==o.getRaisefloat())&&
				(this.cashinc==o.getCashinc())&&
				(this.mainincome==o.getMainincome())&&
				(this.mainprofit==o.getMainprofit())&&
				(this.tradeprofit==o.getTradeprofit())&&
				(this.investprofit==o.getInvestprofit())&&
				(this.otherbalance==o.getOtherbalance())&&
				(this.allprofit==o.getAllprofit())&&
				(this.netprofit==o.getNetprofit())&&
				(this.unapproprofit==o.getUnapproprofit())&&
				(this.totalshare==o.getTotalshare())&&
				(this.norestrictshare==o.getNorestrictshare())&&
				(this.ashare==o.getAshare())&&
				(this.bshare==o.getBshare())&&
				(this.foreignshare==o.getForeignshare())&&
				(this.otherfloatshare==o.getOtherfloatshare())&&
				(this.restrictshare==o.getRestrictshare())&&
				(this.nationshare==o.getNationshare())&&
				(this.natcorpshare==o.getNatcorpshare())&&
				(this.domestcorpshare==o.getDomestcorpshare())&&
				(this.domestindshare==o.getDomestindshare())&&
				(this.otherlaunchshare==o.getOtherlaunchshare())&&
				(this.raisecorpshare==o.getRaisecorpshare())&&
				(this.foreigncorpshare==o.getForeigncorpshare())&&
				(this.foreingIndshare==o.getForeingindshare())&&
				(this.preforothershare==o.getPreforothershare());
		}
		return retsign;

	}
	public String getObj()  
	{
		return this.obj;
	}
	public String getRepdate() 
	{
		return this.repdate;
		
	}
	public long   getFloatdate()
	{
		return this.floatdate;
	}
	public double getEarnps()
	{
		return this.earnps;
	}
	public double getAssetps()
	{
		return this.assetps;
	}
	public double getRona()
	{
		return this.rona;
	}
	public double getCashps()
	{
		return this.cashps;
	}
	public double getAccufundps()
	{
		return this.accufundps;
	}
	public double getUnapproprofitps() 
	{
		return this.unapproprofitps;
		
	}
	public double getRateonequity()    
	{
		return this.rateonequity;
	}
	public double getProfitinc()     
	{
		return this.profitinc;
		
	}
	public double getIncomeinc()        
	{
		return this.incomeinc;
	}
	public double getGrossprofit()      
	{
		return this.grossprofit;
	}
	public double getAdjassetps()     
	{
		return this.adjassetps;
	}
	public double getAsset()            
	{
		return this.asset;
	}
	public double getFloatasset()     
	{
		return this.floatasset;
	}
	public double getFixedasset()       
	{
		return this.fixedasset;
		
	}
	public double getIntasset()        
	{
		return this.intasset;
	}
	public double getFloatdebet()     
	{
		return this.floatdebet;
		
	}
	public double getLongdebet()   
	{
		return this.longdebet;
	}
	public double getAlldebet()      
	{
		return this.alldebet;
	}
	public double getHoderequity()      
	{
		return this.hoderequity;
	}
	public double getCapitalfund()      
	{
		return this.capitalfund;
	}
	public double getCashfloat()        
	{
		return this.cashfloat;
	}
	public double getInvestfloat()    
	{
		return this.investfloat;
	}
	public double getRaisefloat()   
	{
		return this.raisefloat;
	}
	public double getCashinc()       
	{
		return this.cashinc;
	}
	public double getMainincome()   
	{
		return this.mainincome;
	}
	public double getMainprofit()     
	{
		return this.mainprofit;
	}
	public double getTradeprofit()      
	{
		return this.tradeprofit;
	}
	public double getInvestprofit()     
	{
		return this.investprofit;
	}
	public double getOtherbalance()    
	{
		return this.otherbalance;
	}
	public double getAllprofit()    
	{
		return this.allprofit;
	}
	public double getNetprofit()    
	{
		return this.netprofit;
	}
	public double getUnapproprofit()    
	{
		return this.unapproprofit;
	}
	public double getTotalshare()       
	{
		return this.totalshare;
	}
	public double getNorestrictshare () 
	{
		return this.norestrictshare;
	}
	public double getAshare()        
	{
		return this.ashare;
	}
	public double getBshare()        
	{
		return this.bshare;
	}
	public double getForeignshare()     
	{
		return this.foreigncorpshare;
	}
	public double getOtherfloatshare()  
	{
		return this.otherfloatshare;
	}
	public double getRestrictshare()    
	{
		return this.restrictshare;
	}
	public double getNationshare()     
	{
		return this.nationshare;
	}
	public double getNatcorpshare()     
	{
		return this.natcorpshare;
	}
	public double getDomestcorpshare()  
	{
		return this.domestcorpshare;
	}
	public double getDomestindshare()   
	{
		return this.domestindshare;
	}
	public double getOtherlaunchshare() 
	{
		return this.otherlaunchshare;
	}
	public double getRaisecorpshare()   
	{
		return this.raisecorpshare;
	}
	public double getForeigncorpshare() 
	{
		return this.foreigncorpshare;
	}
	public double getForeingindshare()  
	{
		return this.foreingIndshare;
	}
	public double getPreforothershare() 
	{
		return this.preforothershare;
	}
	public void setObj(String A)
	{                 
		this.obj             = A; 
	}

	public void setRepdate(String A)
	{           
		this.repdate         = A;
	}

	public void setFloatdate(Long A)
	{                 
		this.floatdate       = A;
	}

	public void setEarnps(double A)
	{                    
		this.earnps          = A;
	}    

	
	public void setAssetps(double A)
	{                 
		this.assetps         = A;
	}

	public void setRona(double A)
	{                      
		this.rona            = A;
	}

	public void setCashps(double A)
	{                     
		this.cashps          = A;
	}

	public void setAccufundps(double A)
	{                     
		this.accufundps      = A;
	}

	public void setUnapproprofitps(double A)
	{                        
		this.unapproprofitps = A;
	}

	public void setRateonequity(double A)
	{                          
		this.rateonequity    = A;
	}

	public void setProfitinc(double A)
	{                         
		this.profitinc       = A;
	}

	public void setIncomeinc(double A)
	{                   
		this.incomeinc       = A;
	}

	public void setGrossprofit(double A)
	{                      
		this.grossprofit     = A;
	}   

	public void setAdjassetps(double A)
	{                   
		this.adjassetps      = A;
	}

	public void setAsset(double A)
	{                
		this.asset           = A;
	}

	public void setFloatasset(double A)
	{                
		this.floatasset      = A;
	}

	public void setFixedasset(double A)
	{                
		this.fixedasset      = A;
	}   

	public void setIntasset(double A)
	{                  
		this.intasset        = A;
	}  

	public void setFloatdebet(double A)
	{                 
		this.floatdebet      = A;
	}

	public void setLongdebet(double A)
	{                  
		this.longdebet       = A;
	} 

	public void setAlldebet(double A)
	{                 
		this.alldebet        = A;
	}

	public void setHoderequity(double A)
	{                   
		this.hoderequity     = A;
	}
	public void setCapitalfund(double A)
	{                   
		this.capitalfund     = A;
	}
	public void setCashfloat(double A)
	{            
		this.cashfloat       = A;
	}
	public void setInvestfloat(double A)
	{             
		this.investfloat     = A;
	}
	public void setRaisefloat(double A)
	{                
		this.raisefloat      = A;
	}
	public void setCashinc(double A)
	{                 
		this.cashinc         = A;
	}
	public void setMainincome(double A)
	{                
		this.mainincome      = A;
	}
	public void setMainprofit(double A)
	{                   
		this.mainprofit      = A;
	}
	public void setTradeprofit(double A)
	{                   
		this.tradeprofit     = A;
	}
	public void setInvestprofit(double A)
	{                   
		this.investprofit    = A;
	}
	public void setOtherbalance(double A)
	{                   
		this.otherbalance    = A;
	}
	public void setAllprofit(double A)
	{                
		this.allprofit       = A;
	}
	public void setNetprofit(double A)
	{                 
		this.netprofit       = A;
	}
	public void setUnapproprofit(double A)
	{                         
		this.unapproprofit   = A;
	}
	public void setTotalshare(double A)
	{                     
		this.totalshare      = A;
	}
	public void setNorestrictshare(double A)
	{                     
		this.norestrictshare = A;
	}
	public void setAshare(double A)
	{                 
		this.ashare          = A;
	}
	public void setBshare(double A)
	{                
		this.bshare          = A;
	}
	public void setForeignshare(double A)
	{                 
		this.foreignshare    = A;
	}
	public void setOtherfloatshare(double A)
	{                     
		this.otherfloatshare = A;
	}
	public void setRestrictshare(double A)
	{                 
		this.restrictshare   = A;
	}
	public void setNationshare(double A)
	{                 
		this.nationshare     = A;
	}
	public void setNatcorpshare(double A)
	{                       
		this.natcorpshare    = A;
	}
	public void setDomestcorpshare(double A)
	{                  
		this.domestcorpshare = A;
	}
	public void setDomestindshare(double A)
	{                     
		this.domestindshare  = A;
	}
	public void setOtherlaunchshare(double A)
	{                       
		this.otherlaunchshare= A;
	}
	public void setRaisecorpshare(double A)
	{                    
		this.raisecorpshare  = A;
	}
	public void setForeigncorpshare(double A)
	{                         
		this.foreigncorpshare= A;
	}
	public void setForeingindshare(double A)
	{                          
		this.foreingIndshare = A;
	}
	public void setPreforothershare(double A)
	{                     
		this.preforothershare= A;
	}
}
