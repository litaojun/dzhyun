package com.gw.hqserver.util;

public class ParamsRequest {
	//请求参数
	public String szField="";
	public String szWhere="obj=SH601519";
	public String szStart="-1";
	public String szCount="1";
	public int  nResMode = 0;
	public String szParameter="type=dyna";
	public int  nAsyncResMode = 0;                     //同步还是异步模式
	public String szServiceName="quote";
	public int g_nResTimes=0;  //同步
	
	public String getRequest()
	{
		return "service="
				+szServiceName
				+"&"
				+ szParameter
				+ "&field="
				+szField
				+"&where="
				+szWhere
				+"&start="
				+szStart
				+"&count="
				+szCount
				+"&response_times="
				+g_nResTimes
				+"&response_mode="
				+nResMode
				+"&async_response_mode="
				+nAsyncResMode;
	}
}
