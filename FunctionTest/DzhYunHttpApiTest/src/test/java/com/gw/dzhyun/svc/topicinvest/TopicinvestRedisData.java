package com.gw.dzhyun.svc.topicinvest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;
public class TopicinvestRedisData {
//	private JedisOperator jed = new JedisOperator();
//	private BlockStruct bstruct = null;
	private JedisOperator jed = new JedisOperator();
	public TopicinvestRedisData()
	{

	}
//	public static  HashMap<String,BanKuaiShuXingInfo> tranMapBanKuaiShuXingToSeflClass(HashMap<String,Topicinvestblock.BanKuaiShuXing> bksxMap)
//	{
//		HashMap<String,BanKuaiShuXingInfo> retHamp = new HashMap<String,BanKuaiShuXingInfo>();
//		Iterator<String> itr = bksxMap.keySet().iterator();
//		while(itr.hasNext())
//		{
//			BanKuaiShuXingInfo bksinfo = new BanKuaiShuXingInfo();
//			String key = itr.next();
//			Topicinvestblock.BanKuaiShuXing bkx = bksxMap.get(key);
//			bksinfo.setId(bkx.getId());
//			bksinfo.setBanKuaiJiBie(bkx.getBanKuaiJiBie());
//			bksinfo.setBaoHanZiBanKuaiGeShu(bkx.getBaoHanZiBanKuaiGeShu());
//			bksinfo.setSuoShuFuBanKuai(bkx.getSuoShuFuBanKuai());
//			bksinfo.setSuoShuGenBanKuai(bkx.getSuoShuGenBanKuai());
//			retHamp.put(key, bksinfo);
//			
//		}
//		return retHamp;
//	}
//	public static BanKuaiChengFenGuInfo tranBanKuaiChengFenGuToSelfClass(Topicinvestblock.BanKuaiChengFenGu bkcfg)
//	{
//		BanKuaiChengFenGuInfo bkcfginfo = new BanKuaiChengFenGuInfo();
//		bkcfginfo.setBanKuaiId(bkcfg.getBanKuaiId());
//		ArrayList<String> ass = new ArrayList<String>();
//		for(int i=0;i<bkcfg.getChengFenGuObjCount();i++)
//		{
//			ass.add(bkcfg.getChengFenGuObj(i));
//		}
//		bkcfginfo.setChengFenGuObj(ass);
//		return bkcfginfo;
//	}
//
//	public static TopicInvestDataInfo tranTopicInvestDataToSelfClass(Topicinvestdata.TopicInvestData topicdata)
//	{
//		TopicInvestDataInfo tidata = new TopicInvestDataInfo();
//		tidata.setTopicInvestId(topicdata.getTopicInvestId());
//		tidata.setTopicInvestName(topicdata.getTopicInvestName());
//		ArrayList<String> b = new ArrayList<String>();
//		for(int i=0;i<topicdata.getComponentObjCount();i++)
//		{
//			b.add(topicdata.getComponentObj(i));
//		}
//		tidata.setComponentObj(b);
//		return tidata;
//	}
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
	public Topicinvestdata.TopicInvestData getTitdataFromRedisByKey(int topicid) throws InvalidProtocolBufferException
	{
		//Topicinvestdata.TopicInvestData tid = new Topicinvestdata.TopicInvestData(true);
		System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes("B00",topicid);
		System.out.println("keybs="+keybs);
		byte[] data = jed.getByte(keybs);
		System.out.println("data="+data);
		Topicinvestdata.TopicInvestData tid = Topicinvestdata.TopicInvestData.parseFrom(data);
		String topstr = tid.getTopicInvestId()+"----"+tid.getTopicInvestName()+"---"+tid.getComponentObjCount();
		System.out.println(topstr);
		return tid;
	}
	public HashMap<String,Topicinvestblock.BanKuaiShuXing> getBanKuaiShuXingFromRedis(int topicid) throws InvalidProtocolBufferException
	{
		HashMap<String,Topicinvestblock.BanKuaiShuXing> rethm = new HashMap<String,Topicinvestblock.BanKuaiShuXing>();
		System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes("A60",topicid);
		System.out.println("keybs="+keybs);
		Map<byte[],byte[]> data = this.jed.getHashByteArr(keybs);
		Set<byte[]> set = data.keySet();
		Iterator<byte[]> it = set.iterator();
		while(it.hasNext())
		{
			byte[] curb = it.next();
			System.out.println("curb="+new String(curb));
			byte[] curdata = data.get(curb);
			Topicinvestblock.BanKuaiShuXing bksx = Topicinvestblock.BanKuaiShuXing.parseFrom(curdata);
			rethm.put(new String(curb), bksx);
			String topstr = bksx.getId()+ "$" +bksx.getSuoShuGenBanKuai()+ "$" + bksx.getSuoShuFuBanKuai()+ "$"+bksx.getBaoHanZiBanKuaiGeShu()+ "$"+bksx.getBanKuaiJiBie()+ "$" + bksx.getBanKuaiMingCheng() + "$"+ bksx.getQuanLuJingMingChengZhi() + "$"+ bksx.getQuanLuJingIdZhi();
			System.out.println(topstr);
		}
//		for(byte[] curdata : data)
//		{
//		System.out.println("curdata="+curdata);
//		Topicinvestblock.BanKuaiShuXing bksx = Topicinvestblock.BanKuaiShuXing.parseFrom(curdata);
//		String topstr = bksx.getId() +bksx.getSuoShuGenBanKuai() + bksx.getSuoShuFuBanKuai()+bksx.getBaoHanZiBanKuaiGeShu()+bksx.getBanKuaiJiBie() + bksx.getBanKuaiMingCheng() + bksx.getQuanLuJingMingChengZhi() + bksx.getQuanLuJingIdZhi();
//		System.out.println(topstr);
//		}
		return rethm;
		
	}
	public Topicinvestblock.BanKuaiChengFenGu getBanKcfgFromRedis(int topicid) throws InvalidProtocolBufferException
	{
		System.out.println("keybs="+"xx");
		byte[] keybs = this.getKeyBytes("A70",topicid);
		System.out.println("keybs="+keybs);
		byte[] data = this.jed.getByte(keybs);
		Topicinvestblock.BanKuaiChengFenGu tbbkcfg = Topicinvestblock.BanKuaiChengFenGu.parseFrom(data);
		String rst = String.valueOf(tbbkcfg.getBanKuaiId())+tbbkcfg.getChengFenGuObjCount();
		System.out.println("rst="+rst);
		return tbbkcfg;
	}
	 
	//public static void main(String[] args)
	public static void main(String[] args) throws InvalidProtocolBufferException
	{
		TopicinvestRedisData trd = new TopicinvestRedisData();
		trd.getTitdataFromRedisByKey(52);
		trd.getBanKuaiShuXingFromRedis(999999);
		trd.getBanKcfgFromRedis(52);
	}
}
