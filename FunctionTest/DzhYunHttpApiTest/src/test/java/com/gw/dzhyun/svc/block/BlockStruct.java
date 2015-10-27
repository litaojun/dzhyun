package com.gw.dzhyun.svc.block;

public class BlockStruct {
	private int Id                          ;
	private int SuoShuGenBanKuai            ;
	private int SuoShuFuBanKuai             ;
	private int BaoHanZiBanKuaiGeShu        ;
	private int BanKuaiJiBie                ;
	private String BanKuaiMingCheng         ;
	private String QuanLuJingIdZhi          ;
	private String QuanLuJingMingChengZhi   ;
	public int sign = 0;
	private int BanKuaiId                   ;
	private String[] ChengFenGuObj          ;
	
	public int getId()
	{
		return this.Id;
	}
	public int getSuoShuGenBanKuai()
	{
		return this.SuoShuGenBanKuai;
	}
	public int getSuoShuFuBanKuai()
	{
		return this.SuoShuFuBanKuai;
	}
	public int getBaoHanZiBanKuaiGeShu()
	{
		return this.BaoHanZiBanKuaiGeShu;
	}
	public int getBanKuaiJiBie()
	{
		return this.BanKuaiJiBie;
	}
	public String getBanKuaiMingCheng()
	{
		return this.BanKuaiMingCheng;
	}
	public String getQuanLuJingIdZhi()
	{
		return this.QuanLuJingIdZhi;
	}
	public String getQuanLuJingMingChengZhi()
	{
		return this.QuanLuJingIdZhi;
	}

	public int getBanKuaiId()
	{
		return this.BanKuaiId;
	}
	public String[] getChengFenGuObj()
	{
		return this.ChengFenGuObj;
	}
	public void setId(int Id)
	{
		this.Id = Id;
	}
	public void setSuoShuGenBanKuai(int  SuoShuGenBanKuai)
	{
		this.SuoShuGenBanKuai = SuoShuGenBanKuai;
	}
	public void setSuoShuFuBanKuai(int   SuoShuFuBanKuai)
	{
		this.SuoShuFuBanKuai =SuoShuFuBanKuai;
	}
	public void setBaoHanZiBanKuaiGeShu(int   BaoHanZiBanKuaiGeShu)
	{
		this.BaoHanZiBanKuaiGeShu = BaoHanZiBanKuaiGeShu;
	}
	public void setBanKuaiJiBie(int   BanKuaiJiBie)
	{
		this.BanKuaiJiBie = BanKuaiJiBie;
	}
	public void setBanKuaiMingCheng(String   BanKuaiMingCheng)
	{
		this.BanKuaiMingCheng = BanKuaiMingCheng;
	}
	public void setQuanLuJingIdZhi(String   QuanLuJingIdZhi)
	{
		this.QuanLuJingIdZhi = QuanLuJingIdZhi;
	}
	public void setQuanLuJingMingChengZhi(String   QuanLuJingMingChengZhi)
	{
		this.QuanLuJingMingChengZhi = QuanLuJingMingChengZhi;
	}
	                      
	public void setBanKuaiId(int   BanKuaiId)
	{
		this.BanKuaiId = BanKuaiId;
	}
	public void setChengFenGuObj(String[]   ChengFenGuObj)
	{
		this.ChengFenGuObj = ChengFenGuObj;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
