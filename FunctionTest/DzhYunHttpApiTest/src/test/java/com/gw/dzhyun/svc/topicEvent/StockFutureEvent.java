/**
 * @classnmae StockFutureEvent.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class StockFutureEvent 
{
	String obj;
	ArrayList<FutureEvent> feList = new ArrayList<FutureEvent>();
	public boolean equals(Object o)
	{
		if(o instanceof StockFutureEvent )
		{
			StockFutureEvent se = (StockFutureEvent)o;
			
//			boolean a = this.obj.equals(se.getObj());
//			boolean b = this.feList.containsAll(se.getList());
//			boolean c =  se.getList().containsAll(this.feList);
//			System.out.println("1111111111111111"+"StockFutureEvent"+a+b+c);
		    return this.obj.equals(se.getObj()) && this.feList.containsAll(se.getList()) && se.getList().containsAll(this.feList);
		}
		return false;
	}
	public void print()
	{

		System.out.println("StockFutureEvent--obj="+this.obj);
		for(FutureEvent x : feList)
		{
			x.print();
		}
	}
	public void setObj(String obj)
	{
		this.obj = obj;
	}
	public void setFeList(ArrayList<FutureEvent> obj)
	{
		this.feList = obj;
	}
	public String getObj()
	{
		return this.obj;
	}
	public ArrayList<FutureEvent> getList()
	{
		return this.feList;
	}
	public void parseFromStockFutureEvent(Topicevent.StockFutureEvent a)
	{
		this.obj = a.getObj();
		List<Topicevent.FutureEvent> tfet = a.getDataList();
		for(Topicevent.FutureEvent tf:tfet)
		{
			FutureEvent x = new FutureEvent();
			x.paserFromFutureEvent(tf);
			this.feList.add(x);
			//System.out.println("xx"+tf.getEventID()+"$"+tf.getTitle());
		}
	}
}
