/**
 * @classnmae FutureEvent.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

import java.util.ArrayList;
import java.util.List;

import com.gw.dzhyun.util.Yfloat;
import com.gw.dzhyun.util.YfloatObj;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class FutureEvent 
{
	long eventid;
	String title;
	String expectTime;
	ArrayList<EventTopicData> eetlist = new ArrayList<EventTopicData>();
	ArrayList<EventObjData> eodlist = new ArrayList<EventObjData>();
	public boolean equals(Object o)
	{
		if(o instanceof FutureEvent)
		{
			FutureEvent ft = (FutureEvent)o;
//			System.out.println("2222222222222"+"FutureEvent");
//			boolean a = this.eventid ==  ft.getEventid() ;
//			boolean b = this.title.equals(ft.getTitle());
//			boolean c = this.expectTime.equals(ft.getExpectTime()) ;
//			boolean d = this.eetlist.containsAll(ft.getEetlist()) ;
//			boolean e =  this.eodlist.containsAll(ft.getEodlist()) ;
//			boolean f =  ft.getEetlist().containsAll(this.eetlist) ;
//			boolean g =  ft.getEodlist().containsAll(this.eodlist) ;
//			System.out.println("sign="+a +b+c+d+e+f+g);
		    return this.eventid ==  ft.getEventid() && this.title.equals(ft.getTitle()) && this.expectTime.equals(ft.getExpectTime()) && this.eetlist.containsAll(ft.getEetlist()) && this.eodlist.containsAll(ft.getEodlist()) && ft.getEetlist().containsAll(this.eetlist) && ft.getEodlist().containsAll(this.eodlist);
		}
		return false;
	}
	public long getEventid()
	{
		return this.eventid;
	}
	public void setEventid(long eid)
	{
		this.eventid = eid;
	}
	public String getTitle()
	{
		return this.title;
	}
	public void setTitle(String tile)
	{
		this.title = tile;
	}
	public String getExpectTime()
	{
		return this.expectTime;
	}
	public void setExpectTime(String extime)
	{
		this.expectTime = extime;
	}
	public ArrayList<EventTopicData> getEetlist()
	{
		return this.eetlist;
	}
	public void setEetlist(ArrayList<EventTopicData> eet)
	{
		this.eetlist = eet;
	}
	public ArrayList<EventObjData> getEodlist()
	{
		return this.eodlist;
	}
	public void setEodlist(ArrayList<EventObjData> ss)
	{
		this.eodlist = ss;
	}
	public void print()
	{
		System.out.println(eventid);
		System.out.println(String.format("FutureEvent----eventid=%s,title=%s,expectTime=%s", new String[]{String.valueOf(eventid),title,expectTime}));
		for(EventTopicData teod : eetlist)
		{
			teod.print();
		}
		for(EventObjData teod : eodlist)
		{
			teod.print();
		}
	}
	public void paserFromFutureEvent(Topicevent.FutureEvent a)
	{
		Yfloat yf = new Yfloat();
		YfloatObj yoj = yf.UnmakeValue(a.getEventID());
		this.eventid = (long)yoj.getValue();
		this.title = a.getTitle();
		this.expectTime = a.getExpectTime();
		List<Topicevent.EventObjData> ls = a.getObjDataList();
		List<Topicevent.EventTopicData> topicls = a.getTopicDataList();
		for(Topicevent.EventObjData teod : ls)
		{
			EventObjData x = new EventObjData();
			x.paserFromFutureEvent(teod);
			this.eodlist.add(x);
		}
		for(Topicevent.EventTopicData teod : topicls)
		{
			EventTopicData x = new EventTopicData(0,null);
			x.paserFromFutureEvent(teod);
			this.eetlist.add(x);
		}
	}

}
