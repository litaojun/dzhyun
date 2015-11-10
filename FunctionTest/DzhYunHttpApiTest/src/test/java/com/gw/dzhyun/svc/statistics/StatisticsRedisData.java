package com.gw.dzhyun.svc.statistics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.svc.statistics.Statistics.TongjiChengJiaoE;
import com.gw.dzhyun.svc.topicinvest.Topicinvestblock;
/*
 * 根据市场获取redis中统计服务存储的数据
 */
public class StatisticsRedisData 
{
	private JedisOperator jed = new JedisOperator();
	/*
	 * 根据市场种类及对应前缀得到编码后的KEY
	 * 其中市场种类有B$，SH，SO，SZ
	 * 统计服务的编码前缀为610
	 */
	public byte[] getKeyBytes(String ktstr)
	{
		IoBuffer ioBuffer = IoBuffer.allocate(1024);   
		 ioBuffer.put(ktstr.getBytes());
        ioBuffer.flip();
        byte[] a = new byte[ioBuffer.limit()];
        ioBuffer.get(a);
        return a;
	}
	/*
	 * 根据市场获取其下面的A，B,基金，其他 的涨跌详情
	 * 目前支持的市场有B$，SH，SO，SZ
	 */
	public HashMap<String,Object> getBanKuaiShuXingFromRedis(String market) throws InvalidProtocolBufferException
	{
		HashMap<String,Object> rethm = new HashMap<String,Object>();
		System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes("610"+market);
		System.out.println("keybs="+keybs);
		Map<byte[],byte[]> data = this.jed.getHashByteArr(keybs);
		//byte[] statisData = data.get("ShangZhangJiaShuStockA");
		
		Set<byte[]> set = data.keySet();
		Iterator<byte[]> it = set.iterator();
		while(it.hasNext())
		{
			byte[] curb = it.next();
			String keyname = new String(curb);
			System.out.println("keyname="+keyname);
			byte[] curdata = data.get(curb);
			if(keyname.startsWith(""))
			{
				Statistics.TongjiChengJiaoE t = Statistics.TongjiChengJiaoE.parseFrom(curdata);
				rethm.put(keyname, t);
			}
			else
			{
				Statistics.TongjiZhangDiePingJiaShu t  = Statistics.TongjiZhangDiePingJiaShu.parseFrom(curdata);
				rethm.put(keyname, t);
			}
			
		}
		return rethm;
		
	}
	/*
	 * 根据市场和涨跌平类型获取对应数据
	 * 目前支持的市场有B$，SH，SO，SZ
	 * 目前支持的涨跌平类型有ShangZhangJiaShuStockA，XiaDieJiaShuStockA，PingPanJiaShuStockA，TingPaiJiaShuStockA，ShangZhangJiaShuStockB，XiaDieJiaShuStockB，PingPanJiaShuStockB，TingPaiJiaShuStockB，ShangZhangJiaShuJiJin，XiaDieJiaShuJiJin，PingPanJiaShuJiJin
	 */
	public TongjiZhangDiePingJiaShu getZDPdataByMarketOrType(String market,String ZDPtype) throws InvalidProtocolBufferException
	{
		TongjiZhangDiePingJiaShu retdata = new TongjiZhangDiePingJiaShu();
		HashMap<String,Object> hp = this.getBanKuaiShuXingFromRedis(market);
		if(ZDPtype.startsWith("ChengJiaoE"))
		{
			Statistics.TongjiChengJiaoE t = (Statistics.TongjiChengJiaoE) hp.get("ZDPtype");
			retdata.parseFromTongjiChengJiaoE(t);
		}
		else
			if(ZDPtype.contains("JiaShu"))
			{
				Statistics.TongjiZhangDiePingJiaShu t =  (Statistics.TongjiZhangDiePingJiaShu) hp.get("ZDPtype");
				retdata.parseFromTongjiZhangDiePingJiaShu(t);
			}
			else
			{
				retdata = null;
			}
		return retdata;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
