/**
 * @classnmae UserNewHeader.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.usernews.base;

/**
 * @author Litaojun
 * @date   2016年2月24日
 */
public class UserNewHeader
{
	private String pagesize  ;
	private String last      ;
	private String pre       ;
	private String next      ;
	private String totalsize ;
	private String first     ;
	private String totalpage ;
	public  String getPagesize()
	{
		return this.pagesize;
	}
	public  String getLast()
	{
		return this.last;
	}
	public  String getPre()
	{
		return this.pre;
	}
	public  String getNext()
	{
		return this.next;
	}
	public  String getTotalsize ()
	{
		return this.totalsize;
	}
	public  String getFirst()
	{
		return this.first;
	}
	public  String getTotalpage()
	{
		return this.totalpage;
	}
	public void setPagesize (String pagesize )
	{
		this.pagesize = pagesize;
	}
	public void setLast     (String last     )
	{
		this.last = last;
	}
	public void setPre(String pre      )
	{
		this.pre = pre;
	}
	public void setNext(String next     )
	{
		this.next = next;
	}
	public void setTotalsize(String totalsize)
	{
		this.totalsize = totalsize;
	}
	public void setFirst(String first    )
	{
		this.first = first;
	}
	public void setTotalpage(String totalpage)
	{
		this.totalpage = totalpage;
	}



}
