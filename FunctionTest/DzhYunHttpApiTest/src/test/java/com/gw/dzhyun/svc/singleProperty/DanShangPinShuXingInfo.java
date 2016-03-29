/**
 * @classnmae DanShangPinShuXingInfo.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.singleProperty;

/**
 * @author Litaojun
 * @date   2015年12月1日
 */
/*
 * required string obj = 1;					// 单商品OBJ CO
	optional string shangShiShiJian = 2;		// 上市时间 C1
	optional string zhongWenJianCheng = 3;		// 中文简称 C4 
	optional string yingWenQuanCheng = 4;		// 英文简称 C5 
	optional int32 xiaoShuDianWeiShu = 5;		// 小数据位数 C8
	optional int32 jiaoYiShiJianLeiXin = 6;		// 交易时间类型 C9
	optional string jiaoYiBiZhong = 7;			// 交易币种 C10
	optional string tuiShiShiJian = 8;			// 退市时间 C14
	optional int32 shangShiZhuangTai = 9;		// 上市状态 C30 (0:上市,1:未上市,2:暂停上市)
	optional string zhengQuanLeiBie = 10;		// 证券类别 C37 
	optional int32 tingPai = 11;                // 停牌 dzh_stock.tEQ9112.C5
 */
public class DanShangPinShuXingInfo {

	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	
	private String obj ;
	private String shangShiShiJian ;
	private String zhongWenJianCheng;
	private String yingWenQuanCheng;
	private int xiaoShuDianWeiShu;
	private int jiaoYiShiJianLeiXin;
	private String jiaoYiBiZhong ;
	private String tuiShiShiJian;
	private int shangShiZhuangTai;
	private String zhengQuanLeiBie;
	private int tingPai;
	public void parseFromRedisBks(ShangPinShuXing.DanShangPinShuXing danspid)
	{
		
		 obj = danspid.getObj();
		 shangShiShiJian = danspid.getShangShiShiJian();
		 zhongWenJianCheng = danspid.getZhongWenJianCheng();
		 yingWenQuanCheng  = danspid.getYingWenQuanCheng();
		 xiaoShuDianWeiShu  = danspid.getXiaoShuDianWeiShu();
		 jiaoYiShiJianLeiXin = danspid.getJiaoYiShiJianLeiXin();
		 jiaoYiBiZhong = danspid.getJiaoYiBiZhong();
		 tuiShiShiJian = danspid.getTuiShiShiJian();
		 shangShiZhuangTai = danspid.getShangShiZhuangTai();
		 zhengQuanLeiBie = danspid.getZhengQuanLeiBie();
		 this.tingPai = danspid.getTingPai();
	}
	public boolean equals(DanShangPinShuXingInfo tmp)
	{
		boolean sign = true;
		if(!this.obj.equals(tmp.getObj()))
		{
			sign = false;
		}
		if(this.shangShiShiJian!=null && !this.shangShiShiJian.equals(tmp.getShangshishijian()))
		{
			sign = false;
		}
		if(!this.zhongWenJianCheng.equals(tmp.getZhongwenjiancheng()))
		{
			sign = false;
		}
		if(this.yingWenQuanCheng!=null && !this.yingWenQuanCheng.equals(tmp.getYingwenquancheng()))
		{
			sign = false;
		}
		if(!(this.xiaoShuDianWeiShu==tmp.getXiaoshudianweishu()))
		{
			sign = false;
		}
		if(!(this.jiaoYiShiJianLeiXin==tmp.getJiaoyishijianleixin()))
		{
			sign = false;
		}
		if(this.tuiShiShiJian!=null && !this.tuiShiShiJian.equals(tmp.getTuishishijian()))
		{
			sign = false;
		}
		if(!(this.shangShiZhuangTai==tmp.getShangshizhuangtai()))
		{
			sign = false;
		}
		if(this.zhengQuanLeiBie!=null && !this.zhengQuanLeiBie.equals(tmp.getZhengquanleibie()))
		{
			sign = false;
		}
		if(!(this.tingPai==tmp.getTingpai()))
		{
			sign = false;
		}
			
		return sign;
	}
	public String getObj()
	{
		return this.obj;
	}
          
	public String getShangshishijian()   
	{
		return this.shangShiShiJian;
	}
	public String getZhongwenjiancheng()  
	{
		return this.zhongWenJianCheng;
	}
	public String getYingwenquancheng()   
	{
		return this.yingWenQuanCheng;
	}
	public int    getXiaoshudianweishu()  
	{
		return this.xiaoShuDianWeiShu;
	}
	public int    getJiaoyishijianleixin()
	{
		return this.jiaoYiShiJianLeiXin;
	}
	public String getJiaoyibizhong()      
	{
		return this.jiaoYiBiZhong;
	}
	public String getTuishishijian()      
	{
		return this.tuiShiShiJian;
	}
	public int    getShangshizhuangtai()  
	{
		return this.shangShiZhuangTai;
	}
	public String getZhengquanleibie()    
	{
		return this.zhengQuanLeiBie;
	}
	public int    getTingpai()  
	{
		return this.tingPai;
	}


	public void setObj(String tmp)   
	{
		this.obj = tmp;
	}
	public void setShangshishijian(String tmp)  
	{
		this.shangShiShiJian = tmp;
	}
	public void setZhongwenjiancheng(String tmp)
	{
		this.zhongWenJianCheng = tmp;
	}
	public void setYingwenquancheng(String tmp) 
	{
		this.yingWenQuanCheng = tmp;
	}
	public void setXiaoshudianweishu(int tmp)   
	{
		this.xiaoShuDianWeiShu = tmp;
	}
	public void setJiaoyishijianleixin(int tmp) 
	{
		this.jiaoYiShiJianLeiXin = tmp;
	}
	public void setJiaoyibizhong(String tmp) 
	{
		this.jiaoYiBiZhong = tmp;
	}
	public void setTuishishijian(String tmp)  
	{
		this.tuiShiShiJian = tmp;
	}
	public void setShangshizhuangtai(int tmp)   
	{
		this.shangShiZhuangTai = tmp;
	}
	public void setZhengquanleibie(String tmp)  
	{
		this.zhengQuanLeiBie = tmp;
	}
	public void setTingpai(int tmp)
	{
		this.tingPai = tmp;
	}
	public void printDanShangPinShuXingInfoToString()
	{
		System.out.println(obj+"-"+shangShiShiJian+"-"+zhongWenJianCheng+"-"+yingWenQuanCheng+"-"+xiaoShuDianWeiShu+"-"+jiaoYiShiJianLeiXin+"-"+jiaoYiBiZhong+"-"+tuiShiShiJian+"-"+shangShiZhuangTai+"-"+zhengQuanLeiBie+"-"+tingPai);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
