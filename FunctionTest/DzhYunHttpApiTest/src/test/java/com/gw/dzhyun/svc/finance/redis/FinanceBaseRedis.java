/**
 * @classnmae FinanceBaseRedis.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance.BasicFinanceData;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance.DividData;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance.RongZiRongQuanShuJu;
import com.gw.dzhyun.svc.topicinvest.Topicinvestblock;

/**
 * @author Litaojun
 * @date   2016年1月5日
 */
public class FinanceBaseRedis 
{
	private JedisOperator jed = new JedisOperator();
	/**
	 * 
	 * @param @param obj
	 * @param @return
	 * @Title getThreeByInt
	 * @Description 将内码转换为3个字节
	 * @return byte[]
	 *
	 */
	public byte[] getThreeByInt(int obj)
	{
		IoBuffer ioBuffer = IoBuffer.allocate(1024);   
        ioBuffer.putInt(obj);
        ioBuffer.flip();
        byte[] a = new byte[ioBuffer.limit()];
        ioBuffer.get(a);
        byte[] ret = new byte[3];
        for(int i=1;i<4;i++)
        {
        	//System.out.println(a[i]);
        	ret[3-i] = a[i];
        }
        return ret;
	}
	public byte[] getKeyBytes(String ktstr,String scdm,int obj)
	{
		byte[] objbyte = this.getThreeByInt(obj);
		IoBuffer ioBuffer = IoBuffer.allocate(1024);   
		ioBuffer.put((ktstr+scdm).getBytes());
        ioBuffer.put(objbyte);
        ioBuffer.flip();
        byte[] a = new byte[ioBuffer.limit()];
        ioBuffer.get(a);
        return a;
	}
	/**
	 * 
	 * @param @param ktstr
	 * @param @param scdm
	 * @param @param obj
	 * @param @return
	 * @Title getBasicFinanceDataByKeyFromRedis
	 * @Description 实时财务数据
	 * @return Dzhsvcfinance.BasicFinanceData
	 *
	 */
	public Dzhsvcfinance.BasicFinanceData getSSFinanceDataByKeyFromRedis(String ktstr,String scdm,int obj) 
	{
		byte[] key = this.getKeyBytes(ktstr, scdm, obj);
		try {
			Dzhsvcfinance.BasicFinanceData retdata = Dzhsvcfinance.BasicFinanceData.parseFrom(this.jed.getByte(key));
			return retdata;
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Dzhsvcfinance.RongZiRongQuanShuJu getBasicRongZiRongQuanShuJuByKeyFromRedis(String ktstr,String scdm,int obj) 
	{
		byte[] key = this.getKeyBytes(ktstr, scdm, obj);
		try {
			Dzhsvcfinance.RongZiRongQuanShuJu retdata = Dzhsvcfinance.RongZiRongQuanShuJu.parseFrom(this.jed.getByte(key));
			return retdata;
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param @param ktstr
	 * @param @param scdm
	 * @param @param obj
	 * @param @return
	 * @param @throws InvalidProtocolBufferException
	 * @Title getDividDataFromRedis
	 * @Description 
	 *              基本财务数据
	 *              从redis中取出 基本财务数据
	 * @return HashMap<String,Dzhsvcfinance.DividData>
	 *
	 */
	public HashMap<String,Dzhsvcfinance.BasicFinanceData> getBasicFinanceDataFromRedis(String ktstr,String scdm,int obj) throws InvalidProtocolBufferException
	{
		HashMap<String,Dzhsvcfinance.BasicFinanceData> rethm = new HashMap<String,Dzhsvcfinance.BasicFinanceData>();
		//System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes(ktstr, scdm, obj);
		//System.out.println("keybs="+new String(keybs));
		Map<byte[],byte[]> data = this.jed.getHashByteArr(keybs);
		Set<byte[]> set = data.keySet();
		Iterator<byte[]> it = set.iterator();
		while(it.hasNext())
		{
			byte[] curb = it.next();
			//System.out.println("curb="+new String(curb));
			byte[] curdata = data.get(curb);
			Dzhsvcfinance.BasicFinanceData bksx = Dzhsvcfinance.BasicFinanceData.parseFrom(curdata);
			rethm.put(new String(curb), bksx);
			//String topstr = bksx.getAccufundps()+ "$" +bksx.getEarnps()+ "$" + bksx.getObj()+"$"+bksx.getRepdate();
			//System.out.println(topstr);
		}
		return rethm;
	}
	
	/**
	 * 
	 * @param @param ktstr
	 * @param @param scdm
	 * @param @param obj
	 * @param @return
	 * @param @throws InvalidProtocolBufferException
	 * @Title getDividDataFromRedis
	 * @Description  从redis中取出融资融卷数据
	 * @return HashMap<String,Dzhsvcfinance.DividData>
	 *
	 */
	public ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> getRongZiRongQuanShuJuFromRedis(String ktstr,String scdm,int obj) throws InvalidProtocolBufferException
	{
		ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> rethm = new ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu>();
		//System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes(ktstr, scdm, obj);
		//System.out.println("keybs="+new String(keybs));
		ArrayList<byte[]> data = this.jed.getJredisList(keybs, 0, 60);
		for(byte[] it:data)
		{
			Dzhsvcfinance.RongZiRongQuanShuJu bksx = Dzhsvcfinance.RongZiRongQuanShuJu.parseFrom(it);
			rethm.add(bksx);
			//String topstr = bksx.getJiaoYiRiQi()+ "$" +bksx.getRongQuanChangHuanLiang()+ "$" + bksx.getObj();
			//System.out.println("litaojun=="+topstr);
		}
		return rethm;
	}
	public HashMap<String,Dzhsvcfinance.DividData> getDividDataFromRedis(String ktstr,String scdm,int obj) throws InvalidProtocolBufferException
	{
		HashMap<String,Dzhsvcfinance.DividData> rethm = new HashMap<String,Dzhsvcfinance.DividData>();
		//System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes(ktstr, scdm, obj);
		//System.out.println("keybs="+new String(keybs));
		Map<byte[],byte[]> data = this.jed.getHashByteArr(keybs);
		Set<byte[]> set = data.keySet();
		Iterator<byte[]> it = set.iterator();
		while(it.hasNext())
		{
			byte[] curb = it.next();
			//System.out.println("curb="+new String(curb));
			byte[] curdata = data.get(curb);
			Dzhsvcfinance.DividData bksx = Dzhsvcfinance.DividData.parseFrom(curdata);
			rethm.put(new String(curb), bksx);
			String topstr = bksx.getChuQuanRiQi()+ "$" +bksx.getGuQuanDenJiRi()+ "$" + bksx.getObj();
			//System.out.println(topstr);
		}
		return rethm;
	}
	public static void main(String[] args) throws InvalidProtocolBufferException
	{
		FinanceBaseRedis fbr = new FinanceBaseRedis();
		//基础财务数据SZ300297|3306
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("B10", "SZ", 3306);
		Iterator<String> keys = ddd.keySet().iterator();
		while(keys.hasNext())
		{
			System.out.println("222");
			String dat = keys.next();
			Dzhsvcfinance.BasicFinanceData dd = ddd.get(dat);
		}
		//实时财务数据SZ300297|3306
		Dzhsvcfinance.BasicFinanceData x = fbr.getSSFinanceDataByKeyFromRedis("C80", "SZ", 3306);
		System.out.println("xx="+x.getAccufundps()+"xx="+x.getAdjassetps());
		//融资融卷SH600028|5762
		ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> ff  = fbr.getRongZiRongQuanShuJuFromRedis("E20", "SH", 5762);
		System.out.println("yy="+ff.size());
		//除权数据 SH600623|6256
		HashMap<String,Dzhsvcfinance.DividData> fdd = fbr.getDividDataFromRedis("B10", "SH", 6256);
	}
}
