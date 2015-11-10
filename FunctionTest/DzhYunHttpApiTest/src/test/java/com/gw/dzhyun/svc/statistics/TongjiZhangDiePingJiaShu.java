package com.gw.dzhyun.svc.statistics;

/*
 * Statistics类中
 *  message TongjiZhangDiePingJiaShu
{
  required int64 ShiJian;
  required int64 JiaShu;
}
message TongjiChengJiaoE
{
  required int64 ShiJian;
  required int64 ChengJiaoE;
}
两种结构的转换类（），输出显示使用，
redis中的数据读出后，数据写入该类，供其他类使用。
 */
public class TongjiZhangDiePingJiaShu {

	private long shijian;
	private long jiashu;
	public void setShijian(long shij)
	{
		this.shijian = shij;
	}
	public void setJiashu(long jiashu)
	{
		this.jiashu = jiashu;
	}
	public long getShijian()
	{
		return this.shijian;
	}
	public long getJiashu()
	{
		return this.jiashu;
	}
	public void parseFromTongjiChengJiaoE(Statistics.TongjiChengJiaoE t)
	{
		this.shijian = t.getShiJian();
		this.jiashu = t.getChengJiaoE();
	}
	public void parseFromTongjiZhangDiePingJiaShu(Statistics.TongjiZhangDiePingJiaShu t)
	{
		this.shijian = t.getShiJian();
		this.jiashu = t.getJiaShu();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
