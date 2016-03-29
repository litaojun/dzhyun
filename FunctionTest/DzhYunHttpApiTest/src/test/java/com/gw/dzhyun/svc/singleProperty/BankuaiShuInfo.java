/**
 * @classnmae BankuaiShuInfo.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.singleProperty;

/**
 * @author Litaojun
 * @date   2015年11月30日
 */
/* 板块数据
message BanKuaiShuXing {
	required int64 Id = 1;							//板块ID
	required int64 SuoShuGenBanKuai = 2;			//根板块
	required int64 SuoShuFuBanKuai = 3;				//父板块
	required int64 BaoHanZiBanKuaiGeShu = 4; 		//子板块个数
	required int64 BanKuaiJiBie = 5;				//板块层级号
	required string BanKuaiMingCheng = 6;			//板块名称
	required string QuanLuJingIdZhi	= 7;			//板块ID全路径
	required string QuanLuJingMingChengZhi = 8;		//板块名称全路径			
} */

/* 板块数据成份股数据 
message BanKuaiChengFenGu{
	required int64 BanKuaiId = 1;        	//板块ID
	repeated string ChengFenGuObj = 2;		//板块成份股
}*/
public class BankuaiShuInfo {
	private long id;							//板块ID
	private long SuoShuGenBanKuai;			//根板块
	private long SuoShuFuBanKuai;				//父板块
	private long BaoHanZiBanKuaiGeShu; 		//子板块个数
	private long BanKuaiJiBie;				//板块层级号
	private String BanKuaiMingCheng;			//板块名称
	private String QuanLuJingIdZhi;			//板块ID全路径
	private String QuanLuJingMingChengZhi;		//板块名称全路径		
	public void parseFromRedisBks(CldBanKuaiShu.BanKuaiShuXing bkcfids)
	{
		System.out.println("BankuaiShuInfo->parseFromRedisBks-bkcfids.getBanKuaiMingCheng()"+bkcfids.getBanKuaiMingCheng());
		System.out.println("BankuaiShuInfo->parseFromRedisBks-bkcfids.getBanKuaiJiBie()"+bkcfids.getBanKuaiJiBie());
		System.out.println("BankuaiShuInfo->parseFromRedisBks-bkcfids.getBaoHanZiBanKuaiGeShu()"+bkcfids.getBaoHanZiBanKuaiGeShu());
		 this.BanKuaiMingCheng = bkcfids.getBanKuaiMingCheng();
		 this.BanKuaiJiBie = bkcfids.getBanKuaiJiBie();
		 this.BaoHanZiBanKuaiGeShu = bkcfids.getBaoHanZiBanKuaiGeShu();
		 this.SuoShuGenBanKuai = bkcfids.getSuoShuGenBanKuai();
		 this.id = bkcfids.getId();
		 this.QuanLuJingMingChengZhi = bkcfids.getQuanLuJingMingChengZhi();
		 this.QuanLuJingIdZhi = bkcfids.getQuanLuJingIdZhi();
		 this.SuoShuFuBanKuai = bkcfids.getSuoShuFuBanKuai();

	}
	public void printToString()
	{
		String resstr = id+"~"+SuoShuGenBanKuai+"~"+SuoShuFuBanKuai+"~"+BaoHanZiBanKuaiGeShu+"~"+BanKuaiJiBie+"~"+BanKuaiMingCheng+"~"+QuanLuJingIdZhi+"~"+QuanLuJingMingChengZhi;
		String resstrss = "id="+id+"~"+"SuoShuGenBanKuai="+SuoShuGenBanKuai+"~"+"SuoShuFuBanKuai="+SuoShuFuBanKuai+"~"+"BaoHanZiBanKuaiGeShu="+BaoHanZiBanKuaiGeShu+"~"+"BanKuaiJiBie="+BanKuaiJiBie+"~"+"BanKuaiMingCheng="+BanKuaiMingCheng+"~"+"QuanLuJingIdZhi="+QuanLuJingIdZhi+"~"+"QuanLuJingMingChengZhi="+QuanLuJingMingChengZhi;
		System.out.println(resstr);
		System.out.println(resstrss);
		//return resstr;
	}

}
