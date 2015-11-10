package com.gw.dzhyun.svc.topicinvest;

import java.util.ArrayList;

public class TopicInvestDataInfo {
	private int topicInvestId;
	private String topicInvestName;
	//private ArrayList<String> componentObj = new ArrayList<String>();
	private String componentObj ;
	public void setTopicInvestId(int topicInvestId)
	{
		this.topicInvestId = topicInvestId;
	}
	public void setTopicInvestName(String topicInvestName)
	{
		this.topicInvestName = topicInvestName;
	}
//	public void setComponentObj(ArrayList<String> als)
//	{
//		this.componentObj = als;
//	}
	public void setComponentObj(String als)
	{
		this.componentObj = als;
	}
	public int getTopicInvestId()
	{
		return this.topicInvestId;
	}
	public String getTopicInvestName()
	{
		return this.topicInvestName;
	}
//	public ArrayList<String> getComponentObj()
//	{
//		return this.componentObj;
//	}
	public String getComponentObj()
	{
		return this.componentObj;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
