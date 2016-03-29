/**
 * @classnmae EventTopicData.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

import com.gw.dzhyun.util.Yfloat;
import com.gw.dzhyun.util.YfloatObj;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class EventTopicData
{
	private long topicInvestId;
	private String topicInvestName ;
	public EventTopicData(long tid,String topname)
	{
		this.topicInvestId = tid;
		this.topicInvestName = topname;
	}
	public boolean equals(Object o)
	{
		if(o instanceof EventTopicData)
		{
			EventTopicData se = (EventTopicData)o;
//			System.out.println("33333333333333333"+"EventTopicData");
			return this.topicInvestId ==se.getTopicInvestId() && this.topicInvestName.equals(se.getTopicInvestName());
		}
		return false;
		
	}
	public long getTopicInvestId()
	{
		return this.topicInvestId;
	}
	public String getTopicInvestName()
	{
		return this.topicInvestName;
	}
	public void print()
	{
		System.out.println(topicInvestId);
		System.out.println(String.format("EventTopicData---topicInvestId=%s,topicInvestName=%s", new String[]{String.valueOf(topicInvestId),topicInvestName}));
	}
	public void paserFromFutureEvent(Topicevent.EventTopicData a)
	{
		Yfloat yf = new Yfloat();
		YfloatObj yoj = yf.UnmakeValue(a.getTopicInvestId());
		this.topicInvestId = (long)yoj.getValue();
		this.topicInvestName = a.getTopicInvestName();
	}

}
