/**
 * @classnmae TopicEventRedisBase.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;



import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class TopicEventRedisBase 
{
	private JedisOperator jed = new JedisOperator();
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
	public Topicevent.StockFutureEvent getStockFutureEventByKeyFromRedis(String ktstr,String scdm,int obj) 
	{
		byte[] key = this.getKeyBytes(ktstr, scdm, obj);
		try {
			Topicevent.StockFutureEvent retdata = Topicevent.StockFutureEvent.parseFrom(this.jed.getByte(key));
			return retdata;
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args)
	{
		TopicEventRedisBase x = new TopicEventRedisBase();
		Topicevent.StockFutureEvent tse = x.getStockFutureEventByKeyFromRedis("E00", "SZ", 3323);
		StockFutureEvent sfe = new StockFutureEvent();
		sfe.parseFromStockFutureEvent(tse);
		sfe.print();
	}

}
