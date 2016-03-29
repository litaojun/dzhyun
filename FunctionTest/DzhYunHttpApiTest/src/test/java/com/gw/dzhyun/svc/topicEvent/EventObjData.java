/**
 * @classnmae EventObjData.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class EventObjData {
	String obj;
	String name;
	public boolean equals(Object o)
	{

		if(o instanceof EventObjData)
		{
			EventObjData se = (EventObjData)o;
//			System.out.println("444444444444444444"+"EventObjData");
		    return this.obj.equals(se.getObj()) && this.name.equals(se.getName());
		}
		return false;
	}
	public void print()
	{
		System.out.println(String.format("EventObjData---obj=%s,name=%s", new String[]{obj,name}));
	}
	public void setObj(String obj)
	{
		this.obj = obj;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getObj()
	{
		return this.obj;
	}
	public String getName()
	{
		return this.name;
	}
	public void paserFromFutureEvent(Topicevent.EventObjData a)
	{
		this.obj = a.getObj();
		this.name = a.getName();
	}

}
