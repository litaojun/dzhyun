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
	private String token="00000001:1454118599:1bef295fd190b8b13d5a9956a7be7ef1804e76b3";
	public TopicinvestRedisData()
	{

	}

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
		Topicinvestdata.TopicInvestData tid = null;
		if(data != null)
		{
		   tid = Topicinvestdata.TopicInvestData.parseFrom(data);
			String topstr = tid.getTopicInvestId()+"----"+tid.getTopicInvestName()+"---"+tid.getComponentObjCount();
			for(int i=0;i<tid.getComponentObjCount();i++)
			{
			    String obj = tid.getComponentObjList().get(i);
			    System.out.println(obj);
			    
			}
			System.out.println(topstr);
		}
		else
			System.out.println("topicid="+topicid+",这个主题ID在redis中不存在");
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
		trd.getTitdataFromRedisByKey(4194);
		trd.getBanKuaiShuXingFromRedis(999999);
		trd.getBanKcfgFromRedis(4194);
	}
}
