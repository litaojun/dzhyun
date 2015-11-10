package com.gw.dzhyun.svc.topicinvest;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;


/*
 * http://dms.gw.com.cn/pages/viewpage.action?pageId=135299775
 * 从redis中读取主题投资采集数据
 * 主题投资在redis中有两种形式存储，
 * 第一种是通过单独KEY存储，KEY="TopicInvestData"+topicID，结构如下
 *  主题投资成份股数据 
		message TopicInvestData{
		 required int32 TopicInvestId = 1; //主题投资ID
		 required string TopicInvestName = 2; //主题投资名称
		 repeated string ComponentObj = 3; //主题投资成份股列表
		}
    对应的probuff结构的java类为Topicinvestdata（数据获取见该类的getTitdataFromRedisByKey函数）
 * 第二种，是将主题投资作为一个大的板块，主题投资下的热门板块做为一个子板块，存储在板块KEY对应的结构下，这样可以通过app.block.prop，app.block.obj服务接口获取主题投资的数据
 * 主题投资板块存储key= "BlockInfo"+999999，其value的probuff结构的java实现类为Topicinvestblock
 * 主题投资板块存储成分股存储KEY="BlockComponent"+topicID，其value的probuff结构的java实现类为Topicinvestblock
 */
public class TopicinvestRedisData {
//	private JedisOperator jed = new JedisOperator();
//	private BlockStruct bstruct = null;
	private JedisOperator jed = new JedisOperator();
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
		trd.getTitdataFromRedisByKey(2210);
		trd.getBanKuaiShuXingFromRedis(999999);
		trd.getBanKcfgFromRedis(2210);
	}
}
