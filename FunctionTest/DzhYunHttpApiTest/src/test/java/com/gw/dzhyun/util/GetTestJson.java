package com.gw.dzhyun.util;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;

public class GetTestJson {
	public String code=null,type=null,error=null;
	private String ret=null;
	public TestCaseStyle tcs = null;
	private String urlString = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") + "/news/get?obj=YYYYYYYY&type=UUUU&ERROR";
	public HashMap paraMap = new HashMap();
	public String[] classPara=null;
	public JSONObject getJSONObject(String code,String type) throws SAXException, Exception
	{
		//System.out.println("this.code="+this.code+";this.type="+this.type);
		this.setParaMap(code, type);
		this.ret =MyHttpUtil. getQuoteDyna(urlString,"json");
		//System.out.println("ret="+ret);
		assertNotNull("错误：行情返回null",ret);
		JSONObject data = MyNewsGetUtil.getNewsGetByObjCode(ret);
		return data;
	}
	public void setClassPara(String[] classPara)
	{
		this.classPara = classPara;
	}
	public JSONObject sentHttpReq() throws SAXException, Exception
	{
		String urlstr = this.createReqUrlStr(this.classPara);
		System.out.println("urlstr="+urlstr);
		String retstr = MyHttpUtil. getQuoteDyna(urlstr,"json");
		System.out.println("GetTestJson->sentHttpReq-retstr="+retstr);
		assertNotNull("错误：行情返回null",retstr);
		JSONObject data = MyNewsGetUtil.getNewsGetByObjCode(retstr);
		return data;
	}
	public String createReqUrlStr(String[] arr)
	{
		String urlString = "http://" + MyConfigUtil.getConfig("ip") + ":" +MyConfigUtil.getConfig("port") +this.tcs.getOpeSteps();
		StringBuffer testurl =new StringBuffer();
		testurl.append(urlString);
		String tmp = this.getStrParaHashmap(arr);
		//System.out.println("GetTestJson->createReqUrlStr---teturl1111="+testurl);
		testurl.append(tmp);
		//System.out.println("GetTestJson->createReqUrlStr---teturl2222="+testurl);
		return testurl.toString();
	}
	public String getStrParaHashmap(String[] arr)
	{
		HashMap<String,String> input = this.tcs.getInputData();
		String retstr="";
		int sign = 0;
		for(int i=0;i<arr.length;i++)
		{
			//System.out.println("GetTestJson->getStrParaHashmap.for");
			String tmpstr = input.get(arr[i]);
			if(tmpstr!=null)
			{
				if(sign ==1)
					retstr = retstr + '&';
				retstr = retstr + arr[i]+"="+tmpstr;
				sign = 1;
			}
			
		}
		//System.out.println("GetTestJson->getStrParaHashmap-->retstr="+retstr);
		return retstr;
	}
	public JSONObject getJSONParentByStr()
	{
		return MyNewsGetUtil.getJSONObjByStr(this.ret);
	}
	public int getRetCodeByString() throws SAXException, Exception
	{
		int retcode=0;
		retcode = MyNewsGetUtil.getErrCodeByJsonString(this.ret);
		return retcode;
	}
	public JSONArray getJSONArrayByString()
	{
		return MyNewsGetUtil.getJSONArrayByJsonString(this.ret);
	}
	public void setParaMap(String codez,String typez)
	{
		this.paraMap.put("code", codez);
		this.paraMap.put("type", typez);
		//System.out.println(urlString);
		String aa;
		if(codez==null || codez.equals(""))
			 aa = urlString.replace("obj=YYYYYYYY&",codez);
		else
		    aa = urlString.replace("YYYYYYYY",codez);
		if(typez==null || typez.equals(""))
			 this.urlString = aa.replace("&type=UUUU",typez);
		else
		    this.urlString = aa.replace("UUUU",typez);
		//System.out.println("this.error="+this.error);
		if(this.error==null)
		{
			//System.out.println("zzzzzzzzzzzzzz");
			//System.out.println("this.code="+this.code+";this.type="+this.type);
			//System.out.println("codez="+codez+";typez="+typez);
			//System.out.println("this.urlString="+this.urlString);
			//System.out.println("xxxxxxxxxx");
			this.urlString = this.urlString.replace("&ERROR","");
			//System.out.println("this.urlString="+this.urlString);
			//System.out.println("yyyyyyyyyy");
		}
		else
			this.urlString = this.urlString.replace("&ERROR",error);
		System.out.println(urlString);
	}
	public static void main(String[] args)
	{
		//GetTestJson testg = new GetTestJson();
		//testg.setParaMap("SH000001", "1");
		String aa = "http://10.15.144.80:80/news/get?obj=YYYYYYYY&type=UUUU&ERROR";
		String xx=aa.replace("&ERROR","");
		System.out.println(xx);
		

	}
}
