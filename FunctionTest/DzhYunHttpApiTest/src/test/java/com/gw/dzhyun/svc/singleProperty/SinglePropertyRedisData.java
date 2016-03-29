/**
 * @classnmae SinglePropertyRedisData.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.singleProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.svc.topicinvest.Topicinvestblock;
import com.gw.dzhyun.svc.topicinvest.Topicinvestdata;

/**
 * @author Litaojun
 * @date   2015年11月30日
 */
public class SinglePropertyRedisData {
	
	private JedisOperator jed = new JedisOperator();
	/**
	 * 
	 * @param @param ktstr  key前缀
	 * @param @param topicid 板块ID
	 * @param @return 编码后的KEY
	 * @Title getKeyBytes
	 * @Description TODO  根据板块ID=99999998和其前缀A60得到编码后的KEY
	 * @return byte[]  编码后的KEY
	 *
	 */
	public byte[] getKeyBytes(String ktstr,int topicid)
	{
		IoBuffer ioBuffer = IoBuffer.allocate(1024);   
		 ioBuffer.put(ktstr.getBytes());
		 
		 if(ktstr.equals("A60") || ktstr.equals("A70"))
		 {
			 System.out.println("A60 加个0吧 ");
			 ioBuffer.putInt(0);
		 }
        ioBuffer.putInt(topicid);
        ioBuffer.flip();
        byte[] a = new byte[ioBuffer.limit()];
        ioBuffer.get(a);
        return a;
	}
/**
 * 
 * @param @param scdm
 * @param @return
 * @param @throws InvalidProtocolBufferException
 * @Title getBanKuaiShuXing
 * @Description TODO 获取单商品-单商品属性的PROBUFF结构
 * @return CldBanKuaiShu.BanKuaiShuXing
 *
 */
	public HashMap<String,DanShangPinShuXingInfo> getBanKuaiShuXing(String scdm) throws InvalidProtocolBufferException
	{
		//byte[] keybs = this.getKeyBytesScdm("A50",scdm);
		//System.out.println("keybs="+keybs);
		System.out.println("sssssssssssssssssssss");
		String sckey = "A50"+scdm;
		Map<byte[], byte[]> data = jed.getHashByteArr(sckey.getBytes());
		System.out.println("data.size="+data.size());
		HashMap<String,DanShangPinShuXingInfo> retmap = new HashMap<String,DanShangPinShuXingInfo>();
		ShangPinShuXing.DanShangPinShuXing danspid = null;
		if(data != null)
		{
			Iterator itor = data.entrySet().iterator();
			while(itor.hasNext())
			{
				Entry<byte[], byte[]> a = (Entry<byte[], byte[]>) itor.next();
				String curkey = new String(a.getKey());
				//System.out.println(curkey);
				danspid = ShangPinShuXing.DanShangPinShuXing.parseFrom(a.getValue());
				DanShangPinShuXingInfo dspinfo = new DanShangPinShuXingInfo();
				dspinfo.parseFromRedisBks(danspid);
				retmap.put(curkey, dspinfo);
			}

		}
		return retmap;
	}
	
	/**
	 * 
	 * @param @return
	 * @param @throws InvalidProtocolBufferException
	 * @Title getBanKuaiChengFenGu
	 * @Description TODO 获取ST板块
	 * @return CldBanKuaiShu.BanKuaiChengFenGu
	 *
	 */
	public HashMap<String,BankuaiShuInfo> getBanKuaiChengFenGu() throws InvalidProtocolBufferException
	{
		//System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes("A60",999998);
		//System.out.println("keybs="+keybs);
		//byte[] data = jed.getByte(keybs);
		Map<byte[], byte[]> data = jed.getHashByteArr(keybs);
		System.out.println("data.size="+data.size());
		//CldBanKuaiShu.BanKuaiChengFenGu bkcfid = null;
		HashMap<String,BankuaiShuInfo> bkshuInfo = new HashMap<String,BankuaiShuInfo>();
		if(data != null)
		{
			Iterator itor = data.entrySet().iterator();
			while(itor.hasNext())
			{
				Entry<byte[], byte[]> a = (Entry<byte[], byte[]>) itor.next();
				String tmpkey = new String(a.getKey());
				//System.out.println(tmpkey);
				CldBanKuaiShu.BanKuaiShuXing bkcfids = CldBanKuaiShu.BanKuaiShuXing.parseFrom(a.getValue());
				
				BankuaiShuInfo tmpBksinfo = new BankuaiShuInfo();
				tmpBksinfo.parseFromRedisBks(bkcfids);
				bkshuInfo.put(tmpkey, tmpBksinfo);
			}
	    }
		return bkshuInfo;
	}
	/**
	 * 
	 * @param @return
	 * @param @throws InvalidProtocolBufferException
	 * @Title getBanKuaiChengFenGuInfo
	 * @Description 获取ST板块下的所有成分股
	 * @return BanKuaiChengFenGuInfo
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	public BanKuaiChengFenGuInfo getBanKuaiChengFenGuInfo() throws InvalidProtocolBufferException 
	{
		BanKuaiChengFenGuInfo bkcfginfo = new BanKuaiChengFenGuInfo();
		byte[] keybs = this.getKeyBytes("A70",999998);
		byte[] a = this.jed.getByte(keybs);
		CldBanKuaiShu.BanKuaiChengFenGu bkcfg = CldBanKuaiShu.BanKuaiChengFenGu.parseFrom(a);
		bkcfginfo.parseFromBanKuaiChengFenGu(bkcfg);
		return bkcfginfo;
	}
	public  void printHashMapBankuaiShuInfo(HashMap<String,BankuaiShuInfo> hmapObj)
	{
		Iterator itobj =  hmapObj.keySet().iterator();
		while(itobj.hasNext())
		{
			String a = (String) itobj.next();
			System.out.println("key="+a);
			Object o = hmapObj.get(a);
			if(o instanceof BankuaiShuInfo)
			{
				System.out.println("obj=");
				BankuaiShuInfo tmp = (BankuaiShuInfo) o;
				tmp.printToString();
			}
		}
		
	}
	
	public void printHashMapDanShangPinShuXingInfo(HashMap<String,DanShangPinShuXingInfo> hmapObj)
	{
		Iterator itobj =  hmapObj.keySet().iterator();
		while(itobj.hasNext())
		{
			String a = (String) itobj.next();
			System.out.println("obj="+a);
			Object o = hmapObj.get(a);
			if(o instanceof DanShangPinShuXingInfo)
			{
				DanShangPinShuXingInfo tmp = (DanShangPinShuXingInfo) o;
				tmp.printDanShangPinShuXingInfoToString();
			}
		}
		
	}
	
	public ArrayList<String> getDateListByMarket(String makert) throws InvalidProtocolBufferException
	{
		ArrayList<String> retlist = new ArrayList<String>();
		byte[] a = this.jed.getByte(("El0"+makert).getBytes());
		DzhMarketTradeDate.MarketTradeDate retdata = DzhMarketTradeDate.MarketTradeDate.parseFrom(a);
		String rdmarker = retdata.getMarket();
		if(!rdmarker.equals(makert))
		{
			System.out.println("redis中市场数据错误");
			return null;
		}
		int size = retdata.getTradeDateCount();
		System.out.println("size="+size);
		for(int i=0;i<size;i++)
		{
			String datestr = retdata.getTradeDate(i);
			retlist.add(datestr);
		}
		return retlist;
	}

	public static void main(String[] args) throws InvalidProtocolBufferException
	{
		SinglePropertyRedisData a = new SinglePropertyRedisData();
		//HashMap<String, DanShangPinShuXingInfo> dspHm = a.getBanKuaiShuXing("SH");
		//a.printHashMapDanShangPinShuXingInfo(dspHm);
		//HashMap<String,BankuaiShuInfo> hmapObj = a.getBanKuaiChengFenGu();
		//a.printHashMapBankuaiShuInfo(hmapObj);
		//BanKuaiChengFenGuInfo bkcfginfo = a.getBanKuaiChengFenGuInfo();
		//bkcfginfo.printToString();
		ArrayList<String> retls = a.getDateListByMarket("SH");
		for(String mystr:retls)
			System.out.println(mystr);
		
	}
}
