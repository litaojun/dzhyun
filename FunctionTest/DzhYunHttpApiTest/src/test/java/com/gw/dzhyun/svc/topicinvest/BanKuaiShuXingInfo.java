package com.gw.dzhyun.svc.topicinvest;

public class BanKuaiShuXingInfo {
	private long id;
	private long suoShuGenBanKuai;
	private long suoShuFuBanKuai;
	private long baoHanZiBanKuaiGeShu;
	private long banKuaiJiBie;
	public void setId(long Id) 
	{
		this.id = id;
	}
	public void setSuoShuGenBanKuai(long SuoShuGenBanKuai)   
	{
		this.suoShuGenBanKuai = SuoShuGenBanKuai;
	}
	public void setSuoShuFuBanKuai(long SuoShuFuBanKuai)     
	{
		this.suoShuFuBanKuai = SuoShuFuBanKuai;
	}
	public void setBaoHanZiBanKuaiGeShu(long BaoHanZiBanKuaiGeShu)
	{
		this.baoHanZiBanKuaiGeShu = BaoHanZiBanKuaiGeShu;
	}
	public void setBanKuaiJiBie(long BanKuaiJiBie) 
	{
		this.banKuaiJiBie = BanKuaiJiBie;
	}
	
	public long getId()    
	{
		return this.id;
	}
    public long getSuoShuGenBanKuai()     
    {
    	return this.suoShuGenBanKuai;
    }
    public long getSuoShuFuBanKuai() 
    {
    	return this.suoShuFuBanKuai;
    }
    public long getBaoHanZiBanKuaiGeShu() 
    {
    	return this.baoHanZiBanKuaiGeShu;
    }
    public long getBanKuaiJiBie()
    {
    	return this.banKuaiJiBie;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
